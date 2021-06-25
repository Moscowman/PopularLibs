package ru.varasoft.popularlibs.data.user.model

import ru.varasoft.popularlibs.data.user.Database
import ru.varasoft.popularlibs.data.user.RoomGithubRepository

class RoomGithubReposCache(private val db: Database) : IGithubReposCache {
    override fun insertRepos(repositories: List<GithubRepoDescription>, userLogin: String): List<GithubRepoDescription> {
        val roomUser = userLogin?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                it.id ?: "",
                it.name ?: "",
                it.forks ?: 0,
                roomUser.id
            )
        }
        db.repositoryDao.insert(roomRepos)
        return repositories
    }

    override fun getRepos(userLogin: String): List<GithubRepoDescription> {
        val roomUser = userLogin?.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        return db.repositoryDao.findForUser(roomUser.id)
            .map {
                GithubRepoDescription(it.id, it.name, null, it.forksCount)
            }
    }
}