package app.dz.prizes.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewFlipper
import app.dz.prizes.R
import app.dz.prizes.fragment.base.BackHandledFragment
import butterknife.BindView
import butterknife.ButterKnife


class SettingsFragment : BackHandledFragment() {

    companion object {

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }


    @BindView(R.id.view_switcher)
    private var vFlipper: ViewFlipper? = null


    override fun onBackPressed(): Boolean {
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_settings, container, false)

        if (v != null) {
            setUnbinder(ButterKnife.bind(this, v))
        }

        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setTitle(R.string.action_settings)
        toolbar?.subtitle = null
        //setBackNavigation(false);
        return v
    }

    private fun flipLayoutTo(layoutResourceId: Int) {
        while (vFlipper?.currentView?.id != layoutResourceId) {
            vFlipper?.showNext()
        }
    }
}
