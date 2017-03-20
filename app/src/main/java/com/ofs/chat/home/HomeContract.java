package com.ofs.chat.home;

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
        void showProfile(Employee employee);
        void chooseFile();
        boolean isprofileComplete();
        boolean allFieldsValid() throws Exception;
        String getSharedPrefValue(String key);

    }
    interface PresenterProfile extends BasePresenter{

        void loadProfile(String userId);
        void saveProfile(Employee employee) throws Exception;
        void editProfile(Employee employee);
        void uploadProfilePic();

    }

    interface ViewSettings extends BaseView<PresenterSettings>{

    }

    interface PresenterSettings extends BasePresenter{

    }

    interface ViewAddteam extends BaseView<PresenterAddTeam>{

    }

    interface PresenterAddTeam extends BasePresenter{

    }
}
