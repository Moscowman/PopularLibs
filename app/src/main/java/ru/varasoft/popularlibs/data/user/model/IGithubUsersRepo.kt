package ru.varasoft.popularlibs.data.user.model

import io.reactivex.Maybe
import io.reactivex.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserById(userId: String): Maybe<GithubUser>
    fun getRepos(reposUrl: String): Single<List<GithubRepoDescription>>
}