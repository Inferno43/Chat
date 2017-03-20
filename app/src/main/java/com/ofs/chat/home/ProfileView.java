package com.ofs.chat.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ofs.chat.BaseFragment;
import com.ofs.chat.R;
import com.ofs.chat.exception.EmptyTextException;
import com.ofs.chat.exception.InvalidFieldException;
import com.ofs.chat.model.Employee;
import com.ofs.chat.toolbox.Constants;
import com.ofs.chat.toolbox.SharedPref;
import com.ofs.chat.toolbox.Utils;

import java.io.IOException;

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
    private ImageView userImage;
    private ImageView departmentSelector;
    private ImageView locationSelector;
    private Employee employee;
    private FloatingActionButton fab;
    Snackbar mySnackbar;
    PopupMenu locationMenu;
    PopupMenu departmentMenu;

    private String userId;
    StorageReference storageReference;

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
        storageReference = FirebaseStorage.getInstance().getReference();
        userImage = (ImageView) rootView.findViewById(R.id.emp_Image);
        userName = (EditText) rootView.findViewById(R.id.userName);
        userEmail = (EditText) rootView.findViewById(R.id.email);
        userEmpId = (EditText) rootView.findViewById(R.id.emp_id);
        userDepartment = (EditText) rootView.findViewById(R.id.department);
        userTeam = (EditText) rootView.findViewById(R.id.team);
        userLocation = (EditText) rootView.findViewById(R.id.phase);
        userExtension = (EditText) rootView.findViewById(R.id.extension);
        userRole = (EditText)rootView.findViewById(R.id.emp_role);
        locationSelector =(ImageView)rootView.findViewById(R.id.locationSelector);
        departmentSelector =(ImageView)rootView.findViewById(R.id.departmentSelector);
        fab = (FloatingActionButton) rootView.findViewById(R.id.editImage);

        try{
            userId = getArguments().getString(Constants.EXTRA_USERID)!= null ?
                    getArguments().getString(Constants.EXTRA_USERID):sharedPref.getString(context,SharedPref.PREFS_USERID);
        }catch (Exception e){
            e.printStackTrace();
        }

        Employee y = new Employee();
        Employee c = new Employee();
        if (y==c) Log.d("123","123"); else  Log.d("123","321");
        if (y.equals(c)) Log.d("321","321"); else  Log.d("321","123");

        locationMenu = new PopupMenu(context, locationSelector);
        departmentMenu = new PopupMenu(context, departmentSelector);

        submit =(Button)rootView.findViewById(R.id.submit);

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        locationMenu.getMenuInflater().inflate(R.menu.location, locationMenu.getMenu());
        departmentMenu.getMenuInflater().inflate(R.menu.department_menu, departmentMenu.getMenu());
        locationSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //registering popup with OnMenuItemClickListener
                locationMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        userLocation.setText(item.getTitle());
                        return true;
                    }
                });

                locationMenu.show();
            }
        });
        departmentSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //registering popup with OnMenuItemClickListener
                departmentMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        userDepartment.setText(item.getTitle());
                        return true;
                    }
                });


                departmentMenu.show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterProfile.uploadProfilePic();
            }
        });
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
        userDepartment.setText(employee.getEmployeeDepartment());
        userTeam.setText(employee.getEmployeeTeam());
        userLocation.setText(employee.getEmployeePhase());
        userExtension.setText(employee.getEmployeeExtension());
        userRole.setText(employee.getEmployeeRole());
    }

    @Override
    public void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constants.PICK_IMAGE_REQUEST);
    }

    @Override
    public String getSharedPrefValue(String key) {
        return sharedPref.getString(context, key);
    }


    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                final Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);
                userImage.setImageBitmap(bitmap);
                if (filePath != null) {
                    //displaying a progress dialog while upload is going on
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setTitle("Uploading");
                    progressDialog.show();

                    StorageReference riversRef = storageReference.child("images/"+userId+".jpg");
                    riversRef.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();

                                    sharedPref.save(context,SharedPref.PREFS_USER_IMAGE,String.valueOf("images/"));
                                    //and displaying a success toast
                                    Toast.makeText(context, "File Uploaded "+taskSnapshot.getDownloadUrl(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    //if the upload is not successfull
                                    //hiding the progress dialog
                                    progressDialog.dismiss();

                                    //and displaying error message
                                    Toast.makeText(context, exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    //calculating progress percentage
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                                    //displaying percentage in progress dialog
                                    progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                                }
                            });
                }
                //if there is not any file
                else {
                    //you can display an error toast
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
