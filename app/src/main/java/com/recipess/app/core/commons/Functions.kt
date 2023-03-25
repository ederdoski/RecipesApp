package com.adrenastudies.trufisapp.core.commons

import android.app.Activity
import android.content.*
import android.net.Uri
import java.io.IOException


class Functions {

    companion object {

        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun copyText(activity: Activity, text: String) {
            val clipboardManager =
                activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", text)
            clipboardManager.setPrimaryClip(clipData)
        }

        fun shareText(context: Context, text: String) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }

        fun shareImage(context: Context, path: String) {
            val imageUri = Uri.parse(path)
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
            shareIntent.type = "image/jpeg"
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val shareIntentt = Intent.createChooser(shareIntent, "send")
            context.startActivity(shareIntentt)
        }

        fun shareArticle(context: Context, path: String, text:String) {
            val imageUri = Uri.parse(path)
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, text)
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
            shareIntent.type = "image/jpeg"
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val shareIntentt = Intent.createChooser(shareIntent, "send")
            context.startActivity(shareIntentt)
        }


    }
}