package ru.varasoft.popularlibs

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.varasoft.popularlibs.data.user.model.GithubRepoDescription
import ru.varasoft.popularlibs.presentation.RepoFragment
import ru.varasoft.popularlibs.presentation.ReposFragment
import ru.varasoft.popularlibs.presentation.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun repos(userLogin: String) = FragmentScreen { ReposFragment.newInstance(userLogin) }
    override fun repo(repoDescription: GithubRepoDescription) = FragmentScreen { RepoFragment.newInstance(repoDescription) }
}