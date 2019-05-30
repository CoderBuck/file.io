package me.buck.fileio

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by gwf on 2019/5/30
 */
interface FileIoApi {

    companion object {
        const val BASE_URL = "https://file.io/"
    }

    @Multipart
    @POST("/")
    fun upload(@Part body: MultipartBody.Part): Call<FileIoRsp>

}