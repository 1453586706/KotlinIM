package com.huluobo.lc.kotlinim.widget

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.huluobo.lc.kotlinim.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_send_message_item.view.*
import java.util.*

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 16:33
 */

class SendMessageItemView(context: Context, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    private val TAG = "SendMessageItemView"
    fun bindView(emMessage: EMMessage) {
        updateTimestamp(emMessage)
        updateMessage(emMessage)
        updateProgress(emMessage)
    }

    private fun updateProgress(emMessage: EMMessage) {
        emMessage.status().let {
            when (it) {
                EMMessage.Status.INPROGRESS -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.drawable.send_message_progress)
                    val animationDrawable = sendMessageProgress.drawable as AnimationDrawable
                    animationDrawable.start()
                }
                EMMessage.Status.SUCCESS -> {
                    sendMessageProgress.visibility = View.GONE
                }
                EMMessage.Status.FAIL -> {
                    sendMessageProgress.visibility = View.VISIBLE
                    sendMessageProgress.setImageResource(R.mipmap.msg_error)
                }
            }
        }
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            sendMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            //其他消息类型写在这里else if
            sendMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun updateTimestamp(emMessage: EMMessage) {
        Log.i(TAG, DateUtils.getTimestampString(Date(emMessage.msgTime)))
        timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
    }

    init {
        View.inflate(context, R.layout.view_send_message_item, this)
    }
}