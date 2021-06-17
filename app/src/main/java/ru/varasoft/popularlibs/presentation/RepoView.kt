package ru.varasoft.popularlibs.presentation

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoView : MvpView {
    fun setName(name: String)
    fun setFullName(fullName: String)
    fun setNoOfForks(noOfForks: Int)
    fun setNoOfWatchers(noOfWatchers: Int)
}