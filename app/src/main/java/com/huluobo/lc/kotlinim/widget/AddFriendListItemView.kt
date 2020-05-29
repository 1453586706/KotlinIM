package com.huluobo.lc.kotlinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.adapter.EMCallbackAdapter
import com.huluobo.lc.kotlinim.data.AddFriendItem
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.view_add_friend_item.view.*
import kotlinx.android.synthetic.main.view_contact_item.view.*
import kotlinx.android.synthetic.main.view_contact_item.view.userName
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:17
 */

class AddFriendListItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_add_friend_item, this)
    }

    fun bindView(addFriendItem: AddFriendItem) {
        if (addFriendItem.isAdded) {
            add.isEnabled = false
            add.text = context.getString(R.string.already_added)
        } else {
            add.isEnabled = true
            add.text = context.getString(R.string.add)
        }
        userName.text = addFriendItem.userName
        timestamp.text = addFriendItem.timestamp

        add.setOnClickListener { addFriend(addFriendItem.userName) }
    }

    private fun addFriend(userName: String) {
        EMClient.getInstance().contactManager()
            .aysncAddContact(userName, null, object : EMCallbackAdapter() {
                override fun onSuccess() {
                    context.runOnUiThread { toast(R.string.send_add_friend_success) }
                }

                override fun onError(code: Int, error: String?) {
                    context.runOnUiThread { toast(R.string.send_add_friend_failed) }
                }
            })
    }
}