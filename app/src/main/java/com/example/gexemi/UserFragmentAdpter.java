package com.example.gexemi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.gexemi.Fragment.AllUser_Fragment;
import com.example.gexemi.Fragment.LockUser_Fragment;
import com.example.gexemi.Fragment.UnistallUser_Fragment;

public class UserFragmentAdpter extends FragmentStateAdapter {
    public UserFragmentAdpter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0:
                return new AllUser_Fragment();
            case 1 :
                return new LockUser_Fragment();
            case 2 :
                return new UnistallUser_Fragment();
            default:
                return null;
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
