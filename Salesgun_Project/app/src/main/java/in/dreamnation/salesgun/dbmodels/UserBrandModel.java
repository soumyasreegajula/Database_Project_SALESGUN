package in.dreamnation.salesgun.dbmodels;

import java.util.Date;


public class UserBrandModel {
    private int UserBrandId, UserBrandUserId, UserBrandBrandId,UserBrandCredits;
    private Date UserBrandStartDate, UserBrandEndDate;

    public UserBrandModel(){}

    public UserBrandModel(int userBrandId, int userBrandUserId, int userBrandBrandId, int userBrandCredits, Date userBrandStartDate, Date userBrandEndDate)
    {
        UserBrandId = userBrandId;
        UserBrandUserId = userBrandUserId;
        UserBrandBrandId = userBrandBrandId;
        UserBrandCredits = userBrandCredits;
        UserBrandStartDate = userBrandStartDate;
        UserBrandEndDate = userBrandEndDate;
    }

    public int getUserBrandId() {
        return UserBrandId;
    }

    public  void setUserBrandId(int userBrandId)
    {
        UserBrandId = userBrandId;
    }

    public int getUserBrandUserId() {
        return UserBrandUserId;
    }

    public  void setUserBrandUserId(int userBrandUserId)
    {
        UserBrandUserId = userBrandUserId;
    }

    public int getUserBrandBrandId() {
        return UserBrandBrandId;
    }

    public  void setUserBrandBrandId(int userBrandBrandId)
    {
        UserBrandBrandId = userBrandBrandId;
    }

    public int getUserBrandCredits() {
        return UserBrandCredits;
    }

    public  void setUserBrandCredits(int userBrandCredits)
    {
        UserBrandCredits = userBrandCredits;
    }

    public Date getUserBrandStartDate() {
        return UserBrandStartDate;
    }

    public  void setUserBrandStartDate(Date userBrandStartDate)
    {
        UserBrandStartDate = userBrandStartDate;
    }

    public Date getUserBrandEndDate() {
        return UserBrandEndDate;
    }

    public  void setUserBrandEndDate(Date userBrandEndDate)
    {
        UserBrandEndDate = userBrandEndDate;
    }
}
