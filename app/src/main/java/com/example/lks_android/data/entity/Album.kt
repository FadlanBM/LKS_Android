package com.example.lks_android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album (
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    var title:String,
    var description:String,
    var image:String,
    )