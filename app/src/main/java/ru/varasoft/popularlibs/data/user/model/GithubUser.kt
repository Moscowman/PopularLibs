package ru.varasoft.popularlibs.data.user.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.Expose

@Parcelize
data class GithubUser(
    @Expose val id: String,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
) : Parcelable