package me.chunyu.yuriel.kotdebugtool.components.floating

import android.app.Activity
import android.app.ActivityManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import me.chunyu.yuriel.kotdebugtool.components.DTActivityManager
import java.lang.reflect.InvocationTargetException

/**
 * Created by yuriel on 9/1/16.
 */
internal class __FloatingService : Service() {

    private var binder: Binder? = null

    override fun onCreate() {
        super.onCreate()
        createView()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private fun createView() {
        FloatingViewMgr.setupWith(this)
        FloatingViewMgr.show3DViewFloating()
    }

    override fun onDestroy() {
        super.onDestroy()
        FloatingViewMgr.release3DView()
        FloatingViewMgr.releaseContext(this)
    }

    fun getForegroundActivity(): Activity? = DTActivityManager.topActivity

    inner class FloatingBinder: Binder() {
        fun getService(): __FloatingService {
            return this@__FloatingService
        }
    }
}