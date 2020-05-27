package com.huluobo.lc.kotlinim.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.ContactListAdapter
import com.huluobo.lc.kotlinim.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 15:05
 */
class ContactFragment : BaseFragment() {
    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            //对当前控件或者类的创建者模式,kotlin高级语法
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context)
        }
    }
}