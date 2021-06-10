package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val userId: String,
    private val router: Router,
    private val userRepository: UserRepository
) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        userRepository
            .fetchUserById(userId)
            ?.login
            ?.let(viewState::setLogin)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
