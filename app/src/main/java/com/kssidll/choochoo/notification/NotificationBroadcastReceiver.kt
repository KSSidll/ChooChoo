package com.kssidll.choochoo.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import com.kssidll.choochoo.data.repository.ITicketRepository
import com.kssidll.choochoo.notification.Extras.CONNECTION_DESTINATION
import com.kssidll.choochoo.notification.Extras.CONNECTION_ORIGIN
import com.kssidll.choochoo.notification.Extras.NOTIFICATION_DELAY
import com.kssidll.choochoo.notification.Extras.TICKET_ID
import com.kssidll.choochoo.notification.NotificationActions.CANCEL_TICKET
import com.kssidll.choochoo.notification.NotificationActions.SCHEDULE_DEPARTURE_TIME_NOTIFICATION
import com.kssidll.choochoo.notification.NotificationActions.SHOW_DEPARTURE_TIME_NOTIFICATION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

object NotificationActions {
    const val CANCEL_TICKET = "notification.actions.ticketcancel"
    const val SCHEDULE_DEPARTURE_TIME_NOTIFICATION = "notification.actions.scheduledeparturetimenotification"
    const val SHOW_DEPARTURE_TIME_NOTIFICATION = "notification.actions.showdeparturetimenotification"
}

@AndroidEntryPoint
class NotificationBroadcastReceiver : BroadcastReceiver() {
    @Inject lateinit var ticketRepository: ITicketRepository
    @Inject lateinit var connectionRepository: IConnectionRepository
    @Inject lateinit var stationRepository: IStationRepository

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent != null) {
            when (intent.action) {
                CANCEL_TICKET -> CoroutineScope(Dispatchers.IO).launch {
                    val ticket = ticketRepository.get(intent.extras?.getInt(TICKET_ID)!!)
                    val connection = connectionRepository.get(ticket.connectionId)

                    ticketRepository.cancel(ticket.id)

                    sendTicketCancelledNotification(
                        context = context!!,
                        origin = stationRepository.get(connection.originId).name,
                        destination = stationRepository.get(connection.destinationId).name,
                        price = connection.price
                    )
                }

                SCHEDULE_DEPARTURE_TIME_NOTIFICATION -> {

                    val pendingIntent = PendingIntent.getBroadcast(
                        context!!,
                        0,
                        Intent(context, this::class.java).apply {
                            this.action = SHOW_DEPARTURE_TIME_NOTIFICATION

                            this.putExtras(Bundle().apply {
                                putString(CONNECTION_ORIGIN, intent.extras?.getString(CONNECTION_ORIGIN)!!)
                                putString(CONNECTION_DESTINATION, intent.extras?.getString(CONNECTION_DESTINATION)!!)
                                putInt(TICKET_ID, intent.extras?.getInt(TICKET_ID)!!)
                            })
                        },
                        PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                    )

                    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + intent.extras?.getLong(NOTIFICATION_DELAY)!!,
                        pendingIntent
                    )
                }

                SHOW_DEPARTURE_TIME_NOTIFICATION -> {
                    sendDepartureTimeCloseNotification(
                        context = context!!,
                        origin = intent.extras?.getString(CONNECTION_ORIGIN)!!,
                        destination = intent.extras?.getString(CONNECTION_DESTINATION)!!,
                        ticketId = intent.extras?.getInt(TICKET_ID)!!
                    )
                }
            }
        }
    }
}