package com.zengyuhao.paginglibrarydemo.paging.pagekeyed

import androidx.paging.DataSource
import com.zengyuhao.paginglibrarydemo.paging.MyDataSourceFactory
import com.zengyuhao.paginglibrarydemo.vo.Book

class MyPageKeyedDataSourceFactory : MyDataSourceFactory() {
    override fun create(): DataSource<Long, Book> {
        val source = BookPageKeyedDataSource()
        liveDataSource.postValue(source)
        return source
    }

}