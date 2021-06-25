package ru.varasoft.popularlibs.data.user.model

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.varasoft.popularlibs.data.user.*

class GithubUserRepository(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val roomGithubUsersCache: IGithubUsersCache,
    private val roomGithubReposCache: IGithubReposCache
) : IGithubUsersRepo {
    companion object {
        var api: IDataSource

        init {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            api = retrofit.create(IDataSource::class.java)
        }
    }

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        roomGithubUsersCache.insertUsers(users)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                roomGithubUsersCache.getUsers()
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getUserById(userId: String): Maybe<GithubUser> {
        return api.getUser(userId).subscribeOn(Schedulers.io())
    }

    override fun getRepos(userLogin: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            userLogin?.let { userLogin ->
                api.getRepos(userLogin)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            roomGithubReposCache.insertRepos(repositories, userLogin)
                        }
                    }
            }
        } else {
            Single.fromCallable {
                roomGithubReposCache.getRepos(userLogin)
            }

        }
    }.subscribeOn(Schedulers.io())
}