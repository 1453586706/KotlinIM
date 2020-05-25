package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.contract.SplashContract

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 17:03
 */
class SplashPresenter(private val view: SplashContract.View) : SplashContract.Presenter {
    override fun checkLoginStatus() {
        if (isLoggedIn()) view.onLoggedIn() else view.onNotLoggedIn()
    }

    private fun isLoggedIn(): Boolean = false

}