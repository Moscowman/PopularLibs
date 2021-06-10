package ru.varasoft.popularlibs.presentation.Users

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.varasoft.popularlibs.IScreens
import ru.varasoft.popularlibs.IUserListPresenter
import ru.varasoft.popularlibs.data.user.UserRepository
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.presentation.User.UserItemView

class UsersPresenter(val usersRepo: UserRepository, val router: Router, val screens: IScreens) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val login = itemView.getLogin()
            for (item in usersRepo.fetchUsers()) {
                if (item.login == login) {
                    router.navigateTo(screens.user(item.login))
                    break
                }
            }
        }
    }

    fun loadData() {
        val users = usersRepo.fetchUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
