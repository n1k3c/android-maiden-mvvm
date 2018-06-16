package n1x0nj4.maidenmvvm.ui.greeting

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import n1x0nj4.maidenmvvm.repository.SayHelloRepository
import n1x0nj4.maidenmvvm.util.Status
import javax.inject.Inject


class GreetingViewModel @Inject constructor(private val sayHelloRepository: SayHelloRepository) : ViewModel() {

    private var disposable: Disposable? = null

    private val _status: MutableLiveData<Status> = MutableLiveData()
    val status: LiveData<Status>
        get() = _status


    private val _greetingResult: MutableLiveData<String> = MutableLiveData()
    val greetingResult: LiveData<String>
        get() = _greetingResult


    init {
        _status.value = Status.SUCCESS
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun sayHello(greeting: String?) {

        disposable?.dispose()

        _status.value = Status.LOADING

        disposable = sayHelloRepository.sayHello(greeting!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            _greetingResult.value = data
                            _status.value = Status.SUCCESS
                        },
                        { error ->
                            _status.value = Status.ERROR
                        }
                )
    }

}