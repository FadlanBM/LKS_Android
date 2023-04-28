package com.example.lks_android.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lks_android.data.entity.Album

@Dao
interface GaleryDao {
    @Insert
    fun insert(album:Album)
    @Update
    fun update(album: Album)
    @Delete
    fun delete(album: Album)
    @Query("SELECT * FROM Album")
    fun getAllAlbum():LiveData<List<Album>>
}