package com.test.data.model.feed

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FeedImageEntity(
    val title: String,
    val secret: String,
    val server: String,
    @PrimaryKey(autoGenerate = false) val id: String,
)