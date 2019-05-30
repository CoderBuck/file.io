package me.buck.fileio

import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var filePath: String
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootDir = Environment.getExternalStorageDirectory()
        filePath = rootDir.absolutePath + "/AndTools/微信.apk"

        btn = findViewById(R.id.btn)
        btn.setOnClickListener { upload() }
    }

    fun upload() {
        val retrofit = Retrofit.Builder()
            .baseUrl(FileIoApiKt.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val fileIoApi = retrofit.create(FileIoApiKt::class.java)

        val file = File(filePath)
        if (!file.exists()) {
            println("文件不存在")
        }

        val fileRQ = RequestBody.create(MediaType.parse("file/*"), file)
        val part = MultipartBody.Part.createFormData("file", "wx.apk", fileRQ)

        val uploadCall = fileIoApi.upload(part)
        uploadCall.enqueue(object : Callback<FileIoRsp> {
            override fun onFailure(call: Call<FileIoRsp>, t: Throwable) {
                println("onFailure")
            }

            override fun onResponse(call: Call<FileIoRsp>, response: Response<FileIoRsp>) {
                val body = response.body()
                println(body)
            }
        })



//        fileIoApi.upload()
    }
}
