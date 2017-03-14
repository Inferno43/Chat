package com.ofs.chat.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ofs.chat.BaseFragment;
import com.ofs.chat.R;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class SettingsView extends BaseFragment implements HomeContract.ViewSettings{

    HomeContract.PresenterSettings presenterSettings;
    private View rootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings,container,false);
        return rootView;
    }

    @Override
    public void setPresenter(HomeContract.PresenterSettings presenter) {
        presenterSettings = presenter;
    }
}
