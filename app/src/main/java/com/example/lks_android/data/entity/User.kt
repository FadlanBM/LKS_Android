package com.example.lks_android.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)var uid:Int?=null,
    @ColumnInfo(name= "full_name")var full_name: String?,
    @ColumnInfo(name = "email")var email: String?,
    @ColumnInfo(name = "password")var password: String?
)