package app.dz.prizes.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import android.widget.ViewFlipper
import app.dz.prizes.R
import app.dz.prizes.activity.MainActivity
import app.dz.prizes.adapter.SubscriptionAdapter
import app.dz.prizes.fragment.base.BackHandledFragment
import app.dz.prizes.model.SubscriptionList
import app.dz.prizes.retrofit2.RetrofitAPI
import app.dz.prizes.retrofit2.RetrofitService
import app.dz.prizes.retrofit2.model.Subscription
import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SubscriptionFragment : BackHandledFragment() {

    @BindView(R.id.view_switcher)
    private var vFlipper: ViewFlipper? = null

    @BindView(R.id.rv)
    private var rv: RecyclerView? = null

    private var adapter: SubscriptionAdapter? = null
    //private var items: MutableList<Subscription> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        /*
        if (getArguments() != null && getArguments().getSerializable("CONTENT_TYPE") != null) {
            contentType = (ContentType) getArguments().getSerializable("CONTENT_TYPE");
        }
        */

        val items = mutableListOf<Subscription>()
        val item1 = Subscription()
        item1.title = "title 1"
        items.add(item1)
        val item2 = Subscription()
        item2.title = "title 2"
        items.add(item2)
        //val itemClickListener = View.OnClickListener {  }

        adapter = SubscriptionAdapter(items)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_subscriptions, container, false)
        setUnbinder(ButterKnife.bind(this, v))

        val toolbar = (activity as AppCompatActivity).supportActionBar
        toolbar?.setTitle(R.string.action_subscriptions)
        toolbar?.subtitle = null

        //setBackNavigation(false);

        /*
        if (adapter?.itemCount == 0) {
            flipLayoutTo(R.id.no_data_layout)
        } else {
            flipLayoutTo(R.id.ltData)
        }
        */

        rv?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv?.adapter = adapter

       loadData()

        return v
    }

    private fun loadData() {
        /*
        if (!App.getInstance().isOnline()) {
            // read news from json

            Offline.VideoGroups.load();
            if (!Offline.VideoGroups.list.isEmpty()) {
                VideoGroupsProcessor.resetVideoGroupList(Offline.VideoGroups.list);
                VideoGroupsProcessor.resetFavorites();
                adapter.notifyDataSetChanged();
            } else {
                flipLayoutTo(R.id.no_internet_layout);
            }
            return;
        }
        */

        //requestDataFromServer()

        //val api = RetrofitProvider.api()
        val api = RetrofitAPI(RetrofitService.create())

        MainActivity.compositeDisposable.add(
                api.getSubscriptions()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            adapter?.set(result.data)
                            Toast.makeText(activity, "Found ${result.data.size} item(s)", Toast.LENGTH_SHORT).show()
                            //Log.d("Result", "There are ${result.data.size} items")
                        }, { error ->
                            error.printStackTrace()
                        })
        )
    }

    /*
    private fun requestDataFromServer() {
        LoadingViewMaterial.show(App.instance)


        RetrofitService service = RetrofitFactory.getInstanceWithSessionId(getActivity())
                .create(RetrofitService.class);

        Call<SubscriptionList> callback;

        callback = service.getVideoGroups(App.getInstance().getAppLanguage().toString());

        callback.enqueue(new Callback<GroupList>() {
            @Override
            public void onResponse(Call<SubscriptionList> call, Response<SubscriptionList> response) {
                if (response.code() == 200) {
                    //VideoGroupsProcessor.resetVideoGroupList(response.body().getShowGroupList());
                    adapter.notifyDataSetChanged();
                    //Offline.VideoGroups.save(VideoGroupsProcessor.getVideoGroups());

                    //Offline.VideoGroups.downloadImages(getActivity(), true);

                    if (response.body().getShowGroupList().isEmpty()) {
                        flipLayoutTo(R.id.no_data_layout);
                    } else {
                        flipLayoutTo(R.id.ltVideoGroups);
                    }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
                }
                LoadingViewMaterial.hide();
            }

            @Override
            public void onFailure(Call<SubscriptionList> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
                LoadingViewMaterial.hide();
            }
        });
    }
    */

    private fun flipLayoutTo(layoutResourceId: Int) {
        while (vFlipper?.currentView?.id != layoutResourceId) {
            vFlipper?.showNext()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        //inflater.inflate(R.menu.menu_show, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
        /*
            case R.id.action_downloads:
                //showDownloads();
                return true;

            case R.id.action_favourites:
                if (App.getInstance().getUserProfile() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.favorites)
                            .setMessage(getString(R.string.login_or_signup))
                            .setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (getActivity() instanceof AuthorizationHandler) {
                                        ((AuthorizationHandler) getActivity()).userLogin();
                                    }
                                }
                            })
                            .setNegativeButton(R.string.btn_cancel, null)
                            .setCancelable(true)
                            .show();
                } else {
                    //showFavourites();
                }

                return true;
            */

            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    /*
    protected NavigationView getNavigationView() {
        return (NavigationView) getActivity().findViewById(R.id.navigation_view);
    }

    protected MenuItem getMenuItem(Menu drawerMenu) {
        return drawerMenu.findItem(R.id.programs);
    }
    */


    override fun onResume() {
        super.onResume()
        /*
        if (VideoGroupsProcessor.getVideoGroups().isEmpty()
                || !userName.equals(currentUserName)
                || VideoGroupsProcessor.getLanguageCode() == null
                || VideoGroupsProcessor.getLanguageCode() != App.getInstance().getAppLanguage()) {
            userName = currentUserName;
            loadData();
        }
        */
        adapter?.notifyDataSetChanged()
    }

}
