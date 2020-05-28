package com.huluobo.lc.kotlinim.presenter

import com.huluobo.lc.kotlinim.contract.AddFriendContract

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:33
 */
class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {
    override fun search(key: String) {
        //需要完成model层 搜索并返回model
    }
}