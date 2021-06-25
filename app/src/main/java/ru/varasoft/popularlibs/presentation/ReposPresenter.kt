package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import moxy.MvpPresenter
import ru.varasoft.popularlibs.IRepoListPresenter
import ru.varasoft.popularlibs.IScreens
import ru.varasoft.popularlibs.data.user.model.GithubRepoDescription
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo
import javax.inject.Inject

class ReposPresenter(
    val userId: String
) :
    MvpPresenter<ReposView>() {

    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var repo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens


    class ReposListPresenter : IRepoListPresenter {
        val repos = mutableListOf<GithubRepoDescription>()
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
        repo.getRepos(userLogin)
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
