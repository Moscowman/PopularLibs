package ru.varasoft.popularlibs.presentation

import ru.varasoft.popularlibs.IItemView

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun getLogin(): String
}