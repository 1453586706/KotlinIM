package com.huluobo.lc.kotlinim.app

import android.app.Application
import androidx.multidex.MultiDex
import cn.bmob.v3.Bmob
import com.hyphenate.chat.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 17:22
 */
class IMApplication : Application() {
    companion object {
        lateinit var instance: IMApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        //环信初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)

        //Bmob初始化
        Bmob.initialize(applicationContext, "f62b061c76cd94fd0356bc76098738a0")
    }
}