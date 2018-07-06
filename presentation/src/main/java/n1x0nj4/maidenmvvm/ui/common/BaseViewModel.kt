package n1x0nj4.maidenmvvm.ui.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}