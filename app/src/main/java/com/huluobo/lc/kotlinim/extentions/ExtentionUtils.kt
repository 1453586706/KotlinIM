package com.huluobo.lc.kotlinim.extentions

/**
 * @author Lc
 * @description:
 * @date :2020/5/26 10:46
 */
fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))//扩展函数
fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]{3,20}$"))

fun <K, V> MutableMap<K, V>.toVarargArray(): Array<Pair<K, V>> {
    //将MutableMap转换成Pair类型的数组
    return map {
        Pair(it.key, it.value)
    }.toTypedArray()
}