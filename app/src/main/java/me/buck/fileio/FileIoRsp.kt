package me.buck.fileio

/**
 * Created by gwf on 2019/5/30
 */

data class FileIoRsp(
    val expiry: String,
    val key: String,
    val link: String,
    val success: Boolean
)