package com.example.mynews

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mygithub.repos.MyNewsAdapter
import com.example.mynews.models.NewsAnswer

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<NewsAnswer>?) {
    val adapter = recyclerView.adapter as MyNewsAdapter
    adapter.submitList(data)
}