package com.ofs.chat.onboarding;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ofs.chat.BaseActivity;
import com.ofs.chat.R;
import com.ofs.chat.contract.Arguments;

public class Onboarding extends BaseActivity implements Arguments{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    public ViewPager mViewPager;
    private LoginView loginView;
    private LoginPresenter loginPresenter;
    private SignupView signupView;
    private SignupPresenter signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        loginView = new LoginView();
        signupView = new SignupView();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        loginPresenter = new LoginPresenter(loginView);
        signupPresenter = new SignupPresenter(signupView);

        loginPresenter.start();
        signupPresenter.start();
    }

    @Override
    public void setBundle(Bundle args) {
        if(args!=null){
            loginView.setBundle(args);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return loginView != null ? loginView : new LoginView();
                case 1:

                    return signupView != null ? signupView : new SignupView();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SIGN IN";
                case 1:
                    return "SIGN UP";
            }
            return null;
        }
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
