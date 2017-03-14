package com.ofs.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.ofs.chat.contract.AbstractActivityCallback;
import com.ofs.chat.contract.AbstractFragmentCallback;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class BaseActivity extends AppCompatActivity implements AbstractActivityCallback,AbstractFragmentCallback{

    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthListener;
    protected FirebaseUser user;
    protected FirebaseDatabase firebaseDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
    public void startActivity(Class<? extends AppCompatActivity> claz, Bundle args) {

        Intent intent = new Intent(this,claz);
        if (null != args) intent.putExtras(args);
        startActivity(intent);
    }

    @Override
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackstack, Bundle args) {
        if(fragment!=null){
            try{
                if(fragment.isAdded()){
                    return; //or return false/true, based on where you are calling from
                }
                String backStateName = fragment.getClass().getName();
                try{
                    if (args != null && !fragment.isAdded()) fragment.setArguments(args);
                }catch (Exception e){
                    e.printStackTrace();
                }


                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment, backStateName);
                if (addToBackstack) transaction.addToBackStack(backStateName);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

}
