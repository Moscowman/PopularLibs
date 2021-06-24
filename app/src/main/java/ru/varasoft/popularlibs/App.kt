package ru.varasoft.popularlibs

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.varasoft.popularlibs.data.user.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        Database.create(this)
    }
}