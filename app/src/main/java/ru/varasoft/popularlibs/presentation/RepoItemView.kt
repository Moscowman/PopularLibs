package ru.varasoft.popularlibs.presentation

import ru.varasoft.popularlibs.IItemView

interface RepoItemView: IItemView {
    fun setName(text: String)
}