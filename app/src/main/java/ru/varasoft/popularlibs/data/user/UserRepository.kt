package ru.varasoft.popularlibs.data.user

import io.reactivex.Maybe
import io.reactivex.Single
import ru.varasoft.popularlibs.data.user.model.GithubUser

interface UserRepository {

    /**
     * Возвращает список пользователей.
     * @return список пользователей
     */
    fun fetchUsers(): Single<List<GithubUser>>

    /**
     * Возвращает пользователя по идентификатору.
     * @param userId идентифиактор пользователя
     * @return пользователь
     */
    fun fetchUserById(userId: String): Maybe<GithubUser?>

}