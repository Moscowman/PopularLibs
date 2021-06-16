package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo

class UserPresenter(
    private val user: GithubUser,
    private val router: Router,
    private val userRepository: IGithubUsersRepo
) : MvpPresenter<UserView>() {

    lateinit var userDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        var user: GithubUser? = null
        userDisposable = userRepository.getUsers(this.user)
            .subscribe({ s ->
                user = s
            },
                {
                    println("onError: ${it.message}")
                })
        user
            ?.login
            ?.let(viewState::setLogin)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        userDisposable.dispose()
    }
}
