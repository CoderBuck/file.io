package me.buck.fileio

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

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

    @Streaming
    @GET("/{key}")
    fun download(@Path("key") key: String):Call<ResponseBody>

}