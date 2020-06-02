package com.huluobo.lc.kotlinim.ui.activity

import android.util.Log
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.EMMessageListenerAdapter
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.factory.FragmentFactory
import com.hyphenate.EMConnectionListener
import com.hyphenate.EMError
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main
    val TAG = "MainActivity"

    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: MutableList<EMMessage>?) {
            Log.i(TAG, "onMessageReceived")
            runOnUiThread {
                updateBottomBarUnReadCount()
            }
        }
    }

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(
                R.id.fragment_frame,
                FragmentFactory.instance.setFragment(tabId)!!
            )
            beginTransaction.commit()
        }

        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        EMClient.getInstance().addConnectionListener(object : EMConnectionListener {
            override fun onConnected() {

            }

            override fun onDisconnected(errorCode: Int) {
                if (errorCode == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    //发生多设备登录,跳转到登录界面
                    startActivity<LoginActivity>()
                    toast(getString(R.string.user_login_another_device))
                    finish()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onMessageReceived,onResume")
        updateBottomBarUnReadCount()
    }

    private fun updateBottomBarUnReadCount() {
        //初始化bottomBar维度消息的个数
        val tab = bottomBar.getTabWithId(R.id.tab_conversation)
        tab.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)//设置角标
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}
