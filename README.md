# Kotlin APK/Files Download
Download APK or other files without google play

#### Implement Coroutines
```
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0'
```

#### Android Permissions STEP 1
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

#### Create File Provider
```xml
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/filepaths"/>
        </provider>
```
#### @xml/filepaths
```xml 
<?xml version="1.0" encoding="utf-8"?>
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path name="external_download" path="/"/>
</paths>
```

#### How to download apk
```kotlin
(DownloadService("https://yourservice.com/app.apk","app_1_2.apl",context,listener).download())

override fun onProgress(progress: Int) {
}

override fun onDownloadError(message: String) {
}

override fun onDownloadFinished(downloadPath: String) {
}
```
