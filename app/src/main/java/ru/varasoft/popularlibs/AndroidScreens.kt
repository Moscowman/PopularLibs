package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.presentation.ReposFragment
import ru.varasoft.popularlibs.presentation.UserFragment
import ru.varasoft.popularlibs.presentation.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(userId: GithubUser) = FragmentScreen { UserFragment.newInstance(userId) }
    override fun repos(userLogin: String) = FragmentScreen { ReposFragment.newInstance(userLogin) }
}