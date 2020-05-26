package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.contract.LoginContract
import com.huluobo.lc.kotlinim.extentions.isValidPassword
import com.huluobo.lc.kotlinim.extentions.isValidUserName
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

/**
 * @author Lc
 * @description:
 * @date :2020/5/26 10:43
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {
    override fun login(userName: String, password: String) {
        if (userName.isValidUserName()) {
            //用户名合法,继续校验密码
            if (password.isValidPassword()) {
                //密码合法,开始登陆
                view.onStartLogin()
                loginEaseMob(userName, password)//登陆到环信
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName, password, object : EMCallBack {
            //在子线程回调
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                uiThread { view.onLoggedInSuccess() }
            }

            override fun onProgress(progress: Int, status: String?) {
                TODO("Not yet implemented")
            }

            override fun onError(code: Int, error: String?) {
                uiThread { view.onLoggedInFailed() }
            }
        })
    }
}