package in.dreamnation.salesgun.dbmodels;

import java.util.Date;


public class TaskModel {

    private int TaskId, TaskBrandId, TaskCredits, TaskQuantity, TaskStatus;
    private String TaskName, TaskDescription;
    private boolean TaskIsAmount, TaskIsQuantity;
    private double TaskAmount;
    private Date TaskCreatedDate, TaskStartDate, TaskEndDate;

    private int UserTaskQuantity;
    private double UserTaskAmount;
    private Date UserTaskStartDate, UserTaskEndDate;

    public TaskModel() {
    }

    public TaskModel(int taskId, String taskName, int taskBrandId, String taskDescription, int taskCredits, Date taskCreatedDate, Date taskStartDate, Date taskEndDate, boolean taskIsAmount, double taskAmount, boolean taskIsQuantity, int taskQuantity, int taskStatus) {
        TaskId = taskId;
        TaskName = taskName;
        TaskBrandId = taskBrandId;
        TaskDescription = taskDescription;
        TaskCredits = taskCredits;
        TaskCreatedDate = taskCreatedDate;
        TaskStartDate = taskStartDate;
        TaskEndDate = taskEndDate;
        TaskIsAmount = taskIsAmount;
        TaskAmount = taskAmount;
        TaskIsQuantity = taskIsQuantity;
        TaskQuantity = taskQuantity;
        TaskStatus = taskStatus;
    }

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int taskId) {
        TaskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public int getTaskStatus()
    {
        return TaskStatus;
    }

    public void setTaskStatus(int taskStatus)
    {
        TaskStatus = taskStatus;
    }

    public int getTaskBrandId() {
        return TaskBrandId;
    }

    public void setTaskBrandId(int taskBrandId) {
        TaskBrandId = taskBrandId;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public void setTaskDescription(String taskDescription)
    {
        TaskDescription = taskDescription;
    }

    public int getTaskCredits()
    {
        return TaskCredits;
    }

    public void setTaskCredits(int taskCredits)
    {
        TaskCredits = taskCredits;
    }

    public Date getTaskCreatedDate()
    {
        return  TaskCreatedDate;
    }

    public void setTaskCreatedDate(Date taskCreatedDate)
    {
        TaskCreatedDate = taskCreatedDate;
    }

    public Date getTaskStartDate()
    {
        return  TaskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate)
    {
        TaskStartDate = taskStartDate;
    }

    public Date getTaskEndDate()
    {
        return  TaskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate)
    {
        TaskEndDate = taskEndDate;
    }

    public boolean getTaskIsAmount()
    {
        return  TaskIsAmount;
    }

    public void setTaskIsAmount(boolean taskIsAmount)
    {
        TaskIsAmount = taskIsAmount;
    }

    public double getTaskAmount()
    {
        return  TaskAmount;
    }

    public void setTaskAmount(double taskAmount)
    {
        TaskAmount = taskAmount;
    }

    public boolean getTaskIsQuantity()
    {
        return  TaskIsQuantity;
    }

    public void setTaskIsQuantity(boolean taskIsQuantity)
    {
        TaskIsQuantity = taskIsQuantity;
    }

    public int getTaskQuantity()
    {
        return  TaskQuantity;
    }

    public void setTaskQuantity(int taskQuantity)
    {
        TaskQuantity = taskQuantity;
    }

    public Date getUserTaskStartDate()
    {
        return  UserTaskStartDate;
    }

    public void setUserTaskStartDate(Date userTaskStartDate)
    {
        UserTaskStartDate = userTaskStartDate;
    }

    public Date getUserTaskEndDate()
    {
        return  UserTaskEndDate;
    }

    public void setUserTaskEndDate(Date userTaskEndDate)
    {
        UserTaskEndDate = userTaskEndDate;
    }

    public double getUserTaskAmount()
    {
        return  UserTaskAmount;
    }

    public void setUserTaskAmount(double userTaskAmount)
    {
        UserTaskAmount = userTaskAmount;
    }

    public int getUserTaskQuantity()
    {
        return  UserTaskQuantity;
    }

    public void setUserTaskQuantity(int userTaskQuantity)
    {
        UserTaskQuantity = userTaskQuantity;
    }

}
