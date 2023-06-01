package com.kssidll.choochoo

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import dagger.hilt.android.AndroidEntryPoint

object NotificationChannels {
    const val TICKET_BOUGHT = "ticketbought"
    const val TICKET_CANCELLED = "ticketcancelled"
    const val DEPARTURE_TIME_CLOSE = "departuretimeclose"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val navigationViewModel: NavigationViewModel by viewModels()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //!! Lock orientation to portrait
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // support notifications for APK26+
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager.notificationChannels.isEmpty()) {

            notificationManager.createNotificationChannels(
                listOf(
                    NotificationChannel(NotificationChannels.TICKET_BOUGHT, "Ticket Bought", NotificationManager.IMPORTANCE_HIGH),
                    NotificationChannel(NotificationChannels.TICKET_CANCELLED, "Ticket Cancelled", NotificationManager.IMPORTANCE_HIGH),
                    NotificationChannel(NotificationChannels.DEPARTURE_TIME_CLOSE, "Departure Reminder", NotificationManager.IMPORTANCE_HIGH)
                )
            )

            startActivity(
                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                }
            )

        }

        setContent {
            ChooChooTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }

        navigationViewModel.setAction(intent.action, intent.extras)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            navigationViewModel.setAction(intent.action, intent.extras)
        }
    }

}
