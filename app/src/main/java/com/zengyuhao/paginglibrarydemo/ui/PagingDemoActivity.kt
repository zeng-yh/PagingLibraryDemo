package com.zengyuhao.paginglibrarydemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.zengyuhao.paginglibrarydemo.paging.DIFF_CALLBACK
import com.zengyuhao.paginglibrarydemo.paging.PAGED_LIST_CONFIG
import com.zengyuhao.paginglibrarydemo.R
import com.zengyuhao.paginglibrarydemo.paging.BookPagedListAdapter
import com.zengyuhao.paginglibrarydemo.paging.itemkeyed.MyItemKeyedDataSourceFactory
import com.zengyuhao.paginglibrarydemo.paging.pagekeyed.MyPageKeyedDataSourceFactory
import com.zengyuhao.paginglibrarydemo.vo.Book
import kotlinx.android.synthetic.main.activity_paging_demo.*
import java.util.concurrent.Executors

class PagingDemoActivity : AppCompatActivity() {
    companion object {
        const val DATA_TYPE = "PagingDemoActivity.Type"
        const val PAGE_KEYED = "PageKeyedDataSource"
        const val ITEM_KEYED = "ItemKeyedDataSource"
    }

    private lateinit var adapter: BookPagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_demo)

        adapter = BookPagedListAdapter(DIFF_CALLBACK)
        rvBookList.layoutManager = LinearLayoutManager(this)
        rvBookList.adapter = adapter

        val type = intent.getStringExtra(DATA_TYPE)
        val factory = if (ITEM_KEYED == type) MyItemKeyedDataSourceFactory() else MyPageKeyedDataSourceFactory()
        title = type

        val listBuilder = LivePagedListBuilder<Long, Book>(factory, PAGED_LIST_CONFIG)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
        val list = listBuilder.build()

        swipeRefreshLayout.setOnRefreshListener {
            // invalidate the data liveDataSource will trigger the list to fetch new data
            factory.liveDataSource.value?.invalidate()
        }

        list.observe(this, Observer {
            // new data (pagedList) have came
            adapter.submitList(it)
            swipeRefreshLayout.isRefreshing = false
        })
    }

}
