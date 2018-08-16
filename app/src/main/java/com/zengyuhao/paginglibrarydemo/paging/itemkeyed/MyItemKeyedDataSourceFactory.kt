package com.zengyuhao.paginglibrarydemo.paging.itemkeyed

import androidx.paging.DataSource
import com.zengyuhao.paginglibrarydemo.paging.MyDataSourceFactory
import com.zengyuhao.paginglibrarydemo.vo.Book

class MyItemKeyedDataSourceFactory : MyDataSourceFactory() {
    override fun create(): DataSource<Long, Book> {
        val source = BookItemkedDataSource()
        liveDataSource.postValue(source)
        return source
    }
}