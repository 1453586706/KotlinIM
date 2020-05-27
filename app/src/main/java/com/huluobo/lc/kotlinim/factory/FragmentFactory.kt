package com.huluobo.lc.kotlinim.factory

import androidx.fragment.app.Fragment
import com.huluobo.lc.kotlinim.R
import com.huluobo.lc.kotlinim.ui.fragment.ContactFragment
import com.huluobo.lc.kotlinim.ui.fragment.ConversationFragment
import com.huluobo.lc.kotlinim.ui.fragment.DynamicFragment

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 15:09
 */
class FragmentFactory private constructor() {
    private val conversation by lazy {
        ConversationFragment()
    }

    private val contact by lazy {
        ContactFragment()
    }

    private val dynamic by lazy {
        DynamicFragment()
    }

    companion object {
        val instance = FragmentFactory()
    }

    fun setFragment(tabId: Int): Fragment? {
        when (tabId) {
            R.id.tab_conversation -> return conversation
            R.id.tab_contacts -> return contact
            R.id.tab_dynamic -> return dynamic
        }
        return null
    }
}