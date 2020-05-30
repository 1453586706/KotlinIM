package com.huluobo.lc.kotlinim.contract

import com.hyphenate.chat.EMMessage

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 16:38
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(contact: String, message: String)
        fun addMessage(username: String, messages: MutableList<EMMessage>?)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}