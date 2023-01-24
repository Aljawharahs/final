package com.second.finalapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ProjectDatabase(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val initBrekfastTable = "CREATE TABLE $BREKFAST_TABLE " +
                "($COLUMN_BNAME TEXT PRIMARY KEY , " +
                "$COLUMN_BDESCRPTION TEXT, " +
                "$COLUMN_BPRICE TEXT)"

        val initLunchTable = "CREATE TABLE $LUNCH_TABLE " +
                "($COLUMN_LNAME TEXT PRIMARY KEY , " +
                "$COLUMN_LDESCRPTION TEXT, " +
                "$COLUMN_LPRICE TEXT)"

        db.execSQL(initBrekfastTable)
        db.execSQL(initLunchTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun getItem(bname: String): BrekfastModel? {
        val db = readableDatabase
        val query = "SELECT * FROM $BREKFAST_TABLE WHERE $COLUMN_BNAME = '$bname'"
        val cursor = db.rawQuery(query, null)
        var menu: BrekfastModel? = null

        try {
            if (cursor.moveToFirst()) {
                val descrption = cursor.getString(cursor.getColumnIndex(COLUMN_BDESCRPTION))
                val price = cursor.getString(cursor.getColumnIndex(COLUMN_BPRICE))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_BNAME))
                menu = BrekfastModel(name, descrption, price)
            }
        } catch (exception: SQLiteException) {
            Log.d("TAG", "getUser: ${exception.message}")
        }

        return menu
    }
    fun getItems(): List<BrekfastModel> {
        val db = readableDatabase
        val menu = arrayListOf<BrekfastModel>()
        val query = "SELECT * FROM $BREKFAST_TABLE"
        try {
            val cursor = db.rawQuery(query, null)

            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_BNAME))
                val descerption = cursor.getString(cursor.getColumnIndex(COLUMN_BDESCRPTION))
                val price = cursor.getString(cursor.getColumnIndex(COLUMN_BPRICE))

                val menumodel = BrekfastModel(name , descerption,price)

                menu.add(menumodel)
            }
        } catch (exception: SQLiteException) {
            Log.d("exception", "getUsers: ${exception.message}")
        }

        return menu
    }

    fun insertBrekfast(BrekfastMenu: BrekfastModel) {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_BNAME, BrekfastMenu.name)
        values.put(COLUMN_BDESCRPTION, BrekfastMenu.descrption)
        values.put(COLUMN_BPRICE, BrekfastMenu.price)

        db.insert(BREKFAST_TABLE, null, values)
    }

    fun insertLunch(LunchModel: LunchModel) {
        val db = writableDatabase
        val values = ContentValues()

        values.put(COLUMN_LNAME, LunchModel.name)
        values.put(COLUMN_LDESCRPTION, LunchModel.descrption)
        values.put(COLUMN_LPRICE, LunchModel.price)

        db.insert(LUNCH_TABLE, null, values)
    }

    private companion object {
        // database info
        const val DATABASE_NAME = "project_database"
        const val DATABASE_VERSION = 1

        // brekfast table info
        const val BREKFAST_TABLE = "brekfast_table"
        const val COLUMN_BDESCRPTION = "bdescrption"
        const val COLUMN_BNAME = "bname"
        const val COLUMN_BPRICE ="bprice"

        // lunch table info
        const val LUNCH_TABLE = "lunch_table"
        const val COLUMN_LDESCRPTION = "ldescrption"
        const val COLUMN_LNAME = "lname"
        const val COLUMN_LPRICE ="lprice"
    }
}