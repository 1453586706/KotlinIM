package com.huluobo.lc.kotlinim.ui.fragment

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.ContactListAdapter
import com.huluobo.lc.kotlinim.adapter.EMContactListenterAdapter
import com.huluobo.lc.kotlinim.base.BaseFragment
import com.huluobo.lc.kotlinim.contract.ContactContact
import com.huluobo.lc.kotlinim.presenter.ContactPresenter
import com.huluobo.lc.kotlinim.ui.activity.AddFriendActivity
import com.huluobo.lc.kotlinim.widget.SlideBar
import com.hyphenate.EMContactListener
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 15:05
 */
class ContactFragment : BaseFragment(), ContactContact.View {
    val TAG = "ContactFragment"
    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)

    private val contactListener = object : EMContactListenterAdapter() {
        override fun onContactDeleted(username: String?) {
            //重新获取联系人数据
            presenter.loadContacts()
        }

        override fun onContactAdded(username: String?) {
            //重新获取联系人数据
            presenter.loadContacts()
        }
    }

    override fun init() {
        super.init()
        initHeader()
        initSwipeRefreshLayout()
        initRecyclerView()
        EMClient.getInstance().contactManager()
            .setContactListener(contactListener)
        initSlideBar()
        presenter.loadContacts()
    }

    private fun initSlideBar() {
        slideBar.onSectionChangeListener = object : SlideBar.OnSectionChangeListener {
            override fun onSectionChange(firstLetter: String) {
                section.visibility = View.VISIBLE
                section.text = firstLetter
                Log.i(TAG, "下标:" + getPosition(firstLetter))
                recyclerView.smoothScrollToPosition(getPosition(firstLetter))
                //TODO 需要修复下标为负
            }

            override fun onSlideFinish() {
                section.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactListAdapter(context, presenter.contactListItems)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            //对当前控件或者类的创建者模式,kotlin高级语法
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadContacts() }
        }
    }

    private fun initHeader() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener {
            context?.startActivity<AddFriendActivity>()
        }
    }

    private fun getPosition(firstLetter: String): Int =
        presenter.contactListItems.binarySearch { contactListItem ->
            contactListItem.firstLetter.minus(firstLetter[0])
        }


    override fun onLoadContactSuccess() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.isRefreshing = false
        }
        if (recyclerView != null) {
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onLoadContactFailed() {
        swipeRefreshLayout.isRefreshing = false
        context?.toast(R.string.load_contacts_failed)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(contactListener)
    }
}