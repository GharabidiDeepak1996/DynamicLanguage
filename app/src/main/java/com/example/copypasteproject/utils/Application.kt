package com.example.copypasteproject.utils

import android.content.Context
import androidx.multidex.MultiDexApplication

class Application : MultiDexApplication() {

    companion object{


        lateinit  var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Application.appContext = applicationContext
    }





}