package com.huluobo.lc.kotlinim.app

import android.app.Application
import com.hyphenate.chat.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 17:22
 */
class IMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化
        EMClient.getInstance().init(applicationContext, EMOptions())
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
    }
}