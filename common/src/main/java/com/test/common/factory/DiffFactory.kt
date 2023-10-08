package com.test.common.factory

import androidx.recyclerview.widget.DiffUtil

// Object to create DiffUtil.ItemCallback
object DiffFactory {

    fun interface ContentComparator<T> {
        fun areContentsTheSame(oldItem: T, newItem: T): Boolean
    }

    fun <T : Any> create(contentComparator: ContentComparator<T>) =
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
                contentComparator.areContentsTheSame(oldItem, newItem)
        }

}