package app.dz.prizes.fragment.base

import android.support.v4.app.Fragment

import butterknife.Unbinder

open class FragmentWithUnbinder : Fragment() {

    private var unbinder: Unbinder? = null

    protected fun setUnbinder(unbinder: Unbinder) {
        this.unbinder = unbinder
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*
        if (unbinder != null) {
            unbinder!!.unbind()
        }
        */
        unbinder?.unbind()
    }
}
