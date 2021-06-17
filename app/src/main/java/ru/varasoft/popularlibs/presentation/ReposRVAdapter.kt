package ru.varasoft.popularlibs.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.varasoft.popularlibs.IImageLoader
import ru.varasoft.popularlibs.IRepoListPresenter
import ru.varasoft.popularlibs.IUserListPresenter
import ru.varasoft.popularlibs.databinding.ItemRepoBinding
import ru.varasoft.popularlibs.databinding.ItemUserBinding

class ReposRVAdapter(val presenter: IRepoListPresenter, val imageLoader: IImageLoader<ImageView>): RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override var pos = -1

        override fun setName(text: String) {
            vb.repoName.text = text
        }
    }
}