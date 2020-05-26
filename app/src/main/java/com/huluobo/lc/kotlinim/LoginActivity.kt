package com.huluobo.lc.kotlinim

import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 16:40
 */
class LoginActivity : BaseActivity(), LoginContract.View {
    override fun getLayoutResId(): Int = R.layout.activity_login
    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onStartLogin() {
        //弹出进度条
        showProgress(getString(R.string.logging))
    }

    override fun onLoggedInSuccess() {
        //隐藏进度条
        dimissProgress()
        //进入主界面
        startActivity<MainActivity>()
        //退出LoginActivity
        finish()
    }

    override fun onLoggedInFailed() {
        //隐藏进度条
        dimissProgress()
        //弹出toast
        toast(getString(R.string.login_failed))
    }

}