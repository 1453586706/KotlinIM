package com.huluobo.lc.kotlinim.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.ConversationListAdapter
import com.huluobo.lc.kotlinim.adapter.EMMessageListenerAdapter
import com.huluobo.lc.kotlinim.adapter.MessageListAdapter
import com.huluobo.lc.kotlinim.base.BaseFragment
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
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
    private val conversations = mutableListOf<EMConversation>()

    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: MutableList<EMMessage>?) {
            loadConversations()
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_conversation

    override fun init() {
        super.init()
        headerTitle.text = getText(R.string.message)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ConversationListAdapter(context, conversations)
        }
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
    }

    private fun loadConversations() {
        doAsync {
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread {
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        conversations.clear()
        loadConversations()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}