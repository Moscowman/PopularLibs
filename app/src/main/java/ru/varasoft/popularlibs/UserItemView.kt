package ru.varasoft.popularlibs

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun getLogin(): String
}