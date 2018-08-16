package com.zengyuhao.paginglibrarydemo.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.zengyuhao.paginglibrarydemo.vo.Book

abstract class MyDataSourceFactory : DataSource.Factory<Long, Book>() {
    val liveDataSource = MutableLiveData<DataSource<Long, Book>>()
}