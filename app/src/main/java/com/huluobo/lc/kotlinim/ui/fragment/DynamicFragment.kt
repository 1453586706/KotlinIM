package com.huluobo.lc.kotlinim.ui.fragment

import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.EMCallbackAdapter
import com.huluobo.lc.kotlinim.base.BaseFragment
import com.huluobo.lc.kotlinim.ui.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * @author Lc
 * @description:
 * @date :2020/5/27 15:06
 */

class DynamicFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_dynamic

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.dynamic)

        val logoutString =
            String.format(getString(R.string.logout), EMClient.getInstance().currentUser)
        logout.text = logoutString

        logout.setOnClickListener { logout() }
    }

    fun logout() {
        EMClient.getInstance().logout(true, object : EMCallbackAdapter() {
            override fun onSuccess() {
                context?.runOnUiThread {
                    toast(R.string.logout_success)
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onError(code: Int, error: String?) {
                context?.runOnUiThread { toast(R.string.logout_failed) }
            }
        })
    }
}