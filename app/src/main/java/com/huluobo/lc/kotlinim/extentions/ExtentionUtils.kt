package com.huluobo.lc.kotlinim.extentions

/**
 * @author Lc
 * @description:
 * @date :2020/5/26 10:46
 */
fun String.isValidUserName():Boolean=this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))//扩展函数
fun String.isValidPassword():Boolean=this.matches(Regex("^[0-9]{3,20}$"))