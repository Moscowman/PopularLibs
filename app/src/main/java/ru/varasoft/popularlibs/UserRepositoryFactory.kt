package ru.varasoft.popularlibs

object UserRepositoryFactory {

    fun create(): UserRepository = MockedUserRepository(MockedUsers.users)

}