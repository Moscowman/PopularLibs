package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.Scheduler
import moxy.MvpPresenter
import ru.varasoft.popularlibs.IScreens
import ru.varasoft.popularlibs.IUserListPresenter
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.data.user.model.IGithubUsersRepo

class UsersPresenter(val uiScheduler: Scheduler, val usersRepo: IGithubUsersRepo, val router: Router, val screens: IScreens) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            user.reposUrl?.let {router.navigateTo(screens.repos(it))}
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
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
