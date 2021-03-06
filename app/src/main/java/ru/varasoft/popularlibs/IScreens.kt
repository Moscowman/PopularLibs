package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.Screen
import ru.varasoft.popularlibs.data.user.model.GithubRepo
import ru.varasoft.popularlibs.data.user.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(userId: GithubUser): Screen
    fun repos(userLogin: String): Screen
    fun repo(repo: GithubRepo): Screen
}