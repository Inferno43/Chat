package com.ofs.chat.home;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.ofs.chat.BaseActivity;
import com.ofs.chat.R;
import com.ofs.chat.toolbox.Constants;
import com.ofs.chat.toolbox.SharedPref;

public class Home extends BaseActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private TeamChatView teamChatView;
    private ProfileView  profileView;
    private SettingsView settingsView;

    private TeamChatPresenter chatPresenter;
    private ProfilePresenter profilePresenter;
    private SettingsPresenter settingsPresenter;

    private SharedPref sharedPref;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_base);

        sharedPref = new SharedPref();
        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

       if(sharedPref.getBoolean(context,SharedPref.PREFS_IS_PROFILE_COMPLETE)) mViewPager.setCurrentItem(0);
       else mViewPager.setCurrentItem(1,true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:

                    return fragmentChat();
                case 1:

                    return fragmentProfile();
                case 2:

                    return fragmentSettings();

                default:
                    return null;

            }
        }

        @Override
        public int getItemPosition(Object object) {
            if(object!=null && object instanceof TeamChatView )
                teamChatView.presenterChat.start();
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.team_chat);
                case 1:
                    return getString(R.string.profile);
                case 2:
                    return getString(R.string.settings);
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_logout:
                sharedPref.clearSharedPreference(context);
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    TeamChatView fragmentChat(){
        if(teamChatView == null)
            teamChatView = new TeamChatView();
        chatPresenter = new TeamChatPresenter(teamChatView);
        chatPresenter.start();
        return teamChatView;
    }

    SettingsView fragmentSettings(){
        if(settingsView == null)
            settingsView = new SettingsView();
        settingsPresenter = new SettingsPresenter(settingsView);
        settingsPresenter.start();
        return settingsView;
    }

    ProfileView fragmentProfile(){
        if(profileView == null)
            profileView = new ProfileView();

        profilePresenter = new ProfilePresenter(profileView, FirebaseDatabase.getInstance());
        profilePresenter.start();
        return profileView;
    }


    @Override
    public void onBackPressed() {

        if(getSupportFragmentManager().getBackStackEntryCount()==0)
            new CustomDialog().show(getSupportFragmentManager(),null);
        else
            getSupportFragmentManager().popBackStack();


    }


    public static class CustomDialog extends DialogFragment {

        public static CustomDialog newInstance(int title) {
            CustomDialog frag = new CustomDialog();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Do you want to close the app?")
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    startActivity(new Intent()
                                            . setAction(Intent.ACTION_MAIN)
                                            .addCategory(Intent.CATEGORY_HOME));
                                }
                            }
                    )
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            }
                    )
                    .create();
        }
    }
}
