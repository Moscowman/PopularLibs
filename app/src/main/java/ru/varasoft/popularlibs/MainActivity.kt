package ru.varasoft.popularlibs

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.varasoft.popularlibs.databinding.ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}