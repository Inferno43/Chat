package com.ofs.chat.onboarding;

import android.content.Context;
import android.os.Bundle;

import com.ofs.chat.BasePresenter;
import com.ofs.chat.BaseView;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public interface OnboardingContract {

    interface ViewLogin extends BaseView<PresenterLogin>{

        boolean allfieldsValid() throws Exception;
        void startHome(String fireBaseUId);
        void showMessage(String message);
        Context loginContext();
    }

    interface PresenterLogin extends BasePresenter{

        void login(String username, String password) throws Exception;

    }

    interface ViewSignup extends BaseView<PresenterSignUp>{
        void showProgress(boolean show);
        void showLogin(String userName,String email,String password);
        void showMessage(String message);
        boolean allFieldsValid()throws Exception;
        Context signupContext();
    }

    interface PresenterSignUp extends BasePresenter{
        void signUp(String userName,String email,String password) throws Exception;
    }
}
