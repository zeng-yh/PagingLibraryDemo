package com.zengyuhao.paginglibrarydemo.vo

import java.util.*

data class Book(
        var id: Long,
        var name: String,
        var author: String = "",
        var introduction: String = "",
        var source: String = "unspecified",
        var publishDate: Date = Date()
)