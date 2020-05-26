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
        //伴生函数
        val handler by lazy {
            Handler(Looper.getMainLooper())//通过Looper主线程绑定Handler
        }
    }

    fun uiThread(f: () -> Unit) {//f代表一个方法,kotlin语法中参数可以是一个方法,Unit:代表没有返回值(void)
        handler.post { f() }//把参数中传入的方法放到handler中去运行
    }
}