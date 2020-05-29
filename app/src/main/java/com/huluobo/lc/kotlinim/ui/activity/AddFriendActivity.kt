package com.huluobo.lc.kotlinim.ui.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.AddFriendAdapter
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.AddFriendContract
import com.huluobo.lc.kotlinim.presenter.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:11
 */
class AddFriendActivity : BaseActivity(), AddFriendContract.View {
    val presenter = AddFriendPresenter(this)

    override fun getLayoutResId(): Int = R.layout.activity_add_friend

    override fun init() {
        super.init()
        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendAdapter(context,presenter.addFriendItems)
        }
        search.setOnClickListener {
            search()
        }
        userName.setOnEditorActionListener { _, _, _ ->
            search()
            true
        }

    }

    private fun search() {
        hideSoftKeyboard()
        showProgress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        presenter.search(key)
    }

    override fun onSearchSuccess() {
        dimissProgress()
        toast(R.string.search_success)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSearchFailed() {
        dimissProgress()
        toast(R.string.search_failed)
    }
}