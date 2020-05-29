package com.huluobo.lc.kotlinim.presenter

import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.huluobo.lc.kotlinim.contract.AddFriendContract
import com.huluobo.lc.kotlinim.data.AddFriendItem
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:33
 */
class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {

    val addFriendItems = mutableListOf<AddFriendItem>()

    override fun search(key: String) {
        //需要完成model层 搜索并返回model
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username", key)
            .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(list: MutableList<BmobUser>?, e: BmobException?) {
                if (e == null) {
                    //处理数据
                    //创建AddFriendItem的集合
                    doAsync {
                        list?.forEach {
                            val addFriendItem = AddFriendItem(it.username, it.createdAt)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread { view.onSearchSuccess() }
                    }
                } else view.onSearchFailed()
            }
        })
    }
}