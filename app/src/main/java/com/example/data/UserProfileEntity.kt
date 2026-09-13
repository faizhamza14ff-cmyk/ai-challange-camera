package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val username: String = "alex",
    val xp: Int = 12400,
    val coins: Int = 2450,
    val challenges: Int = 127,
    val wins: Int = 83,
    val bestScore: Int = 99,
    val streak: Int = 7,
    val isPro: Boolean = false
)
