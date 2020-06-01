package com.huluobo.lc.kotlinim.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.ConversationListAdapter
import com.huluobo.lc.kotlinim.base.BaseFragment
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 15:03
 */

class ConversationFragment : BaseFragment() {
    val conversations = mutableListOf<EMConversation>()

    override fun getLayoutResId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getText(R.string.message)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context, conversations)
        }

        loadConversations()
    }

    private fun loadConversations() {
        doAsync {
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread {
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }
}