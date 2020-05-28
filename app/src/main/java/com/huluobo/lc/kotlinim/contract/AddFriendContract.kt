package com.huluobo.lc.kotlinim.contract

/**
 * @author Lc
 * @description:
 * @date :2020/5/28 17:29
 */
interface AddFriendContract {
    interface Presenter : BasePresenter {
        fun search(key: String)
    }

    interface View {
        fun onSearchSuccess()
        fun onSearchFailed()
    }
}