package com.huluobo.lc.kotlinim.contract

import android.os.Handler
import android.os.Looper

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 16:28
 */
interface BasePresenter {

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    fun uiThread(f: () -> Unit) {
        handler.post { f() }
    }
}