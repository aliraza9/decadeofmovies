package com.ali.decadeofmovies.rxjava

 import com.ali.decadeofmovies.utils.rxjava.ISchedulerProvider
 import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class TestSchedulerProviderImpl : ISchedulerProvider {

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

}