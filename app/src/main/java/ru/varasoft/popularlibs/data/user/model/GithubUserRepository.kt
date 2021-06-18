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

class GithubUserRepository(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) : IGithubUsersRepo {
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
                        val roomUsers = users.map { user -> RoomGithubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "", user.reposUrl ?: "") }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
                }
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
                            val roomUser = userLogin?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                            val roomRepos = repositories.map { RoomGithubRepository(it.id ?: "", it.name ?: "", it.forks ?: 0, roomUser.id) }
                            db.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    }
            } ?: Single.error<List<GithubRepo>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            Single.fromCallable {
                val roomUser = userLogin?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepo(it.id, it.name, null, it.forksCount) }
            }

        }
    }.subscribeOn(Schedulers.io())
}