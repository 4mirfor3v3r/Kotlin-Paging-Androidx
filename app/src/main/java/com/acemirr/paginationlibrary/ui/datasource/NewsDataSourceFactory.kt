package com.acemirr.paginationlibrary.ui.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.acemirr.paginationlibrary.data.model.News
import com.acemirr.paginationlibrary.data.repositories.PagingRepo

class NewsDataSourceFactory(private val ctx:Context, val pagingRepo: PagingRepo):DataSource.Factory<Int,News>() {
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()
    override fun create(): DataSource<Int, News> {
        val newsDataSource = NewsDataSource(ctx, pagingRepo)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}