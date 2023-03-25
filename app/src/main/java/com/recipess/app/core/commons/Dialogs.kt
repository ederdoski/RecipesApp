package com.recipess.app.core.commons

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog

class Dialogs {

    abstract class DialogCallback {
        open fun onPositiveClick() {}
        open fun onNegativeClick() {}
    }

    companion object {

        fun makeDialogCancelable(dAlert: AlertDialog){
            dAlert.setCancelable(false)
        }

        fun hideBackground(dAlert:AlertDialog) {
            dAlert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}