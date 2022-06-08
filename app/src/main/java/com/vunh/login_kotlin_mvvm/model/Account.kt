package com.vunh.login_kotlin_mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_Account")
data class Account(
    @SerializedName("username")
    var username: String,
    @SerializedName("password")
    var password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

