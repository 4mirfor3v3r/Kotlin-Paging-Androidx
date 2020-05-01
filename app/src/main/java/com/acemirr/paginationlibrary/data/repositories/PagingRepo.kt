package com.acemirr.paginationlibrary.data.repositories

import android.content.Context
import com.acemirr.paginationlibrary.data.model.Response
import com.acemirr.paginationlibrary.data.network.Network
import com.acemirr.paginationlibrary.data.network.NetworkConfig
import kotlinx.coroutines.CoroutineScope

class PagingRepo(coroutineScope: CoroutineScope) {
    private val network = Network(coroutineScope)

    fun getPagedNews(context: Context, page:Int, pageSize:Int, onSuccess: (Response?) -> Unit, onFinally: (Boolean) -> Unit){
        network.request(context,{
            NetworkConfig.api().getAllNews(page, pageSize)
        },{
            onSuccess(it)
        },{
            onFinally(it)
        })
    }
}