package com.ofs.chat.home;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class SettingsPresenter implements HomeContract.PresenterSettings {

    HomeContract.ViewSettings viewSettings;
    public SettingsPresenter(HomeContract.ViewSettings viewSettings) {
        this.viewSettings = viewSettings;
        this.viewSettings.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
