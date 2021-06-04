package com.yourpackagename

interface NextUInterface {
    fun onDownloadFinished(downloadPath: String)
    fun onProgress(progress : Int)
    fun onDownloadError(message : String)
}
