package com.huluobo.lc.kotlinim.adapter

import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMMessage

/**
 * @author Lc
 * @description:
 * @date :2020/5/30 10:29
 */

open class EMMessageListenerAdapter : EMMessageListener {
    override fun onMessageRecalled(messages: MutableList<EMMessage>?) {
        TODO("Not yet implemented")
    }

    override fun onMessageChanged(message: EMMessage?, change: Any?) {
        TODO("Not yet implemented")
    }

    override fun onCmdMessageReceived(messages: MutableList<EMMessage>?) {
        TODO("Not yet implemented")
    }

    override fun onMessageReceived(messages: MutableList<EMMessage>?) {
        TODO("Not yet implemented")
    }

    override fun onMessageDelivered(messages: MutableList<EMMessage>?) {
        TODO("Not yet implemented")
    }

    override fun onMessageRead(messages: MutableList<EMMessage>?) {
        TODO("Not yet implemented")
    }

}