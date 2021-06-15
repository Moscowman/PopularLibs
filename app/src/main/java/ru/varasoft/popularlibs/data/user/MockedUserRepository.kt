package ru.varasoft.popularlibs.data.user

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.varasoft.popularlibs.data.user.model.GithubUser

class MockedUserRepository(private val users: List<GithubUser>) : UserRepository {

    override fun fetchUsers(): Single<List<GithubUser>> =
        Single.just(users)

    override fun fetchUserById(userId: String): Maybe<GithubUser?> {
        val user = users.firstOrNull { user -> user.login == userId }
        if (user != null)
            return Maybe.just(user)
        else
            return Maybe.empty()
    }
}