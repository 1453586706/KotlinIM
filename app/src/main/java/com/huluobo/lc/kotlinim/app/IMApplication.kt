package com.huluobo.lc.kotlinim.app

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.multidex.MultiDex
import cn.bmob.v3.Bmob
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.EMMessageListenerAdapter
import com.huluobo.lc.kotlinim.ui.activity.ChatActivity
import com.hyphenate.chat.*

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 17:22
 */
class IMApplication : Application() {
    val TAG = "IMApplication"

    companion object {
        lateinit var instance: IMApplication
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    val soundPool: SoundPool = SoundPool.Builder()
        .setMaxStreams(10)
        .build()

    val duan by lazy {
        soundPool.load(instance, R.raw.duan, 0)
    }

    val yulu by lazy {
        soundPool.load(instance, R.raw.yulu, 0)
    }

    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: MutableList<EMMessage>?) {
            //如果在前台,播放短的声音
            if (isForeground()) {
                soundPool.play(duan, 1f, 1f, 0, 0, 1f)
            } else {
                Log.i(TAG, "isForeground:" + isForeground())
                //如果在后台,则播放长的声音
                soundPool.play(yulu, 1f, 1f, 0, 0, 1f)
                showNotification(messages)
            }
        }
    }

    private fun showNotification(messages: MutableList<EMMessage>?) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        messages?.forEach {
            var contentText = getString(R.string.no_text_message)
            if (it.type == EMMessage.Type.TXT) {
                contentText = (it.body as EMTextMessageBody).message
            }//[图片][视频]

            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("username", it.conversationId())
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = Notification.Builder(this)
                .setContentTitle(getString(R.string.receive_new_message))
                .setContentText(contentText)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.avatar1))
                .setSmallIcon(R.mipmap.ic_contact)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
            notificationManager.notify(1, notification)
        }
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

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        soundPool.play(duan, 1f, 1f, 0, 0, 1f)
        soundPool.play(yulu, 1f, 1f, 0, 0, 1f)

    }

    private fun isForeground(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (runningAppProcess in activityManager.runningAppProcesses) {
            if (runningAppProcess.processName == packageName) {
                //找到了app进程
                return runningAppProcess.importance ==
                        ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                //当前app运行的关键级,是否等于处于前台位置的关键级
            }
        }
        return false
    }
}