package ru.varasoft.popularlibs

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.varasoft.popularlibs.data.user.Database
import ru.varasoft.popularlibs.data.user.model.IGithubReposCache
import ru.varasoft.popularlibs.data.user.model.IGithubUsersCache
import ru.varasoft.popularlibs.data.user.model.RoomGithubReposCache
import ru.varasoft.popularlibs.data.user.model.RoomGithubUsersCache
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java,
        Database.DB_NAME
    )
        .build()


    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun reposCache(database: Database): IGithubReposCache = RoomGithubReposCache(database)

}