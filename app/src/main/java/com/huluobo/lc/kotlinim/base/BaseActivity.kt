package com.huluobo.lc.kotlinim.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Lc
 * @description:
 * @date :2020/5/25 11:51
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    open fun init() {
        //初始化一些公共的功能,比如摇一摇,子类也可以复写该方法,完成自己的初始化

    }

    //子类必须实现该方法返回一个布局资源的id
    abstract fun getLayoutResId(): Int
}