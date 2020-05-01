package com.acemirr.paginationlibrary.ui.main.ui.main

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.acemirr.paginationlibrary.data.model.News
import com.acemirr.paginationlibrary.data.repositories.PagingRepo
import com.acemirr.paginationlibrary.ui.datasource.NewsDataSource
import com.acemirr.paginationlibrary.ui.datasource.NewsDataSourceFactory
import com.acemirr.paginationlibrary.utils.LoadingState

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val pagingRepo = PagingRepo(viewModelScope)
    private var newsDataSourceFactory: NewsDataSourceFactory = NewsDataSourceFactory(application.applicationContext,pagingRepo)
    var pagingList: LiveData<PagedList<News>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        pagingList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }

//    if you want a loading state
    fun getLoadingState(): LiveData<LoadingState> {
        return Transformations.switchMap(newsDataSourceFactory.newsDataSourceLiveData, NewsDataSource::state)
    }
}
