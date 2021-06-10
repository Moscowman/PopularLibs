package ru.varasoft.popularlibs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val name: String
) : Parcelable