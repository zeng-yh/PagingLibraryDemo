package com.zengyuhao.paginglibrarydemo.paging.pagekeyed

import androidx.paging.PageKeyedDataSource
import com.zengyuhao.paginglibrarydemo.paging.NetworkBookRepoEmulator
import com.zengyuhao.paginglibrarydemo.paging.PAGE_SIZE
import com.zengyuhao.paginglibrarydemo.vo.Book

class BookPageKeyedDataSource : PageKeyedDataSource<Long, Book>() {
    private val bookRepo = NetworkBookRepoEmulator(1500, 0.3f, 0f)

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Book>) {
        val books = bookRepo.getBooks(0, params.requestedLoadSize)
        if (null != books) {
            books.forEach {
                it.introduction = "loadInitial | initialPage= 0, loadSize=${params.requestedLoadSize} "
            }
            callback.onResult(books, -1, 1)
        } else {
            // retry
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Book>) {
        // the meaning of params.key here (inclusive) is different than that of ItemKeyedDataSource (exclusive).
        val currPage = params.key
        val interval = 2 * PAGE_SIZE
        val startPositionOfCurrentPage = currPage * interval
        val books = bookRepo.getBooks(startPositionOfCurrentPage, params.requestedLoadSize)
        if (null != books) {
            books.forEach {
                it.introduction = "loadAfter | page=${currPage}, loadSize=${params.requestedLoadSize}\n"+
                        "interval between 2 pages = $interval"
            }

            callback.onResult(books, currPage + 1)
        } else {
            // retry
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Book>) {
    }
}