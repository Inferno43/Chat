package com.ofs.chat.home;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ofs.chat.model.Employee;
import com.ofs.chat.toolbox.Constants;
import com.ofs.chat.toolbox.SharedPref;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public class ProfilePresenter implements HomeContract.PresenterProfile {

    HomeContract.ViewProfile viewProfile;
    FirebaseDatabase firebaseDatabase;

    public ProfilePresenter(HomeContract.ViewProfile viewProfile,FirebaseDatabase firebaseDatabase) {
        this.viewProfile = viewProfile;
        this.viewProfile.setPresenter(this);
        this.firebaseDatabase = firebaseDatabase;

    }

    @Override
    public void start() {

    }

    @Override
    public void loadProfile(final String userId) {
        firebaseDatabase.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Employees").hasChild(userId)) {
                    String user = (String) dataSnapshot.child("Employees").child(userId).child("userId").getValue();
                    String employeeId = (String) dataSnapshot.child("Employees").child(userId).child("employeeId").getValue();
                    String employeeName = (String) dataSnapshot.child("Employees").child(userId).child("employeeName").getValue();
                    String employeeDepartment = (String) dataSnapshot.child("Employees").child(userId).child("employeeDepartment").getValue();
                    String employeeEmail = (String) dataSnapshot.child("Employees").child(userId).child("employeeEmail").getValue();
                    String employeePhase = (String) dataSnapshot.child("Employees").child(userId).child("employeePhase").getValue();
                    String employeeTeam = (String) dataSnapshot.child("Employees").child(userId).child("employeeTeam").getValue();
                    String employeeExtension = (String) dataSnapshot.child("Employees").child(userId).child("employeeExtension").getValue();
                    String employeeRole = (String) dataSnapshot.child("Employees").child(userId).child("employeeRole").getValue();
                    String employeeImage = Constants.FIREBASE_IMAGE_PATH;
                    viewProfile.showProfile(new Employee(user,employeeImage,employeeId,employeeName,employeeRole,employeeDepartment,
                            employeeTeam,employeeEmail,employeePhase,employeeExtension));
                    viewProfile.showMessage("your profile loaded");

                }else{
                    String userId = viewProfile.getSharedPrefValue(SharedPref.PREFS_USERID);
                    String employeeName = viewProfile.getSharedPrefValue(SharedPref.PREFS_USERNAME);
                    String employeeEmail = viewProfile.getSharedPrefValue(SharedPref.PREFS_USEREMAIL);
                    viewProfile.showProfile(new Employee(userId,"","",employeeName,"",""
                            ,"",employeeEmail,"",""));
                    viewProfile.showMessage("your profile is incomplete");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                viewProfile.showMessage("Update your profile");
            }
        });
    }

    @Override
    public void saveProfile(final Employee employee) throws Exception {

        if(viewProfile.allFieldsValid())
            firebaseDatabase.getReference().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.child("Employees").hasChild(employee.getUserId()))
                        firebaseDatabase.getReference().child("Employees").child(employee.getUserId()).setValue(employee);
                    else
                        editProfile(employee);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    viewProfile.showMessage("Update your profile");
                }
            });


    }

    @Override
    public void editProfile(Employee employee) {

        Map<String,Object> emp = new HashMap<>();
        emp.put(employee.getUserId(),employee);
        firebaseDatabase.getReference().child("Employees").updateChildren(emp);
    }

    @Override
    public void uploadProfilePic() {
        viewProfile.chooseFile();
    }

}
