package n1x0nj4.maidenmvvm.ui.common

import android.content.Context
import android.content.DialogInterface

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showMessage(description: String)

    fun showMessage(message: String, description: String)

    fun showMessage(message: String, description: String, cancelable: Boolean)

    fun showMessage(message: String, description: String, listener: DialogInterface.OnClickListener, cancelable: Boolean)

    fun showError(description: String)

    fun showSuccessToast(context: Context, message: String)

    fun showErrorToast(context: Context, message: String)

    fun showInfoToast(context: Context, message: String)
}