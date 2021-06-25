package ru.varasoft.popularlibs

import dagger.Component
import ru.varasoft.popularlibs.presentation.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        SchedulerModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)

    fun inject(reposPresenter: ReposPresenter)

    fun inject(repoPresenter: RepoPresenter)
}