package com.huluobo.lc.kotlinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.huluobo.lc.kotlinim.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 16:37
 */
class ReceiveMessageItemView(context: Context, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTimestamp: Boolean) {
        updateMessage(emMessage)
        updateTimestamp(emMessage, showTimestamp)
    }

    init {
        View.inflate(context, R.layout.view_receive_message_item, this)
    }

    private fun updateMessage(emMessage: EMMessage) {
        if (emMessage.type == EMMessage.Type.TXT) {
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else {
            //其他消息类型写在这里else if
            receiveMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun updateTimestamp(
        emMessage: EMMessage,
        showTimestamp: Boolean
    ) {
        if (showTimestamp) {
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        } else timestamp.visibility = View.GONE
    }
}