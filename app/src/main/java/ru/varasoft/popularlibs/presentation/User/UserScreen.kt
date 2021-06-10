package ru.varasoft.popularlibs.presentation.User

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen(private val userId: String): FragmentScreen {

    override fun createFragment(factory: FragmentFactory) =
        UserFragment.newInstance(userId)
}