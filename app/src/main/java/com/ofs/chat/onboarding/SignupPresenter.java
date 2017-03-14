package com.ofs.chat.onboarding;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ofs.chat.toolbox.Utils;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class SignupPresenter implements OnboardingContract.PresenterSignUp {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private FirebaseDatabase firebaseDatabase;
    OnboardingContract.ViewSignup viewSignup;
    SignupPresenter(OnboardingContract.ViewSignup viewSignup) {
        this.viewSignup = viewSignup;
        this.viewSignup.setPresenter(this);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {

                }else {

                }
            }
        };
    }

    @Override
    public void start() {

    }

    @Override
    public void signUp(String userName, String email, String password) throws Exception {
        if(viewSignup.allFieldsValid())
            createAccount(userName,email,password);
    }

    private void createAccount(final String userName, final String email, final String password) {

        if (Utils.validateEmail(email) && Utils.validpassword(password)) {
            //replaceActivity(LoginActivity.class,true,null);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                //Toast.makeText(context,"Could not create your account, please try later",Toast.LENGTH_LONG).show();
                            }else {
                                task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        if (authResult.getUser() != null) {
                                            viewSignup.showLogin(userName,email,password);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                        }
                                    });
                            }
                        }
                    });
        } else{
            //Toast.makeText(context, "Invalid email or password", Toast.LENGTH_LONG).show();
        }

    }
}
