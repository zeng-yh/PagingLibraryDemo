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

        // Step 1: define data source
        // Step 2: create data source factory for LivePagedListBuilder
        val type = intent.getStringExtra(DATA_TYPE)
        val factory = if (ITEM_KEYED == type) MyItemKeyedDataSourceFactory() else MyPageKeyedDataSourceFactory()
        title = type

        // Setp 3: define a DiffUtil.ItemCallback
        // Step 3.1: define PagedListAdapter and set to RecyclerView
        adapter = BookPagedListAdapter(DIFF_CALLBACK)
        rvBookList.layoutManager = LinearLayoutManager(this)
        rvBookList.adapter = adapter

        // Step 4: build PagedList.Config
        // Step 4.1: config LivePagedListBuilder using elements created before
        val listBuilder = LivePagedListBuilder<Long, Book>(factory, PAGED_LIST_CONFIG)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
        val list = listBuilder.build()

        // Step 5: register observer and listener
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
