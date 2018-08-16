package com.zengyuhao.paginglibrarydemo.paging

import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.zengyuhao.paginglibrarydemo.vo.Book

val PAGE_SIZE = 20

val PAGED_LIST_CONFIG = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPrefetchDistance(PAGE_SIZE * 2 / 3)
        .setPageSize(PAGE_SIZE)
        .build()

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}