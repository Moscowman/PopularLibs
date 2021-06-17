package ru.varasoft.popularlibs.data.user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose

@Parcelize
data class GithubRepo(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val fullName: String? = null,
    @Expose val forksUrl: String? = null
) : Parcelable