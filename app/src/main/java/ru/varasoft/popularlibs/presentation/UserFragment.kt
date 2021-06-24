package ru.varasoft.popularlibs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.*
import ru.varasoft.popularlibs.data.user.AndroidNetworkStatus
import ru.varasoft.popularlibs.data.user.Database
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.data.user.model.GithubUserRepository
import ru.varasoft.popularlibs.data.user.model.RoomGithubReposCache
import ru.varasoft.popularlibs.data.user.model.RoomGithubUsersCache
import ru.varasoft.popularlibs.databinding.FragmentUserBinding
import javax.inject.Inject

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var router: Router

    companion object {

        private const val ARG_USER_ID = "GithubUser"

        fun newInstance(userId: GithubUser): Fragment =
            UserFragment()
                .arguments(ARG_USER_ID to userId)
    }

    private val userId: String by lazy {
        arguments?.getString(ARG_USER_ID) ?: ""
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(ARG_USER_ID) as GithubUser
        UserPresenter(
            user.id,
            router,
            GithubUserRepository(GithubUserRepository.api, AndroidNetworkStatus(App.instance), RoomGithubUsersCache(database), RoomGithubReposCache(database)),
        )
    }


    private var vb: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setLogin(login: String) {
        vb?.userLogin?.text = login
    }

    override fun backPressed() = presenter.backPressed()
}