package n1x0nj4.maidenmvvm.ui.greeting

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import com.jurajkusnier.androidapptemplate.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.greeting_fragment.*
import n1x0nj4.maidenmvvm.R
import n1x0nj4.maidenmvvm.ui.common.BaseFragment
import n1x0nj4.maidenmvvm.util.Status
import javax.inject.Inject

class GreetingFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: GreetingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.greeting_fragment, container, false)
    }

    override fun injectDependencies() {
        AndroidInjection.inject(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GreetingViewModel::class.java)

        viewModel.greetingResult.observe(this, Observer<String> { t ->
            if (t?.isNotEmpty() == true) {
                tvGreetingText.text = t
            } else {
                tvGreetingText.text = "No greeting for now"
            }
        })


        viewModel.status.observe(this, Observer<Status> {
            when (it) {
                Status.LOADING -> d { "Loading state" }
                Status.SUCCESS -> d { "Success state" }
                Status.ERROR -> d { "Error state" }
            }
        })

        viewModel.sayHello("Hello World!")
    }
}