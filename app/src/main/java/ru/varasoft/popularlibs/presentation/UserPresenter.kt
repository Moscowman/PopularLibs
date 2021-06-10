package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.varasoft.popularlibs.data.user.UserRepository
import ru.varasoft.popularlibs.data.user.model.GithubUser

class UserPresenter(
    private val userId: String,
    private val router: Router,
    private val userRepository: UserRepository
) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        var user: GithubUser? = null
        val disposable = userRepository.fetchUserById(userId)
            .subscribe({ s ->
                user = s
            })
        disposable.dispose()
        user
            ?.login
            ?.let(viewState::setLogin)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
