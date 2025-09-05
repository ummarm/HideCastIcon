package com.hidecasticon

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.content.getSystemService

class OverlayService : Service() {

    private var overlayView: View? = null
    private var windowManager: WindowManager? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService()
        showOverlay()
    }

    private fun showOverlay() {
        if (windowManager == null) return

        val params = WindowManager.LayoutParams(
            // width and height in pixels - keep small (~48px)
            48,
            48,
            // Use TYPE_APPLICATION_OVERLAY for Android O+
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        )

        // Position top-right: Gravity TOP | END
        params.gravity = Gravity.TOP or Gravity.END

        val inflater = LayoutInflater.from(this)
        overlayView = inflater.inflate(R.layout.overlay_view, null)
        // Optional: set alpha or background programmatically
        overlayView?.setBackgroundResource(android.R.color.black)
        windowManager?.addView(overlayView, params)
    }

    override fun onDestroy() {
        overlayView?.let { windowManager?.removeView(it) }
        overlayView = null
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Keep service running
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
