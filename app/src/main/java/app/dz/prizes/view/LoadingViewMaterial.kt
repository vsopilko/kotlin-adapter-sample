package app.dz.prizes.view

import android.app.ProgressDialog
import android.content.Context
import android.view.Window

import app.dz.prizes.R


object LoadingViewMaterial {

    private var dialog: ProgressDialog? = null

    val isNull: Boolean
        get() = dialog == null

    fun show(context: Context) {
        if (dialog == null) {
            dialog = ProgressDialog(context)
            dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setMessage(context.getString(R.string.loading)) //"Loading..."
            dialog?.setCancelable(false)
        }
        dialog?.show()
    }

    fun hide() {
        dialog?.dismiss()
        dialog = null
    }
}
