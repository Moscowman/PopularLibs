package ru.varasoft.popularlibs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.App
import ru.varasoft.popularlibs.BackButtonListener
import ru.varasoft.popularlibs.arguments
import ru.varasoft.popularlibs.data.user.model.GithubUserRepository
import ru.varasoft.popularlibs.data.user.model.GithubUser
import ru.varasoft.popularlibs.databinding.FragmentUserBinding

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
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
        UserPresenter(
            userId,
            App.instance.router,
            userRepository = GithubUserRepository()
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