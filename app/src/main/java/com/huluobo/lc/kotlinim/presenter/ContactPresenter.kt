package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.contract.ContactContact
import com.huluobo.lc.kotlinim.data.ContactListItem
import com.huluobo.lc.kotlinim.data.db.Contact
import com.huluobo.lc.kotlinim.data.db.IMDatabase
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 16:42
 */
class ContactPresenter(val view: ContactContact.View) : ContactContact.Presenter {
    val contactListItems = mutableListOf<ContactListItem>()//可变的list

    override fun loadContacts() {
        doAsync {
            //再次加载数据,先清空集合
            contactListItems.clear()
            //清空数据库
            IMDatabase.instance.deleteAllContacts()
            try {
                val usernames =
                    EMClient.getInstance().contactManager().allContactsFromServer
                contactListItems.clear()
                usernames.sortBy { it[0] }
                usernames.forEachIndexed { index, s ->
                    //什么时候显示首字符
                    //当index=0显示
                    //usernames上一位的首字符如果和当前首字符不同
                    val showFirstLetter = index == 0 || s[0] != usernames[index - 1][0]
                    val contactListItem =
                        ContactListItem(s, s[0].toUpperCase(), showFirstLetter)//拿到每一个联系人对象(名字,首字母)
                    contactListItems.add(contactListItem)

                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)
                }

                usernames.forEach {
                    //判断是否显示首字符


                }

                uiThread { view.onLoadContactSuccess() }
            } catch (e: HyphenateException) {
                uiThread { view.onLoadContactFailed() }
            }

        }
    }
}