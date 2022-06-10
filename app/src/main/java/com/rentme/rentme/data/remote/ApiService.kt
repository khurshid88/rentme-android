package com.rentme.rentme.data.remote

import com.rentme.rentme.model.FileResponse
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.UploadAdvertisement
import com.rentme.rentme.model.UploadAdvertisementResp
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseObject
import okhttp3.MultipartBody
import retrofit2.http.*
import java.io.File

interface ApiService {

    companion object {
        const val Access_key: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrOTk4OTk1NTQ1ODUyIiwicm9sZXMiOltdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXBpL2xvZ2luIiwiZXhwIjoxNjU2NDk3NTcyfQ.05buQg4U3sUjrvwYn7pVW98s__zbDp8ftpeD967-2ts"
        const val key: String = "Authorization"
    }

    @POST("main-page")
    suspend fun getMainDetails(@Body count: Int): BaseResponse<BaseResponseObject<MainPage>>

    @Headers("$key: $Access_key")
    @POST("/advertisement/create")
    suspend fun createAdvertisement(@Body advertisement: UploadAdvertisement) : UploadAdvertisementResp

    @Headers("$key: $Access_key")
    @Multipart
    @POST("/file")
    suspend fun createFile(@Part("file") files: List<MultipartBody.Part>) : FileResponse

}