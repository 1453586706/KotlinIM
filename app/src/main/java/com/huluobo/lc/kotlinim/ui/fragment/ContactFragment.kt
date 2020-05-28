package com.huluobo.lc.kotlinim.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.ContactListAdapter
import com.huluobo.lc.kotlinim.adapter.EMContactListenterAdapter
import com.huluobo.lc.kotlinim.base.BaseFragment
import com.huluobo.lc.kotlinim.contract.ContactContact
import com.huluobo.lc.kotlinim.presenter.ContactPresenter
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 15:05
 */
class ContactFragment : BaseFragment(), ContactContact.View {
    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE

        swipeRefreshLayout.apply {
            //对当前控件或者类的创建者模式,kotlin高级语法
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }

        EMClient.getInstance().contactManager()
            .setContactListener(object : EMContactListenterAdapter() {
                override fun onContactDeleted(username: String?) {
                    //重新获取联系人数据
                    presenter.loadContacts()
                }
            })

        presenter.loadContacts()
    }

    override fun onLoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onLoadContactFailed() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(R.string.load_contacts_failed)
    }
}