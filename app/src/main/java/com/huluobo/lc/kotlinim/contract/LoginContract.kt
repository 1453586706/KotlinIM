package com.huluobo.lc.kotlinim.contract

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 17:39
 */
interface LoginContract {
    interface Presenter : BasePresenter {
        fun login(userName: String, password: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onStartLogin()
        fun onLoggedInSuccess()
        fun onLoggedInFailed()
    }
}