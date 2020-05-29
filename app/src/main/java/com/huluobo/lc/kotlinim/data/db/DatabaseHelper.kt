package com.huluobo.lc.kotlinim.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.huluobo.lc.kotlinim.app.IMApplication
import org.jetbrains.anko.db.*

/**
 * @author Lc
 * @description:
 * @date :2020/5/29 10:59
 */
class DatabaseHelper(
    ctx: Context = IMApplication.instance
) : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object {
        const val NAME = "im.db"
        const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            ContactTable.NAME,
            true,
            ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME, true)
        onCreate(db)
    }

}