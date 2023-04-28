package com.example.lks_android.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lks_android.data.dao.GaleryDao
import com.example.lks_android.data.entity.Album

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class AlbumDatabase:RoomDatabase {
    abstract fun GaleryDao():Album
    companion object{
        @Volatile
        private var INSTANCE: AlbumDatabase?=null

        fun getDatabase(context: Context):AlbumDatabase{
            val tempInstance= INSTANCE
            if (tempInstance==null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDatabase::class.java,
                    "album_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}