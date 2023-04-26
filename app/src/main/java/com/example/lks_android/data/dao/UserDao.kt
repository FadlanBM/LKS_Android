package com.example.lks_android.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lks_android.data.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE email=:email AND password=:password")
    fun getUser(email:String,password:String):User

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}