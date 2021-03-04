package com.vishal.mjapp.baseFiles

import android.app.Dialog
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.vishal.mjapp.R

open class BaseActivity: AppCompatActivity() {
    lateinit var progressDialog: Dialog

    fun showProgress() {
        // Disable onClick on entire screen
        window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        progressDialog = Dialog(this)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setCancelable(false)
        progressDialog.setContentView(R.layout.dialog_loading)
        progressDialog.show()
    }

    fun dismissProgress() {
        // Enable onClick on entire screen
        try {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            progressDialog.dismiss()
        } catch ( e : Exception){}
    }
}