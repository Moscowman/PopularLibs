package ru.varasoft.popularlibs.data.user

import io.reactivex.rxjava3.core.Single
import ru.varasoft.popularlibs.data.user.model.GithubUser

class MockedUserRepository(private val users: List<GithubUser>) : UserRepository {

    override fun fetchUsers(): Single<List<GithubUser>> =
        Single.just(users)

    override fun fetchUserById(userId: String): GithubUser? =
        users.firstOrNull { user -> user.login == userId }

}