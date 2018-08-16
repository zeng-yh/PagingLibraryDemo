package com.zengyuhao.paginglibrarydemo.paging.itemkeyed

import androidx.paging.ItemKeyedDataSource
import com.zengyuhao.paginglibrarydemo.paging.NetworkBookRepoEmulator
import com.zengyuhao.paginglibrarydemo.vo.Book

class BookItemkedDataSource : ItemKeyedDataSource<Long, Book>() {
    private val bookRepo = NetworkBookRepoEmulator(1500, 0.3f, 0f)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Book>) {
        // params.requestedInitialKey may be a hint, it's ignorable if we want restart at the beginning all the time.
        val books = bookRepo.getBooks(0, params.requestedLoadSize)
        if (null != books) {
            books.forEach {
                it.introduction = "loadInitial | initialKey=${params.requestedInitialKey}, loadSize=${params.requestedLoadSize} "
            }
            callback.onResult(books)
        } else {
            // retry
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Book>) {
        // data at position [params.key] is not included
        val books = bookRepo.getBooks(params.key + 1, params.requestedLoadSize)
        if (null != books) {
            books.forEach {
                it.introduction = "loadAfter | item=${params.key}, loadSize=${params.requestedLoadSize}"
            }
            callback.onResult(books)
        } else {
            // retry
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Book>) {
        // Normally, prepending is not needed for most lists, but code is also given here as an example.

//        val books = bookRepo.getBooks(params.key - 1, params.requestedLoadSize)
//        if (null != books) {
//            books.forEach {
//                it.introduction = "loadBefore | key=${params.key}, loadSize=${params.requestedLoadSize}"
//            }
//            // reverse the list.
//            callback.onResult(books.reversed())
//        } else {
//            // retry
//        }
    }

    override fun getKey(item: Book): Long {
        return item.id
    }
}