package com.ofs.chat.onboarding;

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
import com.ofs.chat.home.Home;
import com.ofs.chat.toolbox.Constants;
import com.ofs.chat.toolbox.SharedPref;
import com.ofs.chat.toolbox.Utils;

import java.util.HashMap;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class LoginView extends BaseFragment implements OnboardingContract.ViewLogin,Arguments{

    private View rootView;
    private OnboardingContract.PresenterLogin presenterLogin;
    private Button signin;
    private EditText userEmail;
    private EditText userPassword;

    private String userName;
    private Context context;
    private SharedPref sharedPref;
    private HashMap<String,String> map;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login,container,false);
        context = getActivity();
        sharedPref = new SharedPref();
        map = new HashMap<>();
        signin = (Button)rootView.findViewById(R.id.signIn);
        userEmail = (EditText)rootView.findViewById(R.id.userName);
        userPassword = (EditText) rootView.findViewById(R.id.password);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenterLogin.login(userEmail.getText().toString(),userPassword.getText().toString());
                } catch (Exception e) {
                    showMessage(e.getMessage());
                }
            }
        });


        return rootView;
    }

    @Override
    public boolean allfieldsValid() throws Exception {
        if( userEmail.getText().toString().isEmpty() && userPassword.getText().toString().isEmpty())
            throw new EmptyTextException("Fields are empty");
        if(Utils.validateEmail(userEmail.getText().toString())
                && Utils.validpassword(userPassword.getText().toString()))
            return true;
        else
            throw new InvalidFieldException("Please enter Username and Password correctly");
    }

    @Override
    public void startHome(String fireBaseUId) {
        if(sharedPref.getString(context, SharedPref.PREFS_USERNAME) == null){
            map.put(SharedPref.PREFS_USERNAME,userName);
            map.put(SharedPref.PREFS_USEREMAIL,userEmail.getText().toString());
            map.put(SharedPref.PREFS_PASSWORD,userPassword.getText().toString());
            map.put(SharedPref.PREFS_USERID,fireBaseUId);


            sharedPref.save(getContext(),map);
        }
        startActivity(Home.class,null);

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(rootView,message,Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void setBundle(Bundle args) {
        userEmail.setText(args.getString(Constants.EXTRA_EMAIL));
        userName = args.getString(Constants.EXTRA_USERNAME);
    }

    @Override
    public void setPresenter(OnboardingContract.PresenterLogin presenter) {
        this.presenterLogin = presenter;
    }
}
