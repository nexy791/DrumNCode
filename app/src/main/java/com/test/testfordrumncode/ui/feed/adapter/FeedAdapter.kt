package com.test.testfordrumncode.ui.feed.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.test.common.factory.DiffFactory
import com.test.domain.model.FeedImageModel

class FeedAdapter(
    private val callback: Callback,
) : ListAdapter<FeedImageModel, FeedHolder>(DiffFactory.create { old, new -> old.id == new.id }) {

    fun interface Callback {
        fun onImageClick(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder =
        FeedHolder.create(parent)

    override fun onBindViewHolder(holder: FeedHolder, position: Int) =
        holder.bind(getItem(position), callback)

}