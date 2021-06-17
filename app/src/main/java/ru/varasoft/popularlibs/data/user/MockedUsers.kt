package ru.varasoft.popularlibs.data.user

import ru.varasoft.popularlibs.data.user.model.GithubUser

object MockedUsers {

    val users: List<GithubUser> =
        listOf(
            GithubUser(login = "User01", id = "User01"),
            GithubUser(login = "User02", id = "User02"),
            GithubUser(login = "User03", id = "User03"),
            GithubUser(login = "User04", id = "User04"),
            GithubUser(login = "User05", id = "User05"),
            GithubUser(login = "User06", id = "User06"),
            GithubUser(login = "User07", id = "User07"),
            GithubUser(login = "User08", id = "User08"),
            GithubUser(login = "User09", id = "User09"),
            GithubUser(login = "User10", id = "User10")
        )
}