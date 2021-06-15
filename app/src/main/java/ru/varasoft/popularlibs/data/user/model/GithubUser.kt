package ru.varasoft.popularlibs.data.user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose

@Parcelize
data class GithubUser(
    @Expose val login: String? = null,
    @Expose val id: String? = null,
    @Expose val avatarUrl: String? = null
) : Parcelable