package com.example.gexemi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class UserFragmentAdpter extends FragmentStateAdapter {
    public UserFragmentAdpter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 1 :
                return new LockUser_Fragment();
            case 2 :
                return new UnistallUser_Fragment();
        }

        return new AllUser_Fragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
