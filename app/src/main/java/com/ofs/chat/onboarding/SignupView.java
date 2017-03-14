package com.ofs.chat.onboarding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ofs.chat.BaseFragment;
import com.ofs.chat.R;
import com.ofs.chat.contract.Arguments;
import com.ofs.chat.exception.EmptyTextException;
import com.ofs.chat.exception.InvalidFieldException;
import com.ofs.chat.toolbox.Constants;
import com.ofs.chat.toolbox.Utils;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class SignupView extends BaseFragment implements OnboardingContract.ViewSignup{

    private View rootView;
    private OnboardingContract.PresenterSignUp presenterSignUp;
    private EditText userName;
    private EditText email;
    private EditText password;
    private Button signUp;
    private Context context;
    public Arguments arguments;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try{
            arguments = (Arguments)activity;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.signup,container,false);
        context = getActivity();
        userName = (EditText)rootView.findViewById(R.id.userName);
        email = (EditText)rootView.findViewById(R.id.email);
        password = (EditText)rootView.findViewById(R.id.password);
        signUp = (Button)rootView.findViewById(R.id.register);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenterSignUp.signUp(userName.getText().toString(),email.getText().toString(),password.getText().toString());
                } catch (Exception e) {
                    showMessage(e.getMessage());
                }
            }
        });
        return rootView;
    }

    @Override
    public void setPresenter(OnboardingContract.PresenterSignUp presenter) {
        this.presenterSignUp = presenter;
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showLogin(String userName, String email, String password) {
        Onboarding onboarding = (Onboarding) getActivity();
        Bundle args = new Bundle();
        args.putString(Constants.EXTRA_EMAIL,email);
        args.putString(Constants.EXTRA_USERNAME,userName);
        arguments.setBundle(args);
        onboarding.getViewPager().setCurrentItem(0);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(rootView,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean allFieldsValid() throws Exception {
        if(userName.getText().toString().isEmpty()  &&  password.getText().toString().isEmpty() && email.getText().toString().isEmpty())
            throw new EmptyTextException("Fields are empty");
        if(Utils.validUserName(userName.getText().toString()) && Utils.validateEmail(email.getText().toString())
                && Utils.validpassword(password.getText().toString()))
            return true;
        else
            throw new InvalidFieldException("Please enter Username and Password correctly");
    }

    @Override
    public Context signupContext() {
        return context;
    }
}
