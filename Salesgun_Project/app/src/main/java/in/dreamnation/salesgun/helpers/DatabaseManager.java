package in.dreamnation.salesgun.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.dreamnation.salesgun.dbmodels.BrandModel;
import in.dreamnation.salesgun.dbmodels.TaskModel;
import in.dreamnation.salesgun.dbmodels.UserBrandModel;
import in.dreamnation.salesgun.dbmodels.UserModel;
import in.dreamnation.salesgun.dbmodels.UserTaskModel;


public class DatabaseManager extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseManager";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name

    private static final String DATABASE_NAME = "salesGun";

    // Table Names
    private static final String TABLE_BRAND = "brands";
    private static final String TABLE_TASK = "tasks";
    private static final String TABLE_USER_BRAND = "user_brands";
    private static final String TABLE_USER_TASKS = "user_tasks";
    private static final String TABLE_USER = "user";

    // Brands Table column names
    private static final String KEY_BRAND_ID = "brand_id";
    private static final String KEY_BRAND_NAME = "brand_name";
    private static final String KEY_BRAND_TAG_LINE = "brand_tag_line";
    private static final String KEY_BRAND_DESCRIPTION = "brand_description";
    private static final String KEY_BRAND_IMAGE = "brand_image";
    private static final String KEY_BRAND_CREATED_DATE = "brand_created_date";
    private static final String KEY_BRAND_MODIFIED_DATE = "brand_modified_date";

    // Tasks Table column names
    private static final String KEY_TASK_ID = "task_id";
    private static final String KEY_TASK_NAME = "task_name";
    private static final String KEY_TASK_BRAND_ID = "task_brand_id";
    private static final String KEY_TASK_DESCRIPTION = "task_description";
    private static final String KEY_TASK_CREDITS = "task_credits";
    private static final String KEY_TASK_CREATED_DATE = "task_created_date";
    private static final String KEY_TASK_START_DATE = "task_start_date";
    private static final String KEY_TASK_END_DATE = "task_end_date";
    private static final String KEY_TASK_IS_AMOUNT = "task_is_amount";
    private static final String KEY_TASK_AMOUNT = "task_amount";
    private static final String KEY_TASK_IS_QUANTITY = "task_is_quantity";
    private static final String KEY_TASK_QUANTITY = "task_quantity";

    // User Brand Table column names
    private static final String KEY_USER_BRAND_ID = "user_brand_id";
    private static final String KEY_USER_BRAND_USER_ID = "user_brand_user_id";
    private static final String KEY_USER_BRAND_BRAND_ID = "user_brand_brand_id";
    private static final String KEY_USER_BRAND_CREDITS = "user_brand_credits";
    private static final String KEY_USER_BRAND_START_DATE = "user_brand_start_date";
    private static final String KEY_USER_BRAND_END_DATE = "user_brand_end_date";


    // User Task Table column names
    private static final String KEY_USER_TASK_ID = "user_task_id";
    private static final String KEY_USER_TASK_USER_ID = "user_task_user_id";
    private static final String KEY_USER_TASK_TASK_ID = "user_task_task_id";
    private static final String KEY_USER_TASK_AMOUNT = "user_task_amount";
    private static final String KEY_USER_TASK_QUANTITY = "user_task_quantity";
    private static final String KEY_USER_TASK_START_DATE = "user_task_start_date";
    private static final String KEY_USER_TASK_END_DATE = "user_task_end_date";

    // User Table column names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_UNIQUE_ID = "user_unique_id";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_IMAGE = "user_image";


    // Table Create Statements
    // BRAND table create statement
    private static final String CREATE_TABLE_BRAND = "CREATE TABLE " + TABLE_BRAND + "("
            + KEY_BRAND_ID + " INTEGER PRIMARY KEY,"
            + KEY_BRAND_NAME + " TEXT,"
            + KEY_BRAND_TAG_LINE + " TEXT,"
            + KEY_BRAND_DESCRIPTION + " TEXT,"
            + KEY_BRAND_IMAGE + " TEXT" + ")";

    // TASK table create statement
    private static final String CREATE_TABLE_TASK = "CREATE TABLE " + TABLE_TASK + "("
            + KEY_TASK_ID + " INTEGER PRIMARY KEY,"
            + KEY_TASK_NAME + " TEXT,"
            + KEY_TASK_BRAND_ID + " INTEGER,"
            + KEY_TASK_DESCRIPTION + " TEXT,"
            + KEY_TASK_CREDITS + " INTEGER,"
            + KEY_TASK_CREATED_DATE + " DATETIME,"
            + KEY_TASK_START_DATE + " DATETIME,"
            + KEY_TASK_END_DATE + " DATETIME,"
            + KEY_TASK_IS_AMOUNT + " BOOLEAN,"
            + KEY_TASK_AMOUNT + " DOUBLE,"
            + KEY_TASK_IS_QUANTITY + " BOOLEAN,"
            + KEY_TASK_QUANTITY + " INTEGER" + ")";

    // User Brand table create statement
    private static final String CREATE_TABLE_USER_BRAND = "CREATE TABLE " + TABLE_USER_BRAND + "("
            + KEY_USER_BRAND_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_BRAND_BRAND_ID + " INTEGER,"
            + KEY_USER_BRAND_USER_ID + " INTEGER,"
            + KEY_USER_BRAND_CREDITS + " INTEGER,"
            + KEY_USER_BRAND_START_DATE + " DATETIME,"
            + KEY_USER_BRAND_END_DATE + " DATETIME" + ")";

    // User Task table create statement
    private static final String CREATE_TABLE_USER_TASK = "CREATE TABLE " + TABLE_USER_TASKS + "("
            + KEY_USER_TASK_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_TASK_USER_ID + " INTEGER,"
            + KEY_USER_TASK_TASK_ID + " INTEGER,"
            + KEY_USER_TASK_AMOUNT + " DOUBLE,"
            + KEY_USER_TASK_QUANTITY + " INTEGER,"
            + KEY_USER_TASK_START_DATE + " DATETIME,"
            + KEY_USER_TASK_END_DATE + " DATETIME" + ")";

    // User table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + KEY_USER_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_NAME + " TEXT,"
            + KEY_USER_UNIQUE_ID + " TEXT,"
            + KEY_USER_PHONE + " TEXT,"
            + KEY_USER_EMAIL + " TEXT,"
            + KEY_USER_IMAGE + " BLOB" + ")";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_BRAND);
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_USER_BRAND);
        db.execSQL(CREATE_TABLE_USER_TASK);
        db.execSQL(CREATE_TABLE_USER);
    }

    public void CreateBrandsTable(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_BRAND);
    }

    public void CreateTasksTable(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_TASK);
    }

    public void CreateUserBrandsTable(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_USER_BRAND);
    }

    public void CreateUserTasksTable(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_USER_TASK);
    }

    public void CreateUsersTable(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRAND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_BRAND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // create new tables
        onCreate(db);
    }

    public void DropBrandsTable(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BRAND);
        CreateBrandsTable(db);
    }

    public void DropTasksTable(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TASK);
        CreateTasksTable(db);
    }

    public void DropUserBrandsTable(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER_BRAND);
        CreateUserBrandsTable(db);
    }

    public void DropUserTasksTable(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER_TASK);
        CreateUserTasksTable(db);
    }

    public void DropUsersTable(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER);
        CreateUsersTable(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public SQLiteDatabase getDatabase()
    {
        return this.getWritableDatabase();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * get datetime
     * */
    private String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        if(date  == null)
        {
            date = new Date();
        }
        return dateFormat.format(date);
    }

    /**
     * Creating a User
     */
    public long createUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getUserId());
        values.put(KEY_USER_NAME, user.getUserName());
        values.put(KEY_USER_UNIQUE_ID, user.getUniqueId());
        values.put(KEY_USER_PHONE, user.getPhone());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_IMAGE, user.getImage());

        // insert row
        long userId = db.insert(TABLE_USER, null, values);

        return userId;
    }

    /**
     * get single User
     */
    public UserModel getTodo(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE "
                + KEY_USER_ID + " = " + userId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        UserModel td = new UserModel();
        td.setUserId(c.getInt(c.getColumnIndex(KEY_USER_ID)));
        td.setUserName((c.getString(c.getColumnIndex(KEY_USER_NAME))));
        td.setUniqueId(c.getString(c.getColumnIndex(KEY_USER_UNIQUE_ID)));
        td.setPhone((c.getString(c.getColumnIndex(KEY_USER_PHONE))));
        td.setEmail(c.getString(c.getColumnIndex(KEY_USER_EMAIL)));
        td.setImage(c.getBlob(c.getColumnIndex(KEY_USER_IMAGE)));

        return td;
    }

    /**
     * getting all Users
     * */
    public List<UserModel> getAllUsers() {
        List<UserModel> userModels = new ArrayList<UserModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel td = new UserModel();
                td.setUserId(c.getInt(c.getColumnIndex(KEY_USER_ID)));
                td.setUserName((c.getString(c.getColumnIndex(KEY_USER_NAME))));
                td.setUniqueId(c.getString(c.getColumnIndex(KEY_USER_UNIQUE_ID)));
                td.setPhone((c.getString(c.getColumnIndex(KEY_USER_PHONE))));
                td.setEmail(c.getString(c.getColumnIndex(KEY_USER_EMAIL)));
                td.setImage(c.getBlob(c.getColumnIndex(KEY_USER_IMAGE)));

                // adding to userModels list
                userModels.add(td);
            } while (c.moveToNext());
        }

        return userModels;
    }

    /**
     * getting User count
     */
    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a User
     */
    public int updateUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getUserId());
        values.put(KEY_USER_NAME, user.getUserName());
        values.put(KEY_USER_UNIQUE_ID, user.getUniqueId());
        values.put(KEY_USER_PHONE, user.getPhone());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_IMAGE, user.getImage());


        // updating row
        return db.update(TABLE_USER, values, KEY_USER_ID + " = ?",
                new String[] { String.valueOf(user.getUserId()) });
    }







    /**
     * Deleting a user
     */
    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_USER_ID + " = ?",
                new String[] { String.valueOf(userId) });
    }


    /**
     * Creating a Brand
     */
    public long createBrand(BrandModel brand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BRAND_ID, brand.getBrandId());
        values.put(KEY_BRAND_NAME, brand.getBrandName());
        values.put(KEY_BRAND_TAG_LINE, brand.getBrandTagLine());
        values.put(KEY_BRAND_DESCRIPTION, brand.getBrandDescription());
        values.put(KEY_BRAND_IMAGE, brand.getBrandImage());


        // insert row
        long userId = db.insert(TABLE_BRAND, null, values);

        return userId;
    }

    /**
     * get single Brand
     */
    public BrandModel getBrand(int brandId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_BRAND + " WHERE "
                + KEY_BRAND_ID + " = " + brandId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        BrandModel td = new BrandModel();
        td.setBrandId(c.getInt(c.getColumnIndex(KEY_BRAND_ID)));
        td.setBrandName((c.getString(c.getColumnIndex(KEY_BRAND_NAME))));
        td.setBrandTagLine(c.getString(c.getColumnIndex(KEY_BRAND_TAG_LINE)));
        td.setBrandDescription((c.getString(c.getColumnIndex(KEY_BRAND_DESCRIPTION))));
        td.setBrandImage(c.getString(c.getColumnIndex(KEY_BRAND_IMAGE)));


        return td;
    }

    /**
     * getting all Brands
     * */
    public List<BrandModel> getAllBrands() {
        List<BrandModel> brandModels = new ArrayList<BrandModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_BRAND;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                BrandModel td = new BrandModel();
                td.setBrandId(c.getInt(c.getColumnIndex(KEY_BRAND_ID)));
                td.setBrandName((c.getString(c.getColumnIndex(KEY_BRAND_NAME))));
                td.setBrandTagLine(c.getString(c.getColumnIndex(KEY_BRAND_TAG_LINE)));
                td.setBrandDescription((c.getString(c.getColumnIndex(KEY_BRAND_DESCRIPTION))));
                td.setBrandImage(c.getString(c.getColumnIndex(KEY_BRAND_IMAGE)));

                // adding to brandModels list
                brandModels.add(td);
            } while (c.moveToNext());
        }

        return brandModels;
    }

    /**
     * getting Brands count
     */
    public int getBrandCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BRAND;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        Log.e("tester"," " + cursor);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a Brand
     */
    public int updateBrand(BrandModel brand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BRAND_ID, brand.getBrandId());
        values.put(KEY_BRAND_NAME, brand.getBrandName());
        values.put(KEY_BRAND_TAG_LINE, brand.getBrandTagLine());
        values.put(KEY_BRAND_DESCRIPTION, brand.getBrandDescription());
        values.put(KEY_BRAND_IMAGE, brand.getBrandImage());


        // updating row
        return db.update(TABLE_BRAND, values, KEY_BRAND_ID + " = ?",
                new String[] { String.valueOf(brand.getBrandId()) });
    }

    /**
     * Deleting a brand
     */
    public void deleteBrand(int brandId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BRAND, KEY_BRAND_ID + " = ?",
                new String[] { String.valueOf(brandId) });
    }

    /**
     * Creating a Task
     */
    public long createTask(TaskModel task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_ID, task.getTaskId());
        values.put(KEY_TASK_NAME, task.getTaskName());
        values.put(KEY_TASK_BRAND_ID, task.getTaskBrandId());
        values.put(KEY_TASK_DESCRIPTION, task.getTaskDescription());
        values.put(KEY_TASK_CREDITS, task.getTaskCredits());
        values.put(KEY_TASK_CREATED_DATE, getDateTime(task.getTaskCreatedDate()));
        values.put(KEY_TASK_START_DATE, getDateTime(task.getTaskStartDate()));
        values.put(KEY_TASK_END_DATE, getDateTime(task.getTaskEndDate()));
        values.put(KEY_TASK_IS_AMOUNT, task.getTaskIsAmount());
        values.put(KEY_TASK_AMOUNT, task.getTaskAmount());
        values.put(KEY_TASK_IS_QUANTITY, task.getTaskIsQuantity());
        values.put(KEY_TASK_QUANTITY, task.getTaskQuantity());

        // insert row
        long userId = db.insert(TABLE_TASK, null, values);

        return userId;
    }

    /**
     * get single Task
     */
    public TaskModel getTask(int taskId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TASK + " WHERE "
                + KEY_TASK_ID + " = " + taskId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        TaskModel td = new TaskModel();
        td.setTaskId(c.getInt(c.getColumnIndex(KEY_TASK_ID)));
        td.setTaskName((c.getString(c.getColumnIndex(KEY_TASK_NAME))));
        td.setTaskBrandId((c.getInt(c.getColumnIndex(KEY_TASK_BRAND_ID))));
        td.setTaskDescription(c.getString(c.getColumnIndex(KEY_TASK_DESCRIPTION)));
        td.setTaskCredits((c.getInt(c.getColumnIndex(KEY_TASK_CREDITS))));
        td.setTaskCreatedDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_CREATED_DATE))));
        td.setTaskStartDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_START_DATE))));
        td.setTaskEndDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_END_DATE))));
        td.setTaskIsAmount(toBoolean((c.getInt(c.getColumnIndex(KEY_TASK_IS_AMOUNT)))));
        td.setTaskAmount(c.getInt(c.getColumnIndex(KEY_TASK_AMOUNT)));
        td.setTaskIsQuantity(toBoolean(c.getInt(c.getColumnIndex(KEY_TASK_IS_QUANTITY))));
        td.setTaskQuantity(c.getInt(c.getColumnIndex(KEY_TASK_QUANTITY)));

        return td;
    }

    private boolean toBoolean(int i)
    {
        return i==1? true : false;
    }

    /**
     * getting all Tasks
     * */
    public List<TaskModel> getAllTasks() {
        List<TaskModel> taskModels = new ArrayList<TaskModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TaskModel td = new TaskModel();
                td.setTaskId(c.getInt(c.getColumnIndex(KEY_TASK_ID)));
                td.setTaskName((c.getString(c.getColumnIndex(KEY_TASK_NAME))));
                td.setTaskBrandId((c.getInt(c.getColumnIndex(KEY_TASK_BRAND_ID))));
                td.setTaskDescription(c.getString(c.getColumnIndex(KEY_TASK_DESCRIPTION)));
                td.setTaskCredits((c.getInt(c.getColumnIndex(KEY_TASK_CREDITS))));
                td.setTaskCreatedDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_CREATED_DATE))));
                td.setTaskStartDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_START_DATE))));
                td.setTaskEndDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_END_DATE))));
                td.setTaskIsAmount(toBoolean((c.getInt(c.getColumnIndex(KEY_TASK_IS_AMOUNT)))));
                td.setTaskAmount(c.getInt(c.getColumnIndex(KEY_TASK_AMOUNT)));
                td.setTaskIsQuantity(toBoolean(c.getInt(c.getColumnIndex(KEY_TASK_IS_QUANTITY))));
                td.setTaskQuantity(c.getInt(c.getColumnIndex(KEY_TASK_QUANTITY)));

                // adding to taskModels list
                taskModels.add(td);
            } while (c.moveToNext());
        }

        return taskModels;
    }

    /**
     * getting Tasks count
     */
    public int getTaskCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a Task
     */
    public int updateTask(TaskModel task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_ID, task.getTaskId());
        values.put(KEY_TASK_NAME, task.getTaskName());
        values.put(KEY_TASK_BRAND_ID, task.getTaskBrandId());
        values.put(KEY_TASK_DESCRIPTION, task.getTaskDescription());
        values.put(KEY_TASK_CREDITS, task.getTaskCredits());
        values.put(KEY_TASK_CREATED_DATE, getDateTime(task.getTaskCreatedDate()));
        values.put(KEY_TASK_START_DATE, getDateTime(task.getTaskStartDate()));
        values.put(KEY_TASK_END_DATE, getDateTime(task.getTaskEndDate()));
        values.put(KEY_TASK_IS_AMOUNT, task.getTaskIsAmount());
        values.put(KEY_TASK_AMOUNT, task.getTaskAmount());
        values.put(KEY_TASK_IS_QUANTITY, task.getTaskIsQuantity());
        values.put(KEY_TASK_QUANTITY, task.getTaskQuantity());


        // updating row
        return db.update(TABLE_TASK, values, KEY_TASK_ID + " = ?",
                new String[] { String.valueOf(task.getTaskId()) });
    }

    /**
     * Deleting a task
     */
    public void deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_TASK_ID + " = ?",
                new String[] { String.valueOf(taskId) });
    }


    /**
     * Creating a User Brand
     */
    public long createUserBrand(UserBrandModel brand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_BRAND_ID, brand.getUserBrandId());
        values.put(KEY_USER_BRAND_USER_ID, brand.getUserBrandUserId());
        values.put(KEY_USER_BRAND_BRAND_ID, brand.getUserBrandBrandId());
        values.put(KEY_USER_BRAND_CREDITS, brand.getUserBrandCredits());
        values.put(KEY_USER_BRAND_START_DATE, getDateTime(brand.getUserBrandStartDate()));
        values.put(KEY_USER_BRAND_END_DATE, getDateTime(brand.getUserBrandEndDate()));

        // insert row
        long userBrandId = db.insert(TABLE_BRAND, null, values);

        return userBrandId;
    }

    /**
     * get single User Brand
     */
    public BrandModel getUserBrand(int userBrandId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_BRAND + " WHERE "
                + KEY_USER_BRAND_ID + " = " + userBrandId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        UserBrandModel td = new UserBrandModel();
        td.setUserBrandId(c.getInt(c.getColumnIndex(KEY_USER_BRAND_ID)));
        td.setUserBrandUserId(c.getInt(c.getColumnIndex(KEY_USER_BRAND_USER_ID)));
        td.setUserBrandBrandId((c.getInt(c.getColumnIndex(KEY_USER_BRAND_BRAND_ID))));
        td.setUserBrandCredits(c.getInt(c.getColumnIndex(KEY_USER_BRAND_CREDITS)));
        td.setUserBrandStartDate(new Date(c.getString(c.getColumnIndex(KEY_USER_BRAND_START_DATE))));
        td.setUserBrandEndDate(new Date(c.getString(c.getColumnIndex(KEY_USER_BRAND_END_DATE))));

        String selectBrandQuery = "SELECT  * FROM " + TABLE_BRAND + " WHERE "
                + KEY_USER_BRAND_USER_ID + " = " + td.getUserBrandUserId();

        Log.e(LOG, selectBrandQuery);

        c = db.rawQuery(selectBrandQuery, null);

        if (c != null)
            c.moveToFirst();

        BrandModel td1 = new BrandModel();
        td1.setBrandId(c.getInt(c.getColumnIndex(KEY_BRAND_ID)));
        td1.setBrandName((c.getString(c.getColumnIndex(KEY_BRAND_NAME))));
        td1.setBrandTagLine(c.getString(c.getColumnIndex(KEY_BRAND_TAG_LINE)));
        td1.setBrandDescription((c.getString(c.getColumnIndex(KEY_BRAND_DESCRIPTION))));
        td1.setBrandImage(c.getString(c.getColumnIndex(KEY_BRAND_IMAGE)));
        td1.setBrandCreatedDate(new Date(c.getString(c.getColumnIndex(KEY_BRAND_CREATED_DATE))));
        td1.setBrandModifiedDate(new Date(c.getString(c.getColumnIndex(KEY_BRAND_MODIFIED_DATE))));

        td1.setUserBrandCredits(td.getUserBrandCredits());
        td1.setUserBrandStartDate(td.getUserBrandStartDate());
        td1.setUserBrandEndDate(td.getUserBrandEndDate());

        return td1;
    }

    /**
     * getting all User Brands
     * */
    public List<BrandModel> getAllUserBrands() {
        List<BrandModel> brandModels = new ArrayList<BrandModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER_BRAND ;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserBrandModel td = new UserBrandModel();
                td.setUserBrandId(c.getInt(c.getColumnIndex(KEY_USER_BRAND_ID)));
                td.setUserBrandUserId(c.getInt(c.getColumnIndex(KEY_USER_BRAND_USER_ID)));
                td.setUserBrandBrandId((c.getInt(c.getColumnIndex(KEY_USER_BRAND_BRAND_ID))));
                td.setUserBrandCredits(c.getInt(c.getColumnIndex(KEY_USER_BRAND_CREDITS)));
                td.setUserBrandStartDate(new Date(c.getString(c.getColumnIndex(KEY_USER_BRAND_START_DATE))));
                td.setUserBrandEndDate(new Date(c.getString(c.getColumnIndex(KEY_USER_BRAND_END_DATE))));

                String selectBrandQuery = "SELECT  * FROM " + TABLE_BRAND + " WHERE "
                        + KEY_USER_BRAND_BRAND_ID + " = " + td.getUserBrandBrandId();

                Log.e(LOG, selectBrandQuery);

                c = db.rawQuery(selectBrandQuery, null);

                if (c != null)
                    c.moveToFirst();

                BrandModel td1 = new BrandModel();
                td1.setBrandId(c.getInt(c.getColumnIndex(KEY_BRAND_ID)));
                td1.setBrandName((c.getString(c.getColumnIndex(KEY_BRAND_NAME))));
                td1.setBrandTagLine(c.getString(c.getColumnIndex(KEY_BRAND_TAG_LINE)));
                td1.setBrandDescription((c.getString(c.getColumnIndex(KEY_BRAND_DESCRIPTION))));
                td1.setBrandImage(c.getString(c.getColumnIndex(KEY_BRAND_IMAGE)));
                td1.setBrandCreatedDate(new Date(c.getString(c.getColumnIndex(KEY_BRAND_CREATED_DATE))));
                td1.setBrandModifiedDate(new Date(c.getString(c.getColumnIndex(KEY_BRAND_MODIFIED_DATE))));

                td1.setUserBrandCredits(td.getUserBrandCredits());
                td1.setUserBrandStartDate(td.getUserBrandStartDate());
                td1.setUserBrandEndDate(td.getUserBrandEndDate());

                // adding to brandModels list
                brandModels.add(td1);
            } while (c.moveToNext());
        }

        return brandModels;
    }

    /**
     * getting User Brands count
     */
    public int getUserBrandCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER_BRAND;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a User Brand
     */
    public int updateUserBrand(UserBrandModel brand) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_BRAND_ID, brand.getUserBrandId());
        values.put(KEY_USER_BRAND_USER_ID, brand.getUserBrandUserId());
        values.put(KEY_USER_BRAND_BRAND_ID, brand.getUserBrandBrandId());
        values.put(KEY_USER_BRAND_CREDITS, brand.getUserBrandCredits());
        values.put(KEY_USER_BRAND_START_DATE, getDateTime(brand.getUserBrandStartDate()));
        values.put(KEY_USER_BRAND_END_DATE, getDateTime(brand.getUserBrandEndDate()));


        // updating row
        return db.update(TABLE_USER_BRAND, values, KEY_USER_BRAND_ID + " = ?",
                new String[] { String.valueOf(brand.getUserBrandId()) });
    }

    /**
     * Deleting a user brand
     */
    public void deleteUserBrand(int userBrandId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_BRAND, KEY_USER_BRAND_ID + " = ?",
                new String[] { String.valueOf(userBrandId) });
    }

    /**
     * Creating a User Task
     */
    public long createUserTask(UserTaskModel task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_TASK_ID, task.getUserTaskId());
        values.put(KEY_USER_TASK_USER_ID, task.getUserTaskUserId());
        values.put(KEY_USER_TASK_TASK_ID, task.getUserTaskTaskId());
        values.put(KEY_USER_TASK_AMOUNT, task.getUserTaskAmount());
        values.put(KEY_USER_TASK_QUANTITY, task.getUserTaskAmount());
        values.put(KEY_USER_TASK_START_DATE, getDateTime(task.getUserTaskStartDate()));
        values.put(KEY_USER_TASK_END_DATE, getDateTime(task.getUserTaskEndDate()));

        // insert row
        long userTaskId = db.insert(TABLE_USER_TASKS, null, values);

        return userTaskId;
    }

    /**
     * get single User Task
     */
    public TaskModel getUserTask(int userTaskId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER_TASKS + " WHERE "
                + KEY_USER_TASK_ID + " = " + userTaskId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();


        UserTaskModel td = new UserTaskModel();
        td.setUserTaskId(c.getInt(c.getColumnIndex(KEY_USER_TASK_ID)));
        td.setUserTaskUserId((c.getInt(c.getColumnIndex(KEY_USER_TASK_USER_ID))));
        td.setUserTaskTaskId((c.getInt(c.getColumnIndex(KEY_USER_TASK_TASK_ID))));
        td.setUserTaskAmount(c.getDouble(c.getColumnIndex(KEY_USER_TASK_AMOUNT)));
        td.setUserTaskQuantity((c.getInt(c.getColumnIndex(KEY_USER_TASK_QUANTITY))));
        td.setUserTaskStartDate(new Date(c.getString(c.getColumnIndex(KEY_USER_TASK_START_DATE))));
        td.setUserTaskEndDate(new Date(c.getString(c.getColumnIndex(KEY_USER_TASK_END_DATE))));

        String selectUserTaskQuery = "SELECT  * FROM " + TABLE_TASK + " WHERE "
                + KEY_TASK_ID + " = " + td.getUserTaskTaskId();

        Log.e(LOG, selectQuery);

        c = db.rawQuery(selectUserTaskQuery, null);

        if (c != null)
            c.moveToFirst();

        TaskModel td1 = new TaskModel();
        td1.setTaskId(c.getInt(c.getColumnIndex(KEY_TASK_ID)));
        td1.setTaskName((c.getString(c.getColumnIndex(KEY_TASK_NAME))));
        td1.setTaskBrandId((c.getInt(c.getColumnIndex(KEY_TASK_BRAND_ID))));
        td1.setTaskDescription(c.getString(c.getColumnIndex(KEY_TASK_DESCRIPTION)));
        td1.setTaskCredits((c.getInt(c.getColumnIndex(KEY_TASK_CREDITS))));
        td1.setTaskCreatedDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_CREATED_DATE))));
        td1.setTaskStartDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_START_DATE))));
        td1.setTaskEndDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_END_DATE))));
        td1.setTaskIsAmount(toBoolean((c.getInt(c.getColumnIndex(KEY_TASK_IS_AMOUNT)))));
        td1.setTaskAmount(c.getInt(c.getColumnIndex(KEY_TASK_AMOUNT)));
        td1.setTaskIsQuantity(toBoolean(c.getInt(c.getColumnIndex(KEY_TASK_IS_QUANTITY))));
        td1.setTaskQuantity(c.getInt(c.getColumnIndex(KEY_TASK_QUANTITY)));

        td1.setUserTaskAmount(td.getUserTaskAmount());
        td1.setUserTaskQuantity(td.getUserTaskQuantity());
        td1.setUserTaskStartDate(td.getUserTaskStartDate());
        td1.setUserTaskEndDate(td.getUserTaskEndDate());

        return td1;
    }

    /**
     * getting all UserTasks
     * */
    public List<TaskModel> getAllUserTasks() {
        List<TaskModel> taskModels = new ArrayList<TaskModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER_TASKS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserTaskModel td = new UserTaskModel();
                td.setUserTaskId(c.getInt(c.getColumnIndex(KEY_USER_TASK_ID)));
                td.setUserTaskUserId((c.getInt(c.getColumnIndex(KEY_USER_TASK_USER_ID))));
                td.setUserTaskTaskId((c.getInt(c.getColumnIndex(KEY_USER_TASK_TASK_ID))));
                td.setUserTaskAmount(c.getDouble(c.getColumnIndex(KEY_USER_TASK_AMOUNT)));
                td.setUserTaskQuantity((c.getInt(c.getColumnIndex(KEY_USER_TASK_QUANTITY))));
                td.setUserTaskStartDate(new Date(c.getString(c.getColumnIndex(KEY_USER_TASK_START_DATE))));
                td.setUserTaskEndDate(new Date(c.getString(c.getColumnIndex(KEY_USER_TASK_END_DATE))));

                String selectUserTaskQuery = "SELECT  * FROM " + TABLE_TASK + " WHERE "
                        + KEY_TASK_ID + " = " + td.getUserTaskTaskId();

                Log.e(LOG, selectQuery);

                c = db.rawQuery(selectUserTaskQuery, null);

                if (c != null)
                    c.moveToFirst();

                TaskModel td1 = new TaskModel();
                td1.setTaskId(c.getInt(c.getColumnIndex(KEY_TASK_ID)));
                td1.setTaskName((c.getString(c.getColumnIndex(KEY_TASK_NAME))));
                td1.setTaskBrandId((c.getInt(c.getColumnIndex(KEY_TASK_BRAND_ID))));
                td1.setTaskDescription(c.getString(c.getColumnIndex(KEY_TASK_DESCRIPTION)));
                td1.setTaskCredits((c.getInt(c.getColumnIndex(KEY_TASK_CREDITS))));
                td1.setTaskCreatedDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_CREATED_DATE))));
                td1.setTaskStartDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_START_DATE))));
                td1.setTaskEndDate(new Date(c.getString(c.getColumnIndex(KEY_TASK_END_DATE))));
                td1.setTaskIsAmount(toBoolean((c.getInt(c.getColumnIndex(KEY_TASK_IS_AMOUNT)))));
                td1.setTaskAmount(c.getInt(c.getColumnIndex(KEY_TASK_AMOUNT)));
                td1.setTaskIsQuantity(toBoolean(c.getInt(c.getColumnIndex(KEY_TASK_IS_QUANTITY))));
                td1.setTaskQuantity(c.getInt(c.getColumnIndex(KEY_TASK_QUANTITY)));

                td1.setUserTaskAmount(td.getUserTaskAmount());
                td1.setUserTaskQuantity(td.getUserTaskQuantity());
                td1.setUserTaskStartDate(td.getUserTaskStartDate());
                td1.setUserTaskEndDate(td.getUserTaskEndDate());

                // adding to taskModels list
                taskModels.add(td1);
            } while (c.moveToNext());
        }

        return taskModels;
    }

    /**
     * getting User Tasks count
     */
    public int getUserTaskCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating a User Task
     */
    public int updateUserTask(UserTaskModel task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_TASK_ID, task.getUserTaskId());
        values.put(KEY_USER_TASK_USER_ID, task.getUserTaskUserId());
        values.put(KEY_USER_TASK_TASK_ID, task.getUserTaskTaskId());
        values.put(KEY_USER_TASK_AMOUNT, task.getUserTaskAmount());
        values.put(KEY_USER_TASK_QUANTITY, task.getUserTaskAmount());
        values.put(KEY_USER_TASK_START_DATE, getDateTime(task.getUserTaskStartDate()));
        values.put(KEY_USER_TASK_END_DATE, getDateTime(task.getUserTaskEndDate()));


        // updating row
        return db.update(TABLE_USER_TASKS, values, KEY_USER_TASK_ID + " = ?",
                new String[] { String.valueOf(task.getUserTaskId()) });
    }

    /**
     * Deleting a user task
     */
    public void deleteUserTask(int userTaskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_TASKS, KEY_USER_TASK_ID + " = ?",
                new String[] { String.valueOf(userTaskId) });
    }


}
