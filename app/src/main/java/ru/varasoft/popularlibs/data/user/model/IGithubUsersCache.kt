package ru.varasoft.popularlibs.data.user.model

interface IGithubUsersCache {
    fun insertUsers(users: List<GithubUser>)
    fun getUsers(): List<GithubUser>
}