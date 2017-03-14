package com.ofs.chat.contract;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by saravana.subramanian on 3/6/17.
 */

public interface AbstractFragmentCallback {

    void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackstack, Bundle args);
}
