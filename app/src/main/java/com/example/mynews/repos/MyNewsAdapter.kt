/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.mynews.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynews.databinding.NewItemLayoutBinding
import com.example.mynews.models.NewsAnswer

class MyNewsAdapter( val onClickListener: OnClickListener ) :
    ListAdapter<NewsAnswer, MyNewsAdapter.RepoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holderRepo: RepoViewHolder, position: Int) {
        val myNewsItem = getItem(position)
        holderRepo.itemView.setOnClickListener {
            onClickListener.onClick(myNewsItem)
        }
        holderRepo.bind(myNewsItem)
    }

    class RepoViewHolder(private var binding: NewItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myNewsItem: NewsAnswer) {
            binding.myNewsItem = myNewsItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : RepoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewItemLayoutBinding.inflate(layoutInflater, parent, false)

                return RepoViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NewsAnswer>() {
        override fun areItemsTheSame(oldItem: NewsAnswer, newItem: NewsAnswer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsAnswer, newItem: NewsAnswer): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (myNewsItem: NewsAnswer) -> Unit) {
        fun onClick(myNewsItem: NewsAnswer) = clickListener(myNewsItem)
    }
}