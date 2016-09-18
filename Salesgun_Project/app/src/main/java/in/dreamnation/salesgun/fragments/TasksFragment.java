package in.dreamnation.salesgun.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.adapters.SectionsPagerAdapter;
import in.dreamnation.salesgun.adapters.TasksPagerAdapter;


public class TasksFragment extends Fragment {

    public TasksFragment() {
        // Required empty public constructor
    }
    private View view;
    TasksPagerAdapter mtasksPagerAdapter;
    ViewPager mViewPager;
    private FragmentActivity myContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_tasks, container, false);
        mtasksPagerAdapter = new TasksPagerAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.taskspager);
        mViewPager.setAdapter(mtasksPagerAdapter);
        return view;
    }


    // public void onAttach(Activity activity) {
    //  super.onAttach(activity);
    // }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }
}