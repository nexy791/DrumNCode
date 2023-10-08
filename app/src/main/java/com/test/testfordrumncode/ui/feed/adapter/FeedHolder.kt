package com.test.testfordrumncode.ui.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.domain.model.FeedImageModel
import com.test.testfordrumncode.databinding.ItemFeedBinding

class FeedHolder(private val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FeedImageModel, callback: FeedAdapter.Callback): Unit = with(binding) {
        root.setOnClickListener { callback.onImageClick(item.id) }
        tvTitle.text = item.title
        ivImage.load(item.path)
    }

    companion object {

        fun create(parent: ViewGroup): FeedHolder {
            val binding =
                ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FeedHolder(binding)
        }
    }

}