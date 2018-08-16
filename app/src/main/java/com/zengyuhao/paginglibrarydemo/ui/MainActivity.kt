package com.zengyuhao.paginglibrarydemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zengyuhao.paginglibrarydemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnItemKeyedDataSource.setOnClickListener {
            val intent = Intent(this, PagingDemoActivity::class.java)
            intent.putExtra(PagingDemoActivity.DATA_TYPE, PagingDemoActivity.ITEM_KEYED)
            startActivity(intent)
        }

        btnPageKeyedDataSource.setOnClickListener {
            val intent = Intent(this, PagingDemoActivity::class.java)
            intent.putExtra(PagingDemoActivity.DATA_TYPE, PagingDemoActivity.PAGE_KEYED)
            startActivity(intent)
        }
    }
}
