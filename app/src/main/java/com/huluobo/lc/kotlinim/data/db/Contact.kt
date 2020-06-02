package com.huluobo.lc.kotlinim.data.db

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 11:08
 */

data class Contact(val map: MutableMap<String, Any?>) {
    val _id by map
    val name by map
}