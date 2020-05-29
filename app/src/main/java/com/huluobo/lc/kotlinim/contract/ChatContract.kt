package com.huluobo.lc.kotlinim.contract

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 16:38
 */
interface ChatContract {
    interface Presenter : BasePresenter {
        fun sendMessage(contact: String, message: String)
    }

    interface View {
        fun onStartSendMessage()
        fun onSendMessageSuccess()
        fun onSendMessageFailed()
    }
}