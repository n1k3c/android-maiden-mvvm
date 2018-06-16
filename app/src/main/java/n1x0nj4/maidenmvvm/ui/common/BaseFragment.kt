package n1x0nj4.maidenmvvm.ui.common

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ajalt.timberkt.d
import dagger.android.support.DaggerFragment
import n1x0nj4.maidenmvvm.R

abstract class BaseFragment : DaggerFragment(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(contentViewResource, container, false)

        return view
    }

    protected abstract fun injectDependencies()

    protected open val contentViewResource: Int = 0

//    protected fun setActionBarTitle(title: String) {
//        if (activity != null && activity is ActionBarResourceProvider) {
//            (activity as ActionBarResourceProvider).setTitle(title)
//        }
//    }
//
//    protected fun setActionBarIcon(icon: Int) {
//        if (activity != null && activity is ActionBarResourceProvider) {
//            (activity as ActionBarResourceProvider).setIcon(icon)
//        }
//    }

    override fun showLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showLoading()
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun hideLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideLoading()
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showMessage(description: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(description)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showMessage(message: String, description: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message, description)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showMessage(message: String, description: String, cancelable: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message, description, cancelable)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showMessage(message: String, description: String, listener: DialogInterface.OnClickListener, cancelable: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showMessage(message, description, listener, cancelable)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showError(description: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showError(description)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showSuccessToast(context: Context, message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showSuccessToast(context, message)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showErrorToast(context: Context, message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showErrorToast(context, message)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }

    override fun showInfoToast(context: Context, message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showInfoToast(context, message)
        } else {
            d { getString(R.string.extends_base_activity) }
        }
    }
}