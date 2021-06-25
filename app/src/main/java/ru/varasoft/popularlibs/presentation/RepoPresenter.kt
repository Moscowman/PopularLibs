package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.varasoft.popularlibs.data.user.model.GithubRepoDescription
import javax.inject.Inject

class RepoPresenter(val repoDescription: GithubRepoDescription?) : MvpPresenter<RepoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        repoDescription?.apply {
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
}
