package ru.varasoft.popularlibs

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    fun counterClick(index: Int) {
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())
    }
}