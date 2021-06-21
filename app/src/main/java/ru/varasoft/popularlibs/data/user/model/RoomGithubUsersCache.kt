package ru.varasoft.popularlibs.data.user.model

import ru.varasoft.popularlibs.data.user.Database
import ru.varasoft.popularlibs.data.user.RoomGithubUser

class RoomGithubUsersCache(private val db: Database) : IGithubUsersCache {
    override fun insertUsers(users: List<GithubUser>) {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }

    override fun getUsers(): List<GithubUser> {
        return db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }
}