package app.dz.prizes.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import app.dz.prizes.fragment.base.BackHandledFragment;


public abstract class BaseMainActivity extends AppCompatActivity implements
        BackHandledFragment.BackHandlerInterface {

    private static final String LOG_TAG = BaseMainActivity.class.toString();
    protected DrawerLayout mDrawerLayout;
    protected NavigationView vNavigation;
    private String subtitle = "";

    private BackHandledFragment mSelectedFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ShortcutBadger.removeCount(this);
    }

    /*
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.wrap(newBase,App.getInstance().getAppLanguage().toString()));
    }
    */


    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        this.mSelectedFragment = backHandledFragment;
    }

    public BackHandledFragment getSelectedFragment() {
        return mSelectedFragment;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }

    }

    public void clearBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack(getSupportFragmentManager().getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void showFirstFragment(BackHandledFragment f) {
        showFirstFragment(f, f.getFragmentTag());
    }

    public void showFirstFragment(BackHandledFragment f, String fragmentTag) {
        clearBackStack();

        showFragment(f, fragmentTag);
    }

    public void showFragment(BackHandledFragment f) {
        showFragment(f, f.getFragmentTag());
    }

    public void showFragment(BackHandledFragment f, String fragmentTag) {
        if (getContainerID() < 0) {
            Log.e(LOG_TAG, "Container view for fragment hosting is not defined!");
            return;
        }

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        Fragment fragment = fm.findFragmentByTag(fragmentTag);

        if (fragment == null) {
            setSelectedFragment(f);
            fragmentTransaction
                    //.hide(previousFragment)
                    //.add(R.id.main_fragment_container, f, fragmentTag)
                    .replace(getContainerID(), f, fragmentTag)
                    .addToBackStack(fragmentTag)
                    //.commit();
                    //.commitNowAllowingStateLoss();
                    .commitAllowingStateLoss();
        } else {
            fm.popBackStack(fragmentTag, 0);
            setSelectedFragment((BackHandledFragment) fragment);
        }
    }

    public void setSubtitle(String newSubtitle) {
        subtitle = newSubtitle;
    }

    public void showSubTitle() {
        if (subtitle != null && !subtitle.isEmpty()) {
            ActionBar ab = getSupportActionBar();
            ab.setSubtitle(subtitle);
        }
    }

    public void showPreviousFragment(Class c) {

    }

    protected boolean isServiceRunning(Class serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns resource ID of container for fragment hosting.
     *
     * @return container view resource ID
     */
    protected int getContainerID() {
        return -1;
    }
}
