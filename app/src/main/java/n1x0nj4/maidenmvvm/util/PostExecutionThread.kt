package n1x0nj4.maidenmvvm.util

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}
