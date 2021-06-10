package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.varasoft.popularlibs.presentation.UserFragment
import ru.varasoft.popularlibs.presentation.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(userId: String) = FragmentScreen { UserFragment.newInstance(userId) }
}