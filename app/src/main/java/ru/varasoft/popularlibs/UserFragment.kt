package ru.varasoft.popularlibs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.databinding.FragmentUserBinding

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {
    companion object {
        fun newInstance(user: GithubUser): Fragment {
            val fragment = UserFragment()
            fragment.user = user
            return fragment
        }
    }

    lateinit var user: GithubUser
        set

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            user,
            App.instance.router
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

    override fun init() {
    }

    override fun setLogin(login: String) {
        vb?.userLogin?.text = login
    }

    override fun backPressed() = presenter.backPressed()
}