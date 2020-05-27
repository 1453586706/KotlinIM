package com.huluobo.lc.kotlinim.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
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
                    //开始注册 //正常公司中的app需要注册到公司的后台服务器,因为咱们没有后台,所以注册到Bomb模拟后台数据
                    registerBmob(userName, password)
                } else view.onConfirmPasswordError()
            } else view.onPasswordError()
        } else view.onUserNameError()
    }

    private fun registerBmob(userName: String, password: String) {
        val bmobUser = BmobUser()
        bmobUser.username = userName
        bmobUser.setPassword(password)

        bmobUser.signUp(object : SaveListener<BmobUser>() {
            override fun done(bmobUser: BmobUser?, e: BmobException?) {
                if (e == null) {
                    //注册成功
                    //注册到环信,(公司流程)从服务器直接注册到环信服务器,返回给app是成功或者失败的消息

                } else {
                    //注册失败
                    view.onRegisterFailed()
                }
            }
        })
    }
}