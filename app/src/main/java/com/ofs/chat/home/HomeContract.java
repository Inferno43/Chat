package com.ofs.chat.home;

import android.content.Context;

import com.ofs.chat.BasePresenter;
import com.ofs.chat.BaseView;
import com.ofs.chat.model.Employee;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public interface HomeContract {

    interface ViewChat extends BaseView<PresenterChat> {

    }
    interface PresenterChat extends BasePresenter{

    }


    interface ViewProfile extends BaseView<PresenterProfile>{

        void showMessage(String message);
        void showProgress(boolean show);
        boolean isprofileComplete();
        boolean allFieldsValid() throws Exception;
        void showProfile(Employee employee);

    }
    interface PresenterProfile extends BasePresenter{

        void loadProfile(String userId);
        void saveProfile(Employee employee) throws Exception;
        void editProfile();
        void setContext(Context context);

    }

    interface ViewSettings extends BaseView<PresenterSettings>{

    }

    interface PresenterSettings extends BasePresenter{

    }
}
