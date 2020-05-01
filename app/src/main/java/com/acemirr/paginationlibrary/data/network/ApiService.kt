package com.acemirr.paginationlibrary.data.network

import com.acemirr.paginationlibrary.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?q=sports&apiKey=b0b90bff982042f6a8e03fb6430dbc50")
    suspend fun getAllNews(@Query("page") page:Int,@Query("pageSize") pageSize:Int):retrofit2.Response<Response>
}