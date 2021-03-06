package com.huluobo.lc.kotlinim.ui.activity

import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.RegisterContract
import com.huluobo.lc.kotlinim.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity(), RegisterContract.View {

    private val presenter = RegisterPresenter(this)

    override fun init() {
        super.init()
        register.setOnClickListener { register() }
        confirmPassword.setOnEditorActionListener { _, _, _ ->
            register()
            true
        }
    }

    private fun register() {
        //隐藏软键盘
        hideSoftKeyboard()
        val userNameString = userName.text.trim().toString()
        val passwordString = password.text.trim().toString()
        val confirmPasswordString = confirmPassword.text.trim().toString()
        presenter.register(userNameString, passwordString, confirmPasswordString)
    }

    override fun getLayoutResId(): Int =
        R.layout.activity_register
    override fun onUserNameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfirmPasswordError() {
        confirmPassword.error = getString(R.string.confirm_password_error)
    }

    override fun onStartRegister() {
        showProgress(getString(R.string.registering))
    }

    override fun onRegisterSuccess() {
        dimissProgress()
        finish()
        toast(R.string.register_success)
    }

    override fun onRegisterFailed() {
        dimissProgress()
        toast(R.string.register_failed)
    }

    override fun onUserExist() {
        dimissProgress()
        toast(R.string.user_already_exist)
    }
}
