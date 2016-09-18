package in.dreamnation.salesgun.fragments;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.adapters.CompletedTaskInfoAdapter;
import in.dreamnation.salesgun.models.CompletedTaskInfo;


public class CompletedTasksFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    private static TypedArray navMenuIcons;
    private static TypedArray navStatusIcons;

    public CompletedTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_menu_icons);
        navStatusIcons =  getResources().obtainTypedArray(R.array.nav_drawer_status_icons);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks_completed, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.completedTasksCardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(rootView.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        CompletedTaskInfoAdapter ca = new CompletedTaskInfoAdapter(rootView.getContext(), createList(30));
        recList.setAdapter(ca);
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() { super.onDetach(); }

    private List<CompletedTaskInfo> createList(int size) {

        List<CompletedTaskInfo> result = new ArrayList<CompletedTaskInfo>();
        for (int i=1; i <= size; i++) {
            CompletedTaskInfo ci = new CompletedTaskInfo();
            if(i%3==0) {
                ci.name = CompletedTaskInfo.NAME_PREFIX;
                ci.description = "Description of the brand goes here.Description of the brand goes here.";
                ci.status = navStatusIcons.getResourceId(1, -1);
            }
            if(i%3==1) {
                ci.name = CompletedTaskInfo.SURNAME_PREFIX;
                ci.description = "Description of the brand goes here.Description of the brand goes here.";
                ci.status = navStatusIcons.getResourceId(0, -1);
            }
            if(i%3==2) {
                ci.name = CompletedTaskInfo.EMAIL_PREFIX;
                ci.description = "Description of the brand goes here.Description of the brand goes here.";
                ci.status = navStatusIcons.getResourceId(0, -1);
            }
            ci.surname = CompletedTaskInfo.SURNAME_PREFIX + i;
            ci.email = CompletedTaskInfo.EMAIL_PREFIX + i + "@test.com";
            ci.icon = navMenuIcons.getResourceId(i, -1);

            result.add(ci);

        }

        return result;
    }
}