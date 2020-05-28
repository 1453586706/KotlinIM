package com.huluobo.lc.kotlinim.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huluobo.lc.kotlinim.widget.AddFriendListItemView

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:22
 */
class AddFriendAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddFriendListItemViewHolder(AddFriendListItemView(context))
    }

    override fun getItemCount(): Int = 30

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    class AddFriendListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    }
}