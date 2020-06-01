package com.huluobo.lc.kotlinim.ui.activity

import android.util.Log
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.EMMessageListenerAdapter
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.factory.FragmentFactory
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_main.*

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
