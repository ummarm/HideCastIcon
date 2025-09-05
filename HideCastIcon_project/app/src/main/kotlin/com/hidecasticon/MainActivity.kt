package com.hidecasticon

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If overlay permission not granted, prompt user to grant it via Settings flow.
        if (!Settings.canDrawOverlays(this)) {
            AlertDialog.Builder(this)
                .setTitle("Grant overlay permission")
                .setMessage("Please allow 'Display over other apps' for HideCastIcon. We'll open the settings screen; enable it and then press Back.")
                .setPositiveButton("Open settings") { _, _ ->
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                    startActivity(intent)
                }
                .setNegativeButton("Cancel") { _, _ -> finish() }
                .setCancelable(false)
                .show()
        } else {
            // Start overlay service
            startService(Intent(this, OverlayService::class.java))
            finish()
        }
    }
}
