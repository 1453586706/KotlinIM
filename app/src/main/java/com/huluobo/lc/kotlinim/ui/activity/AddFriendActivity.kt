package com.huluobo.lc.kotlinim.ui.activity

import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.base.BaseActivity
import kotlinx.android.synthetic.main.header.*

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:11
 */
class AddFriendActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)
    }
}