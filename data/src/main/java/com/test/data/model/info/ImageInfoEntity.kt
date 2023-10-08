package com.test.data.model.info

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageInfoEntity(
    val title: String,
    val secret: String,
    val server: String,
    val dateUploaded: String,
    val ownerUsername: String,
    val ownerRealName: String,
    val description: String,
    val views: String,
    val urlOriginal: String,

    @PrimaryKey(autoGenerate = false) val id: String,
)