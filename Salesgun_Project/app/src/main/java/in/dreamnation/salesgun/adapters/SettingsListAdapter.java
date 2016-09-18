package in.dreamnation.salesgun.adapters;

import in.dreamnation.salesgun.R;
import in.dreamnation.salesgun.helpers.AppController;
import in.dreamnation.salesgun.models.Setting;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


abstract  class SettingsListAdapter extends BaseAdapter {
   public Activity activity;
   /*public LayoutInflater inflater;
    private List<Setting> SettingItems;

    public SettingsListAdapter(Activity activity, List<Setting> settingItems) {
        this.activity = activity;
        this.SettingItems = settingItems;
    }

    @Override
    public int getCount() {
        return SettingItems.size();
    }

    @Override
    public Object getItem(int location) {
        return SettingItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.settings_row, null);

        TextView title = (TextView) convertView.findViewById(R.id.settings_name);
        TextView description = (TextView) convertView.findViewById(R.id.settings_description);

        Setting m = SettingItems.get(position);

        // title
        title.setText(m.getTitle());

        description.setTag(m.getDescription());

        return convertView;
    }*/

}
