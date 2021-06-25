package ru.varasoft.popularlibs

import dagger.Module
import dagger.Provides
import ru.varasoft.popularlibs.data.user.IDataSource
import ru.varasoft.popularlibs.data.user.INetworkStatus
import ru.varasoft.popularlibs.data.user.model.GithubUserRepository
import ru.varasoft.popularlibs.data.user.model.IGithubReposCache
import ru.varasoft.popularlibs.data.user.model.IGithubUsersCache
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, usersCache: IGithubUsersCache, reposCache: IGithubReposCache): IGithubUsersRepo = GithubUserRepository(api, networkStatus, usersCache, reposCache)

}