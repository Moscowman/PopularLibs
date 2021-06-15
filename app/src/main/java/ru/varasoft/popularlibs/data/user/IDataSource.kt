package ru.varasoft.popularlibs.data.user

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.varasoft.popularlibs.data.user.model.GithubUser

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>
//...
}
