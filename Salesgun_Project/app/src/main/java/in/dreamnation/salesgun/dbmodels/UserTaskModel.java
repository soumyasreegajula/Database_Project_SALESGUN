package in.dreamnation.salesgun.dbmodels;

import java.util.Date;


public class UserTaskModel {
    private int UserTaskId, UserTaskUserId, UserTaskTaskId, UserTaskQuantity;
    private double UserTaskAmount;
    private Date UserTaskStartDate, UserTaskEndDate;

    public UserTaskModel(){}

    public UserTaskModel(int userTaskId, int userTaskUserId, int userTaskTaskId, int userTaskQuantity, double userTaskAmount, Date userTaskStartDate, Date userTaskEndDate){
        UserTaskId = userTaskId;
        UserTaskUserId = userTaskUserId;
        UserTaskTaskId = userTaskTaskId;
        UserTaskAmount = userTaskAmount;
        UserTaskQuantity = userTaskQuantity;
        UserTaskStartDate = userTaskStartDate;
        UserTaskEndDate = userTaskEndDate;
    }

    public int getUserTaskId(){
        return UserTaskId;
    }

    public void setUserTaskId(int userTaskId)
    {
        UserTaskId = userTaskId;
    }

    public int getUserTaskUserId(){
        return UserTaskUserId;
    }

    public void setUserTaskUserId(int userTaskUserId)
    {
        UserTaskUserId = userTaskUserId;
    }

    public int getUserTaskTaskId(){
        return UserTaskTaskId;
    }

    public void setUserTaskTaskId(int userTaskTaskId)
    {
        UserTaskTaskId = userTaskTaskId;
    }

    public int getUserTaskQuantity(){
        return UserTaskQuantity;
    }

    public void setUserTaskQuantity(int userTaskQuantity)
    {
        UserTaskQuantity = userTaskQuantity;
    }

    public double getUserTaskAmount(){
        return UserTaskAmount;
    }

    public void setUserTaskAmount(double userTaskAmount)
    {
        UserTaskAmount = userTaskAmount;
    }

    public Date getUserTaskStartDate(){
        return UserTaskStartDate;
    }

    public void setUserTaskStartDate(Date userTaskStartDate)
    {
        UserTaskStartDate = userTaskStartDate;
    }

    public Date getUserTaskEndDate(){
        return UserTaskEndDate;
    }

    public void setUserTaskEndDate(Date userTaskEndDate)
    {
        UserTaskEndDate = userTaskEndDate;
    }


}


