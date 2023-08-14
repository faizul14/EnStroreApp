package com.faezolfp.enstoreapp.core.utils

import java.io.File

object ProvideImage {

    fun deleteImageByPath(imgPath: String): String {
        var result: String? = ""
        val imgFile = File(imgPath)
        if (imgFile.exists()) {
            val delete = imgFile.delete()
            if (delete) {
                result = "Succes"
            } else {
                result = "Failed"
            }
        }
        return result ?: ""
    }
}