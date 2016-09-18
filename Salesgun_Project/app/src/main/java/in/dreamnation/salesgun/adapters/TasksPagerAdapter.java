package in.dreamnation.salesgun.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.Locale;

import in.dreamnation.salesgun.fragments.AllTasksFragment;
import in.dreamnation.salesgun.fragments.OngoingTasksFragment;
import in.dreamnation.salesgun.fragments.CompletedTasksFragment;


public class TasksPagerAdapter extends FragmentPagerAdapter {

    public TasksPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        if(position==0) {
            AllTasksFragment fragment = new AllTasksFragment();
            Bundle args = new Bundle();
            args.putInt(AllTasksFragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }
        else if(position==1)
        {
            Fragment fragment = new OngoingTasksFragment();
            Bundle args = new Bundle();
            args.putInt(OngoingTasksFragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }
        else
        {
            Fragment fragment = new CompletedTasksFragment();
            Bundle args = new Bundle();
            args.putInt(CompletedTasksFragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }
    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Ongoing";
            case 2:
                 return "Completed";
        }
        return null;
    }
}