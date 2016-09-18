package in.dreamnation.salesgun.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.Locale;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.fragments.AllBrandsFragment;
import in.dreamnation.salesgun.fragments.MyBrandsFragment;


import static android.provider.Settings.Global.getString;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        if(position==0) {
            Log.e("positions" , "AllBFP : " + position);
            AllBrandsFragment fragment = new AllBrandsFragment();
            Bundle args = new Bundle();
            args.putInt(AllBrandsFragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }
        else
        {
            Log.e("positions" , "MyBFP : " + position);
            Fragment fragment = new MyBrandsFragment();
            Bundle args = new Bundle();
            args.putInt(MyBrandsFragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }

    }


    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "All Brands";
            case 1:
                return "My Brands";
            //   case 2:
            //     return "New Brands";
        }
        return null;
    }
}