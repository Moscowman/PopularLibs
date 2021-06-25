package ru.varasoft.popularlibs.data.user.model

interface IGithubReposCache {
    fun insertRepos(repositories : List<GithubRepoDescription>, userLogin: String) : List<GithubRepoDescription>
    fun getRepos(userLogin: String): List<GithubRepoDescription>
}