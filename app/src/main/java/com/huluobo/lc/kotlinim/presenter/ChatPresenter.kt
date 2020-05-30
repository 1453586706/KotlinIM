package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.adapter.EMCallbackAdapter
import com.huluobo.lc.kotlinim.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 16:46
 */
class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {

    val messages = mutableListOf<EMMessage>()

    override fun sendMessage(contact: String, message: String) {
        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message, contact)
        emMessage.setMessageStatusCallback(object : EMCallbackAdapter() {
            override fun onSuccess() {
                uiThread { view.onSendMessageSuccess() }
            }

            override fun onError(code: Int, error: String?) {
                uiThread { view.onSendMessageFailed() }
            }
        })
        messages.add(emMessage)
        view.onStartSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addMessage(username: String, messages: MutableList<EMMessage>?) {
        //加入当前的消息列表
        messages?.let { this.messages.addAll(it) }
        //更新消息为已读消息
        //获取跟联系人的会话,然后标记会话里面的消息全部为已读
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()//标记为已读
    }
}