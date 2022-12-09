package com.example.gexemi;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.gexemi.Fragment.Assignpolicy_fragment;
import com.example.gexemi.Fragment.Balancepolicy_fragment;
import com.example.gexemi.Fragment.Expiredpolicy_fragment;
import com.example.gexemi.Fragment.Uninstallpolicy_fragment;

public class PolicyFragmentAdapter extends FragmentStateAdapter {
    public PolicyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0:
                return new Balancepolicy_fragment();
            case 1 :
                return new Assignpolicy_fragment();
            case 2 :
                return new Uninstallpolicy_fragment();
            case 3 :
                return new Expiredpolicy_fragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}