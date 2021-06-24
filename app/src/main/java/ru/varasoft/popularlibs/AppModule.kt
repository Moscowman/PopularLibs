package ru.varasoft.popularlibs

import dagger.Module
import dagger.Provides
import ru.varasoft.popularlibs.App

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}
