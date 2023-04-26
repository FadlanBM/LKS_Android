package com.example.lks_android

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME="LKS"
        private const val DATABASE_VERSION=1
        private const val TABLE_USER="user"
        private const val COLUMN_USER_ID="user_id"
        private const val COLUMN_USER_NAME="name"
        private const val COLUMN_USER_EMAIL="username"
        private const val COLUMN_USER_PASSWORD="password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USER_TABLE=("CREATE TABLE"+ TABLE_USER+"("
                + COLUMN_USER_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME+"TEXT,"
                + COLUMN_USER_EMAIL+"TEXT,"
                + COLUMN_USER_PASSWORD+"TEXT"+")")
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS"+ TABLE_USER)
        onCreate(db)
    }

    fun addUser(name:String,username:String,password:String){
        val db=this.writableDatabase
        val values=ContentValues()
        values.put(COLUMN_USER_NAME,name)
        values.put(COLUMN_USER_EMAIL,username)
        values.put(COLUMN_USER_PASSWORD,password)
        db.insert(TABLE_USER,null,values)
        db.close()
    }

    fun checkUser(email: String,password: String):Boolean{
        val column= arrayOf(COLUMN_USER_ID)
        val db=this.readableDatabase
        val selection="$COLUMN_USER_NAME =?AND $COLUMN_USER_EMAIL =?AND $COLUMN_USER_PASSWORD =?"
        val selectionArgs= arrayOf(email,password)
        val cursor=db.query(
            TABLE_USER,column,selection,selectionArgs,null,null,null)
        val count=cursor.count
        cursor.close()
        db.close()
        return count>0
    }

}