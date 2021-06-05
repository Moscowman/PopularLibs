package ru.varasoft.popularlibs

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen(private val user: GithubUser): FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        UserFragment.newInstance(user)
}