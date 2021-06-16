package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import moxy.MvpPresenter
import ru.varasoft.popularlibs.IRepoListPresenter
import ru.varasoft.popularlibs.IScreens
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo

class ReposPresenter(
    val reposUrl: String,
    val uiScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    val router: Router,
    val screens: IScreens
) :
    MvpPresenter<ReposView>() {
    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<String>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount() = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            repo?.let { view.setName(it) }
        }
    }

    val reposListPresenter = ReposListPresenter()

    lateinit var reposDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData(reposUrl)

        reposListPresenter.itemClickListener = { itemView ->
            val repo = reposListPresenter.repos[itemView.pos]
            //TODO: router.navigateTo(screens.repo(repo))
        }
    }

    fun loadData(reposUrl: String) {
        usersRepo.getRepos(reposUrl)
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
