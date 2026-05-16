package com.example.sushantproject.services

import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NotificationHelper {

    /**
     * Set the Supabase User ID as OneSignal External ID for targeted notifications.
     */
    fun setExternalUserId(userId: String) {
        OneSignal.login(userId)
    }

    /**
     * Tags the user with their blood group to receive relevant emergency alerts.
     */
    fun tagUserBloodGroup(bloodGroup: String) {
        OneSignal.User.addTag("blood_group", bloodGroup)
    }

    /**
     * Tags the user with their location for regional emergency alerts.
     */
    fun tagUserLocation(location: String) {
        OneSignal.User.addTag("location", location)
    }

    /**
     * Request notification permission for Android 13+.
     */
    fun requestPermission() {
        CoroutineScope(Dispatchers.Main).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}
