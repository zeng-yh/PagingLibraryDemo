package com.zengyuhao.paginglibrarydemo.paging

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zengyuhao.paginglibrarydemo.ui.BookViewHolder
import com.zengyuhao.paginglibrarydemo.vo.Book

class BookPagedListAdapter(diffCallback: DiffUtil.ItemCallback<Book>)
    : PagedListAdapter<Book, BookViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}