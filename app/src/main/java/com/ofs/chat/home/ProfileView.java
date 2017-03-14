package com.ofs.chat.home;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;
import android.transition.ArcMotion;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ofs.chat.BaseFragment;
import com.ofs.chat.R;
import com.ofs.chat.exception.EmptyTextException;
import com.ofs.chat.exception.InvalidFieldException;
import com.ofs.chat.model.Employee;
import com.ofs.chat.toolbox.SharedPref;
import com.ofs.chat.toolbox.Utils;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class ProfileView extends BaseFragment implements HomeContract.ViewProfile{

    private View rootView;
    private HomeContract.PresenterProfile presenterProfile;
    private SharedPref sharedPref;
    private Context context;
    private Button submit;
    private EditText userName;
    private EditText userEmail;
    private EditText userEmpId;
    private EditText userDepartment;
    private EditText userLocation;
    private EditText userTeam;
    private EditText userExtension;
    private EditText userRole;
    private ImageView departmentSelector;
    private ImageView locationSelector;
    private Employee employee;
    private FloatingActionButton fab;
    Snackbar mySnackbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profile,container,false);
        context = getActivity();
        sharedPref = new SharedPref();
        userName = (EditText) rootView.findViewById(R.id.userName);
        userEmail = (EditText) rootView.findViewById(R.id.email);
        userEmpId = (EditText) rootView.findViewById(R.id.emp_id);
        userDepartment = (EditText) rootView.findViewById(R.id.department);
        userTeam = (EditText) rootView.findViewById(R.id.team);
        userLocation = (EditText) rootView.findViewById(R.id.phase);
        userExtension = (EditText) rootView.findViewById(R.id.extension);
        userRole = (EditText)rootView.findViewById(R.id.emp_role);
        fab = (FloatingActionButton) rootView.findViewById(R.id.editImage);

        submit =(Button)rootView.findViewById(R.id.submit);
        try{
            presenterProfile.setContext(context);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(isprofileComplete()) presenterProfile.loadProfile(sharedPref.getString(context,SharedPref.PREFS_USERID));
        else showMessage("your profile is not complete");

        presenterProfile.loadProfile(sharedPref.getString(context,SharedPref.PREFS_USERID));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    employee = new Employee(sharedPref.getString(context,SharedPref.PREFS_USERID),sharedPref.getString(context,SharedPref.PREFS_USER_IMAGE),
                            userEmpId.getText().toString(),userName.getText().toString(),userRole.getText().toString(),userDepartment.getText().toString(),
                            userTeam.getText().toString(),userEmail.getText().toString(),userLocation.getText().toString(),userExtension.getText().toString());
                    presenterProfile.saveProfile(employee);
                } catch (Exception e) {
                    showMessage(e.getMessage());
                }
            }
        });



        return rootView;
    }



    @Override
    public void setPresenter(HomeContract.PresenterProfile presenter) {
        presenterProfile = presenter;
    }

    @Override
    public void showMessage(String message) {
         mySnackbar = Snackbar.make(rootView,message,Snackbar.LENGTH_INDEFINITE);
         mySnackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySnackbar.dismiss();
            }
         });
        mySnackbar.show();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public boolean isprofileComplete() {
        return sharedPref.getBoolean(context,SharedPref.PREFS_IS_PROFILE_COMPLETE);
    }

    @Override
    public boolean allFieldsValid() throws Exception {
        if( userName.getText().toString().isEmpty() || userEmail.getText().toString().isEmpty()
                || userEmpId.getText().toString().isEmpty() || userDepartment.getText().toString().isEmpty()
                || userTeam.getText().toString().isEmpty() || userLocation.getText().toString().isEmpty()
                || userExtension.getText().toString().isEmpty() || userRole.getText().toString().isEmpty())
            throw new EmptyTextException("Fields are empty");
        if(Utils.validateEmail(userEmail.getText().toString())
                && !userName.getText().toString().isEmpty() && !userEmail.getText().toString().isEmpty()
                && !userEmpId.getText().toString().isEmpty() && !userDepartment.getText().toString().isEmpty()
                && !userTeam.getText().toString().isEmpty() && !userLocation.getText().toString().isEmpty()
                && !userExtension.getText().toString().isEmpty() && !userRole.getText().toString().isEmpty())
            return true;
        else
            throw new InvalidFieldException("Please enter all fields correctly");
    }

    @Override
    public void showProfile(Employee employee) {
        userName.setText(employee.getEmployeeName());
        userEmail.setText(employee.getEmployeeEmail());
        userEmpId.setText(employee.getEmployeeId());
        userDepartment.setText(employee.getEmployeeDesignation());
        userTeam.setText(employee.getEmployeeTeam());
        userLocation.setText(employee.getEmployeeLocation());
        userExtension.setText(employee.getEmployeeExtention());
        userRole.setText(employee.getEmployeeRole());
    }
}
