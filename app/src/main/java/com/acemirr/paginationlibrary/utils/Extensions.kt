package com.acemirr.paginationlibrary.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.acemirr.paginationlibrary.BuildConfig

fun logDebug(message: String) {
    if (BuildConfig.DEBUG) Log.d(Constants.TAG_DEBUG, message)
}

fun logError(message: String) {
    if (BuildConfig.DEBUG) Log.e(Constants.TAG_ERROR, message)
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}