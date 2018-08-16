package com.zengyuhao.paginglibrarydemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zengyuhao.paginglibrarydemo.R
import com.zengyuhao.paginglibrarydemo.vo.Book
import java.text.SimpleDateFormat
import java.util.*

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRENCH)
        fun create(parent: ViewGroup): BookViewHolder {
            return BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false))
        }
    }

    val txtId = itemView.findViewById(R.id.txtBookId) as TextView
    val txtName = itemView.findViewById(R.id.txtBookName) as TextView
    val txtSource = itemView.findViewById(R.id.txtBookSource) as TextView
    val txtAuthor = itemView.findViewById(R.id.txtBookAuthor) as TextView
    val txtPublishDate = itemView.findViewById(R.id.txtBookPublishDate) as TextView
    val txtIntro = itemView.findViewById(R.id.txtBookIntroduction) as TextView

    fun bind(book: Book?) {
        if (null != book) {
            txtId.text = book.id.toString()
            txtName.text = book.name
            txtSource.text = book.source
            txtAuthor.text = book.author
            txtPublishDate.text = dateFormatter.format(book.publishDate)
            txtIntro.text = book.introduction
        } else {
            txtId.text = "??"
            txtName.text = "??"
            txtSource.text = "??"
            txtAuthor.text = "??"
            txtPublishDate.text = "??"
            txtIntro.text = "??"
        }
    }
}