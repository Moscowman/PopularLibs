package ru.varasoft.popularlibs

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}