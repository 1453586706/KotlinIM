package com.huluobo.lc.kotlinim

import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.LoginContract
import com.huluobo.lc.kotlinim.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.jar.Manifest

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 16:40
 */
class LoginActivity : BaseActivity(), LoginContract.View {
    private val presenter = LoginPresenter(this)

    override fun init() {
        super.init()
        newUser.setOnClickListener { startActivity<RegisterActivity>() }
        login.setOnClickListener { login() }
        password.setOnEditorActionListener { _, _, _ ->
            login()
            true
        }
    }

    private fun login() {
        //隐藏软键盘
        hideSoftKeyboard()
        if (hasWriteExternalStoragePermission()) {
            val userNameString = userName.text.trim().toString()
            val passwordString = password.text.trim().toString()
            presenter.login(userNameString, passwordString)
        } else applyWriteExternalStoragePermission()


    }

    private fun applyWriteExternalStoragePermission() {
        val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permission, 0)
    }

    //检查是否有些磁盘的权限
    private fun hasWriteExternalStoragePermission(): Boolean {
        val checkSelfPermission = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //用户同意权限,开始登录
            login()
        }else toast(R.string.permission_denied)
    }

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