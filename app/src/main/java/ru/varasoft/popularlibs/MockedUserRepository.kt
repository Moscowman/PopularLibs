package ru.varasoft.popularlibs

class MockedUserRepository(private val users: List<GithubUser>) : UserRepository {

    override fun fetchUsers(): List<GithubUser> =
        users

    override fun fetchUserById(userId: String): GithubUser? =
        users.firstOrNull { user -> user.login == userId }

}