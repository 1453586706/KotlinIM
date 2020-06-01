package com.huluobo.lc.kotlinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huluobo.lc.kotlinim.widget.ConversationListItemView
import com.hyphenate.chat.EMConversation

/**
 * @author Lc
 * @description:
 * @date :2020/6/1 11:23
 */
class ConversationListAdapter(
    val context: Context,
    private val conversations: MutableList<EMConversation>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(ConversationListItemView(context))
    }

    override fun getItemCount(): Int = conversations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder.itemView as ConversationListItemView
        conversationListItemView.bindView(conversations[position])
    }

    class ConversationListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}