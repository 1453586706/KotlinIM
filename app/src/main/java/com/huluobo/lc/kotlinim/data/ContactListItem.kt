package com.huluobo.lc.kotlinim.data

/**
 * @author Lc
 * @description:
 * @date :2020/5/27 17:05
 */
data class ContactListItem(
    val userName: String,
    val firstLetter: Char,
    val showFirstLetter: Boolean = true
)