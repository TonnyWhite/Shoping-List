package com.example.shopinglist

import android.app.Application
import com.example.shopinglist.di.DaggerApplicationComponent

class ShoppingApp : Application(){

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}