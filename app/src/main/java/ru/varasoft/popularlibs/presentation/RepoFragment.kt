package ru.varasoft.popularlibs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.App
import ru.varasoft.popularlibs.BackButtonListener
import ru.varasoft.popularlibs.data.user.model.GithubRepo
import ru.varasoft.popularlibs.databinding.FragmentRepoBinding

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {
    companion object {

        private const val ARG_REPO = "GithubRepo"

        var repo: GithubRepo? = null

        fun newInstance(repo: GithubRepo): Fragment {
            val myFragment = RepoFragment()
            val args = Bundle()
            args.putParcelable(ARG_REPO, repo)
            myFragment.setArguments(args)

            return myFragment
        }
    }

    private val repo : GithubRepo? by lazy {
        arguments?.getParcelable(ARG_REPO)
    }

    val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            repo,
            App.instance.router
        )
    }

    private var vb: FragmentRepoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setName(name: String) {
        vb?.name?.text = "Name: $name"
    }

    override fun setFullName(fullName: String) {
        vb?.fullName?.text = "Full name: $fullName"
    }

    override fun setNoOfForks(noOfForks: Int) {
        vb?.noOfForks?.text = "Forks: $noOfForks"
    }

    override fun setNoOfWatchers(noOfWatchers: Int) {
        vb?.noOfWatchers?.text = "Watchers: $noOfWatchers"
    }

    override fun backPressed() = presenter.backPressed()
}