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
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)

    //При выполнении практического задания это должно отсюда уйти
    fun inject(userFragment: UserFragment)
    fun inject(reposFragment: ReposFragment)
    fun inject(repoFragment: RepoFragment)
    fun inject(usersFragment: UsersFragment)
}