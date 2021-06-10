package ru.varasoft.popularlibs.data.user

object UserRepositoryFactory {

    fun create(): UserRepository = MockedUserRepository(MockedUsers.users)

}