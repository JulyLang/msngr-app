package com.apps.hw2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.apps.hw2.app.App
import com.apps.hw2.channels.ChannelsFragment
import com.apps.hw2.channels.pager.usecase.SyncSubscribedStreamsUseCase
import com.apps.hw2.db.MyUserIdStorage
import com.apps.hw2.people.PeopleFragment
import com.apps.hw2.profile.ProfileFragment
import com.apps.hw2.profile.usecase.IFetchMyUserUseCase
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener,
    BottomNavigationViewHolder {

    private val bottomNavigationView: BottomNavigationView
        get() = findViewById(R.id.bottomNavigationView)

    private var fetchMyUserDisposable: Disposable? = null

    @Inject
    lateinit var fetchMyUserUseCase: IFetchMyUserUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).userComponent.injectMainActivity(this)
        fetchMyUserDisposable = fetchMyUserUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    MyUserIdStorage.myUserId = it
                    Log.d(TAG, "Fetch was successful, myUserId=$it")
                },
                onError = {
                    Log.e(TAG, "Can't fetch my user ID", it)
                }
            )
        setContentView(R.layout.activity_main)
        initViews()
        selectTabAtStartup()
    }

    override fun onDestroy() {
        super.onDestroy()
        fetchMyUserDisposable?.dispose()
        fetchMyUserDisposable = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_channels -> onChannelsTabSelected()
            R.id.action_people -> onPeopleTabSelected()
            R.id.action_profile -> onProfileTabSelected()
            else -> false
        }
    }

    override fun changeVisibility(visibility: Int) {
        bottomNavigationView.visibility = visibility
    }

    private fun initViews() {
        bottomNavigationView.setOnItemSelectedListener(this)
    }

    private fun selectTabAtStartup() {
        onChannelsTabSelected()
    }

    private fun onChannelsTabSelected(): Boolean {
        Log.i(TAG, "onChannelsTabSelected")
        replaceFragment(ChannelsFragment.TAG) { ChannelsFragment() }
        return true
    }

    private fun onPeopleTabSelected(): Boolean {
        Log.i(TAG, "onPeopleTabSelected")
        replaceFragment(PeopleFragment.TAG) { PeopleFragment() }
        return true
    }

    private fun onProfileTabSelected(): Boolean {
        Log.i(TAG, "onProfileTabSelected")
        replaceFragment(ProfileFragment.TAG) { ProfileFragment() }
        return true
    }

    private fun isCurrentFragmentAlreadyAdded(tag: String): Boolean {
        return supportFragmentManager.findFragmentByTag(tag) != null
    }

    private fun replaceFragment(tag: String, createFragmentCallback: () -> Fragment) {
        if (!isCurrentFragmentAlreadyAdded(tag)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.contentFragmentView, createFragmentCallback.invoke(), tag)
                .commit()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}