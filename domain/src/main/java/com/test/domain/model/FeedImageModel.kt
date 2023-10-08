package com.test.domain.model

interface FeedImageModel {
    val id: String
    val title: String
    val path: String
    val server: String
    val secret: String

    class Base(
        override val id: String,
        override val title: String,
        override val path: String,
        override val server: String,
        override val secret: String,
    ) : FeedImageModel

}