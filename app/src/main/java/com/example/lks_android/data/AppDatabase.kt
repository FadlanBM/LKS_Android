package com.example.lks_android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lks_android.data.dao.UserDao
import com.example.lks_android.data.entity.User

@Database(entities = [User::class], version =1)
abstract class AppDatabase :RoomDatabase()  {
    abstract fun userDao():UserDao

    companion object{
        private var instance:AppDatabase?=null

        fun getInstance(context:Context):AppDatabase{
            if (instance==null){
                instance=Room.databaseBuilder(context,AppDatabase::class.java,"app_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}