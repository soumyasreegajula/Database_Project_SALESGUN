package in.dreamnation.salesgun.models;

import android.support.v4.view.PagerTitleStrip;


public class Setting {
    public String title, description;
    public int id;

    public Setting(){}

    public Setting(int Id, String Title, String Description)
    {
        id =Id;
        title = Title;
        description = Description;
    }

    public void setId(int Id){
        id = Id;
    }

    public int getId()
    {
        return id;
    }

    public void setTitle(String Title){
        title = Title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String Description){
        description = Description;
    }

    public String getDescription()
    {
        return description;
    }


}
