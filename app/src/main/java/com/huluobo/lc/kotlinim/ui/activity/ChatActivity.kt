package com.huluobo.lc.kotlinim.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.EMMessageListenerAdapter
import com.huluobo.lc.kotlinim.adapter.MessageListAdapter
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.ChatContract
import com.huluobo.lc.kotlinim.presenter.ChatPresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 10:33
 */
class ChatActivity : BaseActivity(), ChatContract.View {
    val TAG = "ChatActivity"
    override fun getLayoutResId(): Int = R.layout.activity_chat
    val presenter = ChatPresenter(this)
    lateinit var username: String
    private val messageListener = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(messages: MutableList<EMMessage>?) {
            presenter.addMessage(username, messages)
            runOnUiThread {
                recyclerView.adapter?.notifyDataSetChanged()
                scrollToBottom()
            }
        }
    }

    override fun init() {
        super.init()
        initHeader()
        initEditText()
        initRecyclerView()
        EMClient.getInstance().chatManager().addMessageListener(messageListener)
        send.setOnClickListener { send() }
        presenter.loadMessages(username)

    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, presenter.messages)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    //当RecyclerView是一个空闲的状态
                    //检查是否滑动到顶部,要加在更多数据
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //如果第一个可见条目的位置是0,为滑倒顶部
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                            //加在更多数据
                            presenter.loadMoreMessage(username)
                        }
                    }
                }
            })
        }
    }

    private fun send() {
        hideSoftKeyboard()
        val message = edit.text.trim().toString()
        if (message.isNotEmpty()) {
            presenter.sendMessage(username, message)
        }
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //如果用户输入的文本长度大于0,发送按钮enable
                send.isEnabled = !s?.trim().isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                send.isEnabled = !s?.trim().isNullOrEmpty()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        edit.setOnEditorActionListener { _, _, _ ->
            send()
            true
        }
    }

    private fun initHeader() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }

        //获取聊天的用户名
        username = intent.getStringExtra("username")
        val titleString = String.format(getString(R.string.chat_title), username)
        headerTitle.text = titleString
    }

    override fun onStartSendMessage() {
        //通知RecyclerView刷新列表
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onSendMessageSuccess() {
        recyclerView.adapter?.notifyDataSetChanged()
        toast(R.string.send_message_success)
        //清空编辑框
        edit.text.clear()
        scrollToBottom()
    }

    private fun scrollToBottom() {
        recyclerView.scrollToPosition(presenter.messages.size - 1)
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onMessageLoaded() {
        recyclerView.adapter?.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun onMoreMessageLoaded(size: Int) {
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)

    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}