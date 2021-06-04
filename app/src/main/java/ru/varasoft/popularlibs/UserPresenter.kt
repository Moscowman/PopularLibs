package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(val user: GithubUser, val router: Router) : MvpPresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
