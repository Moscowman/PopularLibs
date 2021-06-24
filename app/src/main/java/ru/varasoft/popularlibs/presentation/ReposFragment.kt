package ru.varasoft.popularlibs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.AndroidScreens
import ru.varasoft.popularlibs.App
import ru.varasoft.popularlibs.BackButtonListener
import ru.varasoft.popularlibs.arguments
import ru.varasoft.popularlibs.data.user.AndroidNetworkStatus
import ru.varasoft.popularlibs.data.user.Database
import ru.varasoft.popularlibs.data.user.model.GithubRepo
import ru.varasoft.popularlibs.data.user.model.GithubUserRepository
import ru.varasoft.popularlibs.data.user.model.RoomGithubReposCache
import ru.varasoft.popularlibs.data.user.model.RoomGithubUsersCache
import ru.varasoft.popularlibs.databinding.FragmentUsersBinding
import javax.inject.Inject

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    @Inject lateinit var router: Router

    companion object {

        private const val REPOSITORY_ARG = "RepositoryArg"

        fun newInstance(reposUrl: String) = ReposFragment()
            .arguments(REPOSITORY_ARG to reposUrl)
    }

    private val reposUrl: String by lazy {
        arguments?.getString(REPOSITORY_ARG) ?: ""
    }

    val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(
            reposUrl, AndroidSchedulers.mainThread(), GithubUserRepository(
                GithubUserRepository.api,
                AndroidNetworkStatus(requireContext()),
                RoomGithubUsersCache(Database.getInstance()),
                RoomGithubReposCache(Database.getInstance()),
            ), App.instance.router, AndroidScreens()
        )
    }
    var adapter: ReposRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {}

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}