package com.huluobo.lc.kotlinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.huluobo.lc.kotlinim.R

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
}