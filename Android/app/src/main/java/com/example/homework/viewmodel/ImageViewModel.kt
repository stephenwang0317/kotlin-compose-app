package com.example.homework.viewmodel

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.homework.model.entity.BaseResponse
import com.example.homework.model.service.ImageService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ImageViewModel {
    var imageUri by mutableStateOf<Uri?>(null)

    var loading by mutableStateOf(false)
        private set

    private val imageService = ImageService.instance()

    suspend fun uploadImage(uri: Uri? = null, context: Context, user_id: Int): BaseResponse {
        loading = true

        if (uri != null) {
//            val file = uri.path?.let { File(it) }
            val path = getFilePath(context = context, uri = uri)
            Log.i("=========", path.toString())
            val fileBody: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), File(path))
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("img", path, fileBody)

            val requestBody: RequestBody = builder.build()
            val res = imageService.uploadImage(requestBody, user_id)
            Log.i("============res", res.toString())
            return res
        }
        return BaseResponse(-1,"无效的图片地址")
    }
}

fun getFilePath(context: Context, uri: Uri): String? {
    try {
        val returnCursor: Cursor? =
            context.getContentResolver().query(uri, null, null, null, null)
        val nameIndex: Int = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name: String = returnCursor.getString(nameIndex)
        val file = File(context.getFilesDir(), name)
        val inputStream: InputStream? = context.getContentResolver().openInputStream(uri)
        val outputStream = FileOutputStream(file)
        var read = 0
        val maxBufferSize = 1 * 1024 * 1024
        val bytesAvailable: Int = inputStream!!.available()
        val bufferSize = Math.min(bytesAvailable, maxBufferSize)
        val buffers = ByteArray(bufferSize)
        while (inputStream!!.read(buffers).also { read = it } != -1) {
            outputStream.write(buffers, 0, read)
        }
        returnCursor.close()
        inputStream.close()
        outputStream.close()
        return file.getPath()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}