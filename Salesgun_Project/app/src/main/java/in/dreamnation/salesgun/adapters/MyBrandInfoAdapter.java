package in.dreamnation.salesgun.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.Collections;
import java.util.List;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.models.MyBrandInfo;
import in.dreamnation.salesgun.anim.AnimationUtils;


public class MyBrandInfoAdapter extends RecyclerView.Adapter<MyBrandInfoAdapter.MyBrandInfoViewHolder> {
    List<MyBrandInfo> myBrandsList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    //keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public MyBrandInfoAdapter(Context context, List<MyBrandInfo> myBrandsList) {
        this.context = context;
        Log.e("skp", "context is " + context);
        inflater = LayoutInflater.from(context);
        this.myBrandsList = myBrandsList;
    }


    @Override
    public int getItemCount() {
        return myBrandsList.size();
    }

    @Override
    public void onBindViewHolder(MyBrandInfoViewHolder myBrandInfoViewHolder, int i) {
        MyBrandInfo ci = myBrandsList.get(i);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        myBrandInfoViewHolder.MyBrandName.setText(ci.name);
        myBrandInfoViewHolder.MyBrandImage.setImageUrl(ci.brandImage, imageLoader);
        //myBrandInfoViewHolder.MyBrandImage.setImageResource(ci.icon);
        myBrandInfoViewHolder.MyBrandCredits.setText(ci.credits);
        myBrandInfoViewHolder.MyTasksInProgress.setText(ci.numberOfTasksInProgress);

        if (i > mPreviousPosition) {
            AnimationUtils.animateSunblind(myBrandInfoViewHolder, true);

        } else {
            AnimationUtils.animateSunblind(myBrandInfoViewHolder, false);

        }
        mPreviousPosition = i;
    }

    @Override
    public MyBrandInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.my_brands_card_layout, viewGroup, false);
        MyBrandInfoViewHolder holder = new MyBrandInfoViewHolder(view);
        return holder;
    }

    class MyBrandInfoViewHolder extends RecyclerView.ViewHolder {

        TextView MyBrandName;
        TextView MyBrandCredits;
        NetworkImageView MyBrandImage;
        TextView MyTasksInProgress;

        public MyBrandInfoViewHolder(View v) {
            super(v);
            MyBrandName =  (TextView) v.findViewById(R.id.MyBrandName);
            MyBrandImage = (NetworkImageView)  v.findViewById(R.id.MyBrandImage);
            MyBrandCredits = (TextView) v.findViewById(R.id.MyCredits);
            MyTasksInProgress = (TextView) v.findViewById(R.id.MyTasksInProgress);
        }
    }
}
