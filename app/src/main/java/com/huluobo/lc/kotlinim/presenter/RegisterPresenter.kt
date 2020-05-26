package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.contract.RegisterContract
import com.huluobo.lc.kotlinim.extentions.isValidPassword
import com.huluobo.lc.kotlinim.extentions.isValidUserName

/**
 * @author Lc
 * @description:
 * @date :2020/5/26 17:07
 */
class RegisterPresenter(val view: RegisterContract.View) : RegisterContract.Presenter {
    override fun register(userName: String, password: String, confirmPassword: String) {
        if (userName.isValidUserName()) {
            //检查密码
            if (password.isValidPassword()) {
                //检查确认密码
                if (password == confirmPassword) {
                    //密码和确认密码一致
                    view.onStartRegister()
                    //开始注册

                } else view.onConfirmPasswordError()
            } else view.onPasswordError()
        } else view.onUserNameError()
    }
}