package ru.varasoft.popularlibs.data.user.model

interface IGithubReposCache {
    fun insertRepos(repositories : List<GithubRepo>, userLogin: String) : List<GithubRepo>
    fun getRepos(userLogin: String): List<GithubRepo>
}