package com.test.data.mapper

interface BaseMapper<From, To> {

    fun map(from: From): To

    fun reverseMap(to: To): From

    fun map(from: List<From>): List<To>

    fun reverseMap(to: List<To>): List<From>


    abstract class Mapper<From, To> : BaseMapper<From, To> {

        override fun map(from: List<From>): List<To> = from.map { map(it) }

        override fun reverseMap(to: List<To>): List<From> = to.map { reverseMap(it) }

    }

    abstract class PathMapper<From, To> : BaseMapper.Mapper<From, To>() {

        protected fun getImagePath(
            base: String,
            server: String,
            id: String,
            secret: String,
            sizeSuffix: String = "q",
        ): String = "$base${server}/${id}_${secret}_${sizeSuffix}.jpg"

    }

}