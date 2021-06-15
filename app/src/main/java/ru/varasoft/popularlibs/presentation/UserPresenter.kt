package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.varasoft.popularlibs.data.user.UserRepository
import ru.varasoft.popularlibs.data.user.model.GithubUser

class UserPresenter(
    private val userId: String,
    private val router: Router,
    private val userRepository: UserRepository
) : MvpPresenter<UserView>() {

    lateinit var userDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        var user: GithubUser? = null
        userDisposable = userRepository.fetchUserById(userId)
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
