package ru.varasoft.popularlibs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.*
import ru.varasoft.popularlibs.data.user.model.GithubUserRepository
import ru.varasoft.popularlibs.databinding.FragmentUsersBinding

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {
    companion object {

        private const val ARG_REPOS_URL = "ReposUrl"

        fun newInstance(reposUrl: String) = ReposFragment()
            .arguments(ARG_REPOS_URL to reposUrl)
    }

    private val reposUrl: String by lazy {
        arguments?.getString(ARG_REPOS_URL) ?: ""
    }
    val presenter: ReposPresenter by moxyPresenter { ReposPresenter(reposUrl, AndroidSchedulers.mainThread(), GithubUserRepository(), App.instance.router, AndroidScreens()) }
    var adapter: ReposRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}