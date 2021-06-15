package ru.varasoft.popularlibs.data.user.model

import io.reactivex.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}