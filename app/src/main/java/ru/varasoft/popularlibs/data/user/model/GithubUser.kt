package ru.varasoft.popularlibs.data.user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val name: String
) : Parcelable