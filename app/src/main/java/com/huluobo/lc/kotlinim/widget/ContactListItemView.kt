package com.huluobo.lc.kotlinim.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.huluobo.lc.kotlinim.R

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 16:18
 */

class ContactListItemView(context: Context?, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_contact_item,this)
    }

}