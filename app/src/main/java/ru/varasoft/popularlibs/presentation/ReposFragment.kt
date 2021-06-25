package ru.varasoft.popularlibs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.App
import ru.varasoft.popularlibs.BackButtonListener
import ru.varasoft.popularlibs.GlideImageLoader
import ru.varasoft.popularlibs.arguments
import ru.varasoft.popularlibs.databinding.FragmentUsersBinding

class ReposFragment : MvpAppCompatFragment(), ReposView, BackButtonListener {

    companion object {

        private const val REPOSITORY_ARG = "RepositoryArg"

        fun newInstance(reposUrl: String) = ReposFragment().apply {
            arguments(REPOSITORY_ARG to reposUrl)
        }
    }

    private val reposUrl: String by lazy {
        arguments?.getString(REPOSITORY_ARG) ?: ""
    }

    val presenter: ReposPresenter by moxyPresenter {
        ReposPresenter(
            reposUrl
        ).apply {
            App.instance.appComponent.inject(this)
        }
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