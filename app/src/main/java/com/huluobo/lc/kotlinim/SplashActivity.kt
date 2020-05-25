package com.huluobo.lc.kotlinim

import android.os.Handler
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.SplashContract
import org.jetbrains.anko.startActivity

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 16:09
 */
class SplashActivity : BaseActivity(), SplashContract.View {
    companion object {
        const val DELAY = 2000L
    }

    private val handler by lazy {
        Handler()
    }

    override fun getLayoutResId(): Int =
        R.layout.activity_splash

    //1.如果没有登录,延时2S,跳转登录页面
    override fun onNotLoggedIn() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, DELAY)
    }

    //2.如果已经登录,则跳转到主页面
    override fun onLoggedIn() {
        startActivity<MainActivity>()
        finish()
    }

}