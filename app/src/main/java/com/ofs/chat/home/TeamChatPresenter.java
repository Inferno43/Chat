package com.ofs.chat.home;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class TeamChatPresenter implements HomeContract.PresenterChat {

    HomeContract.ViewChat viewChat;
    public TeamChatPresenter(HomeContract.ViewChat viewChat) {
        this.viewChat = viewChat;
        this.viewChat.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
