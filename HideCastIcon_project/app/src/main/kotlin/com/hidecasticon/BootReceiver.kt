package com.hidecasticon

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            context?.let {
                // Delay a bit and start the service
                Handler(Looper.getMainLooper()).postDelayed({
                    it.startService(Intent(it, OverlayService::class.java))
                }, 5000)
            }
        }
    }
}
