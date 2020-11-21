package com.hjhjw1991.barney.logcat

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import com.jaredrummler.android.processes.AndroidProcesses

object ProcessUtil {
    fun getProcessNames(context: Context): Map<String, Int> {
        return if (Build.VERSION.SDK_INT > 21) {
            getProcessNamesManually(context)
        } else {
            getProcessNamesCompat(context)
        }
    }

    // todo 获取所有进程
    private fun getProcessNamesManually(context: Context): Map<String, Int>{
        val processes = AndroidProcesses.getRunningAppProcesses()
        return mutableMapOf<String, Int>().apply {
            processes.forEach {
                this[it.name] = it.pid
            }
        }
    }

    private fun getProcessNamesCompat(context: Context): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in activityManager.runningAppProcesses) {
            if (processInfo.processName.contains(context.packageName)) {
                map[processInfo.processName] = processInfo.pid
            }
        }
        return map
    }
}