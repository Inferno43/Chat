package com.ofs.chat.contract;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public interface AbstractActivityCallback {

    void startActivity(Class< ? extends AppCompatActivity> claz,Bundle args);
}
