package com.huluobo.lc.kotlinim.ui.activity

import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun init() {
        super.init()
        bottomBar.setOnTabSelectListener { tabId ->
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(
                R.id.fragment_frame,
                FragmentFactory.instance.setFragment(tabId)!!
            )
            beginTransaction.commit()
        }
    }
}
