package com.example.sushantproject

import android.app.Application
import com.example.sushantproject.services.NotificationService
import com.example.sushantproject.utils.Constants
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Verbose Logging helps determine OneSignal issues with the app
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
        OneSignal.initWithContext(this, Constants.ONESIGNAL_APP_ID)

        // Setup Notification Listeners
        NotificationService.setupNotificationListeners()
    }
}
