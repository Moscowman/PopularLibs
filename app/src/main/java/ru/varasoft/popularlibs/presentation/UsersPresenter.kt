package ru.varasoft.popularlibs.presentation

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.varasoft.popularlibs.IScreens
import ru.varasoft.popularlibs.IUserListPresenter
import ru.varasoft.popularlibs.data.user.UserRepository
import ru.varasoft.popularlibs.data.user.model.GithubUser

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
    lateinit var listDisposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val login = itemView.getLogin()
            var users: List<GithubUser>? = null
            listDisposable = usersRepo.fetchUsers()
                .subscribe({ s ->
                    users = s
                },
                    {println("onError: ${it.message}")})
            if (users != null) {
                for (item in users!!) {
                    if (item.login == login) {
                        router.navigateTo(screens.user(item.login))
                        break
                    }
                }
            }
        }
    }

    fun loadData() {
        var users: List<GithubUser>? = null
        listDisposable = usersRepo.fetchUsers()
            .subscribe({ s ->
                users = s
            })
        users?.let { usersListPresenter.users.addAll(it) }
        viewState.updateList()
    }

    override fun onDestroy() {
        super.onDestroy()
        listDisposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
