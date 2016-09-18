package in.dreamnation.salesgun.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.anim.AnimationUtils;
import in.dreamnation.salesgun.models.CompletedTaskInfo;


public class CompletedTaskInfoAdapter  extends RecyclerView.Adapter<CompletedTaskInfoAdapter.CompletedTaskInfoViewHolder> {

    List<CompletedTaskInfo> TaskList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    //keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;

    public CompletedTaskInfoAdapter(Context context, List<CompletedTaskInfo> taskList) {
        this.context = context;
        Log.e("skp", "context is " + context);
        inflater = LayoutInflater.from(context);
        this.TaskList = taskList;
    }


    @Override
    public int getItemCount() {
        return TaskList.size();
    }

    @Override
    public void onBindViewHolder(CompletedTaskInfoViewHolder taskViewHolder, int i) {
        CompletedTaskInfo ci = TaskList.get(i);
        taskViewHolder.TaskBrandName.setText(ci.name);
        taskViewHolder.TaskBrandImage.setImageResource(ci.icon);
        taskViewHolder.TaskDescription.setText(ci.description);
        taskViewHolder.TaskStatus.setText(ci.credits);

        if (i > mPreviousPosition) {
            AnimationUtils.animateSunblind(taskViewHolder, true);

        } else {
            AnimationUtils.animateSunblind(taskViewHolder, false);

        }
        mPreviousPosition = i;
    }

    @Override
    public CompletedTaskInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.completed_tasks_card_layout, viewGroup, false);
        CompletedTaskInfoViewHolder holder = new CompletedTaskInfoViewHolder(view);
        return holder;
    }

    class CompletedTaskInfoViewHolder extends RecyclerView.ViewHolder {

        TextView TaskBrandName;
        TextView TaskDescription;
        ImageView TaskBrandImage;
        TextView TaskStatus;

        public CompletedTaskInfoViewHolder(View v) {
            super(v);
            TaskBrandName =  (TextView) v.findViewById(R.id.CompletedTaskBrandName);
            TaskBrandImage = (ImageView)  v.findViewById(R.id.CompletedTaskBrandImage);
            TaskDescription = (TextView) v.findViewById(R.id.CompletedTaskDescription);
            TaskStatus = (TextView)v.findViewById(R.id.CompletedTaskCredits);
        }
    }
}