package com.huluobo.lc.kotlinim.contract

/**
 * @author Lc
 * @description: Splash的协议,处理登录状态,修改UI
 * @date :2020/5/25 16:29
 */
interface SplashContract {
    interface Presenter : BasePresenter {
        fun checkLoginStatus()//检查登录状态
    }

    interface View {
        fun onNotLoggedIn()//没有登录的ui处理
        fun onLoggedIn()//已经登录的ui处理
    }
}