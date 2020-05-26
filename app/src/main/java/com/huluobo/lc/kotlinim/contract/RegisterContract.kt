package com.huluobo.lc.kotlinim.contract

/**
 * @author Lc
 * @description:
 * @date :2020/5/26 16:44
 */
interface RegisterContract {
    interface Presenter : BasePresenter {
        fun register(userName: String, password: String, confirmPassword: String)
    }

    interface View {
        fun onUserNameError()
        fun onPasswordError()
        fun onConfirmPasswordError()
        fun onStartRegister()
        fun onRegisterSuccess()
        fun onRegisterFailed()
    }
}