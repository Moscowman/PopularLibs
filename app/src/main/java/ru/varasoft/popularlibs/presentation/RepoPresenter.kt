package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.varasoft.popularlibs.data.user.model.GithubRepo
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo

class RepoPresenter(
    private val repo: GithubRepo?,
    private val router: Router,
) : MvpPresenter<RepoView>() {

    lateinit var userDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repo?.apply {
            name?.let(viewState::setName)
            fullName?.let(viewState::setFullName)
            forks?.let(viewState::setNoOfForks)
            watchers?.let(viewState::setNoOfWatchers)
        }
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
