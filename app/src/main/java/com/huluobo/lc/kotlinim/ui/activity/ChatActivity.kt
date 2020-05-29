package com.huluobo.lc.kotlinim.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.MessageListAdapter
import com.huluobo.lc.kotlinim.base.BaseActivity
import com.huluobo.lc.kotlinim.contract.ChatContract
import com.huluobo.lc.kotlinim.presenter.ChatPresenter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 10:33
 */
class ChatActivity : BaseActivity(), ChatContract.View {
    override fun getLayoutResId(): Int = R.layout.activity_chat
    val presenter = ChatPresenter(this)
    lateinit var username: String

    override fun init() {
        super.init()
        initHeader()
        initEditText()
        initRecyclerView()
        send.setOnClickListener { send() }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageListAdapter(context, presenter.messages)
        }
    }

    private fun send() {
        hideSoftKeyboard()
        val message = edit.text.trim().toString()
        presenter.sendMessage(username, message)
    }

    private fun initEditText() {
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //如果用户输入的文本长度大于0,发送按钮enable
                send.isEnabled = !s.isNullOrEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
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
    }

    override fun onSendMessageFailed() {
        toast(R.string.send_message_failed)
        recyclerView.adapter?.notifyDataSetChanged()
    }


}