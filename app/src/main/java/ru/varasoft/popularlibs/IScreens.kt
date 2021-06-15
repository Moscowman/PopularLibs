package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.Screen
import ru.varasoft.popularlibs.data.user.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(userId: GithubUser): Screen
}