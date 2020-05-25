package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.contract.SplashContract
import com.hyphenate.chat.EMClient

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 17:03
 */
class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    //是否登录到环信的服务器
    private fun isLoggedIn(): Boolean =
        EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore

}