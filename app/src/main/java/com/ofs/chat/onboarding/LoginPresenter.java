package com.ofs.chat.onboarding;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ofs.chat.toolbox.SharedPref;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class LoginPresenter implements OnboardingContract.PresenterLogin {

    OnboardingContract.ViewLogin viewLogin;
    FirebaseAuth mAuth;
    SharedPref sharedPref;

    public LoginPresenter(OnboardingContract.ViewLogin viewLogin) {
        this.viewLogin = viewLogin;
        this.viewLogin.setPresenter(this);
        this.mAuth = FirebaseAuth.getInstance();
        sharedPref = new SharedPref();
    }


    @Override
    public void login(final String username, final String password) throws Exception {
        if(viewLogin.allfieldsValid())
            mAuth.signInWithEmailAndPassword(username,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    viewLogin.startHome(authResult.getUser().getUid());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    viewLogin.showMessage(e.getMessage());
                }
            });

    }

    @Override
    public void start() {

    }


}
