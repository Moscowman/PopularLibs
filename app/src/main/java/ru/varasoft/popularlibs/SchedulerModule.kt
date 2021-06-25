package ru.varasoft.popularlibs

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class SchedulerModule(val app: App) {
    @Provides
    fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
