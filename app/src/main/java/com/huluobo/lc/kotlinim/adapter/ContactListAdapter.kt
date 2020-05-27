package com.huluobo.lc.kotlinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huluobo.lc.kotlinim.data.ContactListItem
import com.huluobo.lc.kotlinim.widget.ContactListItemView

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 16:25
 */
class ContactListAdapter(
    val context: Context,
    val contactListItems: MutableList<ContactListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactListItemViewHolder(ContactListItemView(context))
    }

    override fun getItemCount(): Int = contactListItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
    }

    class ContactListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

}