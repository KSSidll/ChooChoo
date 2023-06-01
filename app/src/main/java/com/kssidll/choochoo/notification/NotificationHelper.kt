package com.kssidll.choochoo.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kssidll.choochoo.MainActivity
import com.kssidll.choochoo.NavigationActions
import com.kssidll.choochoo.NotificationChannels
import com.kssidll.choochoo.R
import com.kssidll.choochoo.notification.Extras.CONNECTION_DESTINATION
import com.kssidll.choochoo.notification.Extras.CONNECTION_ORIGIN
import com.kssidll.choochoo.notification.Extras.NOTIFICATION_DELAY
import com.kssidll.choochoo.notification.Extras.TICKET_ID
import java.util.Locale

object Extras {
    const val TICKET_ID = "extras.ticketid"
    const val CONNECTION_ORIGIN = "extras.connectionorigin"
    const val CONNECTION_DESTINATION = "extras.connectiondestination"
    const val NOTIFICATION_DELAY = "extras.notificationdelay"
}

data class NotificationAction(
    val drawable: Int,
    val text: String,
    val pendingIntent: PendingIntent
) {
    constructor(context: Context, drawable: Int, text: String, action: String, extras: Bundle = Bundle.EMPTY) : this(
        drawable = drawable,
        text = text,
        pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, NotificationBroadcastReceiver::class.java).apply {
                this.action = action

                this.putExtras(extras)
            },
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    )
}

fun getNotification(
    context: Context,
    channel: String,
    title: String,
    text: String? = null,
    intent: Intent? = null,
    requestCode: Int = 0,
    priority: Int = NotificationCompat.PRIORITY_HIGH,
    action: NotificationAction? = null
): NotificationCompat.Builder {
    val notification = NotificationCompat.Builder(context, channel)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(title)
        .setPriority(priority)
        .setAutoCancel(true)

    if (text != null) {
        notification.setContentText(text)
    }

    if (intent != null) {
        notification.setContentIntent(
            PendingIntent.getActivity(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        )

        if (action != null) {
            notification.addAction(action.drawable, action.text, action.pendingIntent)
        }
    }

    return notification
}

fun sendNotification(
    context: Context,
    channel: String,
    title: String,
    text: String? = null,
    intent: Intent? = null,
    requestCode: Int = 0,
    priority: Int = NotificationCompat.PRIORITY_HIGH,
    action: NotificationAction? = null,
    notificationId: Int = 0
) {
    with (NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            val notification = getNotification(context, channel, title, text, intent, requestCode, priority, action)

            notify(notificationId, notification.build())
        }
    }
}

fun sendTicketCancelledNotification(context: Context, origin: String, destination: String, price: Int) {
    sendNotification(
        context = context,
        channel = NotificationChannels.TICKET_CANCELLED,
        title = "You just cancelled a ticket From $origin to $destination",
        text = "Refunded ${String.format(Locale("en", "US"), "$%.2f", (price.toFloat() / 100))}",
        intent = null
    )
}

fun sendTicketBoughtNotification(context: Context, origin: String, destination: String, price: Int, ticketId: Int) {
    sendNotification(
        context = context,
        channel = NotificationChannels.TICKET_BOUGHT,
        title = "You just bought a ticket for ${String.format(Locale("en", "US"), "$%.2f", (price.toFloat() / 100))}",
        text = "From $origin to $destination",
        intent = Intent(context, MainActivity::class.java).apply {
            action = NavigationActions.ACTION_SHOW_TICKET_DETAILS
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            putExtra(TICKET_ID, ticketId)
        },
        action = NotificationAction(
            context = context,
            drawable = R.drawable.ic_launcher_foreground,
            text = "Cancel",
            action = NotificationActions.CANCEL_TICKET,
            extras = Bundle().apply {
                putInt(TICKET_ID, ticketId)
            }
        ),
    )
}

fun scheduleDepartureTimeCloseNotification(
    context: Context,
    delay: Long,
    origin: String,
    destination: String,
    ticketId: Int
) {
    context.sendBroadcast(
        Intent(context, NotificationBroadcastReceiver::class.java).apply {
            this.action = NotificationActions.SCHEDULE_DEPARTURE_TIME_NOTIFICATION
            this.putExtras(Bundle().apply {
                putString(CONNECTION_ORIGIN, origin)
                putString(CONNECTION_DESTINATION, destination)
                putInt(TICKET_ID, ticketId)
                putLong(NOTIFICATION_DELAY, delay)
            })
        }
    )
}

fun sendDepartureTimeCloseNotification(context: Context, origin: String, destination: String, ticketId: Int) {
    sendNotification(
        context = context,
        channel = NotificationChannels.DEPARTURE_TIME_CLOSE,
        title = "Your departure time for ticket $origin} to $destination is close",
        intent = Intent(context, MainActivity::class.java).apply {
            action = NavigationActions.ACTION_SHOW_TICKET_DETAILS
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            putExtra(TICKET_ID, ticketId)
        }
    )
}
