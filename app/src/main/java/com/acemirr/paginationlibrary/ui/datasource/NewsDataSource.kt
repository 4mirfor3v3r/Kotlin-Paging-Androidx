package com.acemirr.paginationlibrary.ui.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.acemirr.paginationlibrary.data.model.News
import com.acemirr.paginationlibrary.data.repositories.PagingRepo
import com.acemirr.paginationlibrary.utils.LoadingState

class NewsDataSource(private val ctx:Context, private val pagingRepo: PagingRepo):PageKeyedDataSource<Int,News>() {

    var state = MutableLiveData<LoadingState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        setState(LoadingState.LOADING)
        pagingRepo.getPagedNews(ctx, 1,params.requestedLoadSize,{
            if (it != null) {
                callback.onResult(it.articles,null,2)
            }
        },{
            setState(LoadingState.DONE)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        setState(LoadingState.LOADING)
        pagingRepo.getPagedNews(ctx,params.key,params.requestedLoadSize,{
            if (it != null){
                callback.onResult(it.articles,params.key + 1)
            }
        },{
            setState(LoadingState.DONE)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }

    private fun setState(state: LoadingState) {
        this.state.postValue(state)
    }
}