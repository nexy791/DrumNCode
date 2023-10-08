package com.test.domain.model

interface InfoImageModel {
    val title: String
    val id: String
    val dateUploaded: String
    val ownerUsername: String
    val ownerRealName: String
    val description: String
    val views: String
    val urlOriginal: String
    val path: String

    val server: String
    val secret: String

    class Base(
        override val title: String,
        override val id: String,
        override val dateUploaded: String,
        override val ownerUsername: String,
        override val ownerRealName: String,
        override val description: String,
        override val views: String,
        override val urlOriginal: String,
        override val path: String,
        override val server: String,
        override val secret: String,
    ) : InfoImageModel

}