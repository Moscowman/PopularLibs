package ru.varasoft.popularlibs

import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}
