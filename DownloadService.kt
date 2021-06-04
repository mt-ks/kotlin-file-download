package com.yourpackagename

import android.content.Context
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import kotlinx.coroutines.*
import java.io.*
import java.net.URL

class DownloadService(private val routeUrl : String, 
                      private val downloadName : String,
                      private val context: Context,
                      private val nextUInterface: NextUInterface) {

    fun download()
    {

        CoroutineScope(Dispatchers.IO).launch {
            downloadRequest()
        }
    }

    private fun downloadRequest()
    {
        try{

            val path = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + downloadName


            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val url = URL(routeUrl)
            val connection = url.openConnection()
            connection.connect()
            val fileLength : Int = connection.contentLength

            val input : InputStream  = BufferedInputStream(url.openStream())
            val output : OutputStream = FileOutputStream(path)


            val data = ByteArray(2048)
            var total: Long = 0
            var count: Int
            while (input.read(data).also { count = it } != -1) {
                total += count.toLong()
                nextUInterface.onProgress((total * 100 / fileLength).toInt())
                output.write(data, 0, count)
            }
            output.flush()
            output.close()
            input.close()
            nextUInterface.onDownloadFinished(path)
        }catch (e : Exception)
        {
            e.printStackTrace()
            nextUInterface.onDownloadError(e.message?:"Download Error")
        }
    }




}
