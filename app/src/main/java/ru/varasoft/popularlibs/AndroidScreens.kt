package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user() = FragmentScreen { UserFragment.newInstance("") }
}