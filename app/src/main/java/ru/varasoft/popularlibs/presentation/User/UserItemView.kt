package ru.varasoft.popularlibs.presentation.User

import ru.varasoft.popularlibs.IItemView

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun getLogin(): String
}