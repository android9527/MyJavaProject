package com.okhttp

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.util.*

/**
 * Created by chenfeiyue on 2018/6/5.
 * Description ：kotlin Extension
 */


/**
 * 从HttpURLConnection获取Response Header
 */
fun getResponseHeader(maps: Map<String,List<String>>?): HashMap<String, String> {
    val map = HashMap<String, String>()

    if (maps == null || maps.isEmpty()) {
        return map
    }

    for (entry in maps.entries) {

        val key = entry.key ?: continue

        val values = entry.value as List<String>?
        if (values != null && values.isNotEmpty()) {
            println("key = ${entry.key} value = ${values[0]}")
            map[entry.key] = values[0]
        }
    }
    return map
}

fun InputStream.copy(): InputStream {
    val baos = this.copyToOutputStream()

    // 打开一个新的输入流
    return ByteArrayInputStream(baos.toByteArray())
}

/**
 * InputStream to ByteArrayOutputStream
 */
fun InputStream.copyToOutputStream(): ByteArrayOutputStream {
    val baos = ByteArrayOutputStream()

    val buffer = ByteArray(1024)
    var len: Int = this.read(buffer)
    while (len != -1) {
        baos.write(buffer, 0, len)
        len = this.read(buffer)
    }
    baos.flush()
    return baos
}