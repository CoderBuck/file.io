package me.buck.fileio

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.buck.fileio.databinding.ActivityMainBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var filePath: String
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val rootDir = Environment.getExternalStorageDirectory()
        filePath = rootDir.absolutePath + "/AndTools/123.png"

        binding.uploadBtn.setOnClickListener {
            println("click")
            upload()
        }

        binding.downloadBtn.setOnClickListener {
            download()
        }
    }

    private fun upload() {
        val retrofit = Retrofit.Builder()
            .baseUrl(FileIoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val fileIoApi = retrofit.create(FileIoApi::class.java)

        val file = File(filePath)
        if (!file.exists()) {
            println("文件不存在")
        }
        println(file.length())


        val fileRQ = RequestBody.create(MediaType.parse("file/*"), file)
        val part = MultipartBody.Part.createFormData("file", "123.png", fileRQ)

        val uploadCall = fileIoApi.upload(part)
        uploadCall.enqueue(object : Callback<FileIoRsp> {
            override fun onFailure(call: Call<FileIoRsp>, t: Throwable) {
                println("onFailure")
            }

            override fun onResponse(call: Call<FileIoRsp>, response: Response<FileIoRsp>) {
                println("onResponse")
                val body = response.body()
                println(body)

                body?.let {
                    binding.fileNameTv.text = body.key
                    binding.httpUrlTv.text = body.link
                }
            }
        })
    }

    private fun download() {
        val retrofit = Retrofit.Builder()
            .baseUrl(FileIoApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val fileIoApi = retrofit.create(FileIoApi::class.java)

        val file = File(filePath).parentFile
        if (!file.exists()) {
            println("文件不存在")
        }

        val downloadCall = fileIoApi.download(binding.keyEt.text.toString())
        downloadCall.enqueue(object: Callback<ResponseBody?> {
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Timber.i("downloadCall : onFailure")
            }

            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Timber.i("downloadCall : onResponse")

                val toString = response.toString()
                Timber.i(toString)
                response.isSuccessful
            }
        })
    }
}
