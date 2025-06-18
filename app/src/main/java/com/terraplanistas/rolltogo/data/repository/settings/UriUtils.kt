package com.terraplanistas.rolltogo.data.repository.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream

object UriUtils {
    fun persistableUriFromPicker(context: Context, uri: Uri): Uri? {
        try {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            return uri
        } catch (e: SecurityException) {
            Log.e("UriUtils", "No se pudo tomar persistencia del URI", e)
        }
        return null
    }



}