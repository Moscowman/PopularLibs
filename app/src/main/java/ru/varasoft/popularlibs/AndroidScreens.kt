package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.varasoft.popularlibs.presentation.User.UserFragment
import ru.varasoft.popularlibs.presentation.Users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(userId: String) = FragmentScreen { UserFragment.newInstance(userId) }
}