package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.varasoft.popularlibs.IRepoListPresenter
import ru.varasoft.popularlibs.IScreens
import ru.varasoft.popularlibs.data.user.model.GithubRepo
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo

class ReposPresenter(
    val userId: String,
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screens: IScreens
) :
    MvpPresenter<ReposView>() {
    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GithubRepo>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            repo?.let { it.name?.let { it1 -> view.setName(it1) } }
        }
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData(userId)

        reposListPresenter.itemClickListener = { itemView ->
            val repo = reposListPresenter.repos[itemView.pos]
            router.navigateTo(screens.repo(repo))
        }
    }

    fun loadData(userLogin: String) {
        usersRepo.getRepos(userLogin)
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                reposListPresenter.repos.clear()
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
