package app.dz.prizes.fragment.base

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View

import app.dz.prizes.activity.MainActivity
import app.dz.prizes.R

abstract class BackHandledFragment : FragmentWithUnbinder() {

    companion object {
        val NO_HIGHLIGHT = 0
    }

    private var title: String? = null
    private var subtitle: String? = null
    private var showBackButton = false
    //private String screenTitle = "";  // заголовок экрана для этого фрагментика
    var layoutMenuHighlight = NO_HIGHLIGHT // id лаяута в slide_menu для подстветки, по умолчанию - никакой не подсвечивать

    lateinit var backHandlerInterface: BackHandlerInterface

    //Toast.makeText(getActivity(), "TAG:" + TAG, Toast.LENGTH_SHORT).show();
    val fragmentTag: String get() = javaClass.simpleName

    private val navigationView: NavigationView? get() = null

    fun show() {
        // Mark this fragment as the selected Fragment.
        backHandlerInterface.setSelectedFragment(this)
        showTitles()
    }

    fun showTitles() {
        val ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = title
        ab?.subtitle = subtitle
    }

    fun setTitle(title: String) {
        this.title = title
        val ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = title
    }

    fun setSubtitle(subtitle: String) {
        this.subtitle = subtitle
        val ab = (activity as AppCompatActivity).supportActionBar
        ab?.subtitle = subtitle
    }

    abstract fun onBackPressed(): Boolean

    interface BackHandlerInterface {
        fun setSelectedFragment(backHandledFragment: BackHandledFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity !is BackHandlerInterface) {
            throw ClassCastException("Hosting activity must implement BackHandlerInterface")
        } else {
            backHandlerInterface = activity as BackHandlerInterface
        }
    }

    override fun onStart() {
        super.onStart()
        // Mark this fragment as the selected Fragment
        backHandlerInterface.setSelectedFragment(this)
    }

    /*
    // GENERIC PATTERN OF OVERRIDING onBackPressed() METHOD
    // INSIDE A FRAGMENT THAT EXTENDS BackHandledFragment.
    @Override
    public boolean onBackPressed() {
        if (condition 1){
            // do something;
            return true; // event consumed
        }else if (condition 2){
            // do something else;
            return true; // event consumed
        }else{
            // event not consumed, let Activity handle it.
            return false;
        }
    }
    */

    override fun onResume() {
        super.onResume()
        if (navigationView != null) {
            setCheckedNavigationViewItem()
        }
    }

    protected fun getMenuItem(drawerMenu: Menu): MenuItem? {
        return null
    }

    private fun setCheckedNavigationViewItem() {
        val navigation = navigationView
        val drawerMenu = navigation!!.menu
        val menuItem = getMenuItem(drawerMenu)

        if (menuItem != null && !menuItem.isChecked) {
            menuItem.isChecked = true
        }
    }

    fun setBackNavigation(showBackButton: Boolean) {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        val toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        this.showBackButton = showBackButton

        if (toolbar != null && actionBar != null && activity is MainActivity) {
            /*
            if (showBackButton) {
                //android.R.drawable.
                actionBar.setHomeAsUpIndicator(R.drawable.icon_toolbal_arrow_white);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().onBackPressed();//goBack();
                    }
                });
            } else {
                actionBar.setHomeAsUpIndicator(R.drawable.ic_humburger);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).openDrawer();
                        }
                    }
                });
            }
            */
        }
    }


    protected fun goBack() {
        activity?.onBackPressed()
    }

}