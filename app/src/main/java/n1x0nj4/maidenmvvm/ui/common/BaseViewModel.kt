package n1x0nj4.maidenmvvm.ui.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import n1x0nj4.maidenmvvm.util.Status

abstract class BaseViewModel : ViewModel() {

    var disposable: Disposable? = null

    val _status: MutableLiveData<Status> = MutableLiveData()
    val status: LiveData<Status>
        get() = _status

    init {
        _status.value = Status.SUCCESS
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}