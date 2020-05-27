package com.huluobo.lc.kotlinim.contract

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 16:38
 */
interface ContactContact {
    interface Presenter : BasePresenter {
        fun loadContacts()
    }

    interface View {
        fun onLoadContactSuccess()
        fun onLoadContactFailed()
    }
}