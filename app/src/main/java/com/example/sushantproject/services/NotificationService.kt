package com.example.sushantproject.services

import android.util.Log
import com.onesignal.notifications.INotificationClickEvent
import com.onesignal.notifications.INotificationClickListener
import com.onesignal.OneSignal
import org.json.JSONObject

object NotificationService {

    private const val TAG = "NotificationService"

    fun setupNotificationListeners() {

        OneSignal.Notifications.addClickListener(
            object : INotificationClickListener {

                override fun onClick(event: INotificationClickEvent) {

                    Log.d(
                        TAG,
                        "Notification clicked: ${event.notification.title}"
                    )

                    val data: JSONObject? =
                        event.notification.additionalData

                    if (data != null) {
                        Log.d(TAG, "Additional Data: $data")
                    }
                }
            }
        )
    }
}