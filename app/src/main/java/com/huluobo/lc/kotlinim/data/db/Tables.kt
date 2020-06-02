package com.huluobo.lc.kotlinim.data.db


/**
 * @author Lc
 * @description:
 * @date :2020/5/29 10:56
 *
 * 1.定义ContactTable类
 * 2,创建ContactTable的一个实例,通过类名直接访问实例,实现单例的一种方式
 */
object ContactTable {
    val NAME = "contact"

    //定义字段
    val ID = "_id"
    val CONTACT = "name"
}