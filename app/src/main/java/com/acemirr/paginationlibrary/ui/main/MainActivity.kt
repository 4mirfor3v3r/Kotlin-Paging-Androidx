package com.acemirr.paginationlibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acemirr.paginationlibrary.R
import com.acemirr.paginationlibrary.ui.main.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}
