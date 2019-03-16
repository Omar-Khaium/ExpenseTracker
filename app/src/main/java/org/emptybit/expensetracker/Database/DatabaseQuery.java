package org.emptybit.expensetracker.Database;

import android.content.ContentValues;
import android.database.Cursor;

import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.Model.CategoryModel;
import org.emptybit.expensetracker.Model.DeleteModel;
import org.emptybit.expensetracker.Model.InsertModel;
import org.emptybit.expensetracker.Model.LoginModel;
import org.emptybit.expensetracker.Model.SubCategoryModel;
import org.emptybit.expensetracker.Model.UpdateModel;
import org.emptybit.expensetracker.Model.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.emptybit.expensetracker.Activity.LoginActivity.USER_ID;
import static org.emptybit.expensetracker.Enum.ACTIVE;
import static org.emptybit.expensetracker.Enum.DEPOSIT;
import static org.emptybit.expensetracker.Enum.WITHDRAW;

public class DatabaseQuery {
    /*Database name*/
    public static final String DATABASE_NAME = "Expense_tracker.db";

    /*Login Table's declaration*/
    public static final String TABLE_LOGIN = "login";
    public static final String COLUMN_LOGIN_ID = "pk_id";
    public static final String COLUMN_LOGIN_USER_ID = "fk_user_id";
    public static final String COLUMN_LOGIN_USERNAME = "username";
    public static final String COLUMN_LOGIN_PASSWORD = "password";

    /*User Table's declaration*/
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "pk_id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_START_DATE = "start_date";

    /*Account Table's declaration*/
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_ACCOUNT_ID = "pk_id";
    public static final String COLUMN_ACCOUNT_USER_ID = "fk_user_id";
    public static final String COLUMN_ACCOUNT_SUB_CATEGORY_ID = "fk_sub_category_id";
    public static final String COLUMN_ACCOUNT_PRICE = "price";
    public static final String COLUMN_ACCOUNT_DATE = "date";
    public static final String COLUMN_ACCOUNT_TRANSACTION_TYPE = "transaction_type";

    /*Category Table's declaration*/
    public static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_CATEGORY_ID = "pk_id";
    public static final String COLUMN_CATEGORY_NAME = "name";
    public static final String COLUMN_CATEGORY_IS_ACTIVE = "is_active";
    public static final String COLUMN_CATEGORY_CREATION_DATE = "creation_date";

    /*Sub-category Table's declaration*/
    public static final String TABLE_SUB_CATEGORIES = "sub_categories";
    public static final String COLUMN_SUB_CATEGORY_ID = "pk_id";
    public static final String COLUMN_SUB_CATEGORY_CATEGORY_ID = "fk_category_id";
    public static final String COLUMN_SUB_CATEGORY_NAME = "name";
    public static final String COLUMN_SUB_CATEGORY_IS_ACTIVE = "is_active";
    public static final String COLUMN_SUB_CATEGORY_CREATION_DATE = "creation_date";

    /*Tracker Table's declaration*/
    public static final String TABLE_TRACKERS_HISTORY = "trackers_history";
    public static final String COLUMN_TRACKERS_HISTORY_ID = "pk_id";
    public static final String COLUMN_TRACKERS_HISTORY_USER_ID = "fk_user_id";
    public static final String COLUMN_TRACKERS_HISTORY_SUB_CATEGORY_ID = "fk_sub_category_id";
    public static final String COLUMN_TRACKERS_HISTORY_DATE = "date";

    /*Database's version*/
    public static final int VERSION = 1;

    public static final String CREATE_LOGIN_TABLE = "CREATE TABLE \"" + TABLE_LOGIN + "\" (" +
            "\"" + COLUMN_LOGIN_ID + "\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "\"" + COLUMN_LOGIN_USERNAME + "\" VARCHAR(32), " +
            "\"" + COLUMN_LOGIN_PASSWORD + "\" VARCHAR(32), " +
            "\"" + COLUMN_LOGIN_USER_ID + "\" INTEGER, " +
            "FOREIGN KEY(\"" + COLUMN_LOGIN_USER_ID + "\") REFERENCES \"" + TABLE_USERS + "\"(\"" + COLUMN_USER_ID + "\")" +
            ");";

    public static final String CREATE_USER_TABLE = "CREATE TABLE \"" + TABLE_USERS + "\" (" +
            "\"" + COLUMN_USER_ID + "\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "\"" + COLUMN_USER_NAME + "\" VARCHAR(64), " +
            "\"" + COLUMN_USER_USERNAME + "\" VARCHAR(32), " +
            "\"" + COLUMN_USER_PASSWORD + "\" VARCHAR(32), " +
            "\"" + COLUMN_USER_GENDER + "\" VARCHAR(32), " +
            "\"" + COLUMN_USER_START_DATE + "\" VARCHAR(32)" +
            ");";

    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE \"" + TABLE_CATEGORIES + "\" (" +
            "\"" + COLUMN_CATEGORY_ID + "\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "\"" + COLUMN_CATEGORY_NAME + "\" VARCHAR(64), " +
            "\"" + COLUMN_CATEGORY_IS_ACTIVE + "\" INTEGER, " +
            "\"" + COLUMN_CATEGORY_CREATION_DATE + "\" VARCHAR(32)" +
            ");";

    public static final String CREATE_SUB_CATEGORY_TABLE = "CREATE TABLE \"" + TABLE_SUB_CATEGORIES + "\" (" +
            "\"" + COLUMN_SUB_CATEGORY_ID + "\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "\"" + COLUMN_SUB_CATEGORY_NAME + "\" VARCHAR(32), " +
            "\"" + COLUMN_SUB_CATEGORY_CATEGORY_ID + "\" INTEGER, " +
            "\"" + COLUMN_SUB_CATEGORY_CREATION_DATE + "\" VARCHAR(32), " +
            "\"" + COLUMN_SUB_CATEGORY_IS_ACTIVE + "\" INTEGER, " +
            "FOREIGN KEY(\"" + COLUMN_SUB_CATEGORY_CATEGORY_ID + "\") REFERENCES \"" + TABLE_CATEGORIES + "\"(\"" + COLUMN_CATEGORY_ID + "\")" +
            ");";

    public static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE \"" + TABLE_ACCOUNTS + "\" (" +
            "\"" + COLUMN_ACCOUNT_ID + "\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "\"" + COLUMN_ACCOUNT_PRICE + "\" INTEGER, " +
            "\"" + COLUMN_ACCOUNT_TRANSACTION_TYPE + "\" INTEGER, " +
            "\"" + COLUMN_ACCOUNT_DATE + "\" TEXT, " +
            "\"" + COLUMN_ACCOUNT_USER_ID + "\" INTEGER, " +
            "\"" + COLUMN_ACCOUNT_SUB_CATEGORY_ID + "\" INTEGER, " +
            "FOREIGN KEY(\"" + COLUMN_ACCOUNT_USER_ID + "\") REFERENCES \"" + TABLE_USERS + "\"(\"" + COLUMN_USER_ID + "\"), " +
            "FOREIGN KEY(\"" + COLUMN_ACCOUNT_USER_ID + "\") REFERENCES \"" + TABLE_SUB_CATEGORIES + "\"(\"" + COLUMN_SUB_CATEGORY_ID + "\") " +
            ");";

    public static final String CREATE_TRACKERS_HISTORY_TABLE = "CREATE TABLE \"" + TABLE_TRACKERS_HISTORY + "\" (" +
            "\"" + COLUMN_TRACKERS_HISTORY_ID + "\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "\"" + COLUMN_TRACKERS_HISTORY_USER_ID + "\" INTEGER, " +
            "\"" + COLUMN_TRACKERS_HISTORY_SUB_CATEGORY_ID + "\" INTEGER, " +
            "\"" + COLUMN_TRACKERS_HISTORY_DATE + "\" TEXT, " +
            "FOREIGN KEY(\"" + COLUMN_TRACKERS_HISTORY_USER_ID + "\") REFERENCES \"" + TABLE_USERS + "\"(\"" + COLUMN_USER_ID + "\"), " +
            "FOREIGN KEY(\"" + COLUMN_TRACKERS_HISTORY_USER_ID + "\") REFERENCES \"" + TABLE_SUB_CATEGORIES + "\"(\"" + COLUMN_SUB_CATEGORY_ID + "\") " +
            ");";

    public static final String DROP_TABLE_LOGIN = "DROP TABLE IF EXISTS " + TABLE_LOGIN + ";";
    public static final String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS + ";";

    public static InsertModel insertUser(UserModel userModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, userModel.getName());
        contentValues.put(COLUMN_USER_USERNAME, userModel.getUsername());
        contentValues.put(COLUMN_USER_PASSWORD, userModel.getPassword());
        contentValues.put(COLUMN_USER_GENDER, userModel.getGender());
        contentValues.put(COLUMN_USER_START_DATE, String.valueOf(new Date()));
        return new InsertModel(TABLE_USERS, contentValues);
    }

    public static InsertModel insertTransaction(AccountModel accountModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ACCOUNT_USER_ID, accountModel.getUser().getId());
        contentValues.put(COLUMN_ACCOUNT_SUB_CATEGORY_ID, accountModel.getSubCategory().getId());
        contentValues.put(COLUMN_ACCOUNT_TRANSACTION_TYPE, accountModel.getType());
        contentValues.put(COLUMN_ACCOUNT_PRICE, accountModel.getPrice());
        contentValues.put(COLUMN_ACCOUNT_DATE, accountModel.getDate());
        return new InsertModel(TABLE_ACCOUNTS, contentValues);
    }

    public static InsertModel insertSubCategory(SubCategoryModel subCategoryModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SUB_CATEGORY_NAME, subCategoryModel.getName());
        contentValues.put(COLUMN_SUB_CATEGORY_CATEGORY_ID, subCategoryModel.getCategory().getId());
        contentValues.put(COLUMN_SUB_CATEGORY_IS_ACTIVE, subCategoryModel.getIsActive());
        contentValues.put(COLUMN_SUB_CATEGORY_CREATION_DATE, subCategoryModel.getCreation_date());
        return new InsertModel(TABLE_SUB_CATEGORIES, contentValues);
    }

    public static InsertModel insertLoginCredential(LoginModel loginModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOGIN_USERNAME, loginModel.getUsername());
        contentValues.put(COLUMN_LOGIN_PASSWORD, loginModel.getPassword());
        return new InsertModel(TABLE_LOGIN, contentValues);
    }

    public static UpdateModel updateUser(UserModel userModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, userModel.getName());
        contentValues.put(COLUMN_USER_USERNAME, userModel.getUsername());
        contentValues.put(COLUMN_USER_PASSWORD, userModel.getPassword());
        contentValues.put(COLUMN_USER_GENDER, userModel.getGender());
        return new UpdateModel(TABLE_USERS, contentValues, COLUMN_USER_ID + " = ?", String.valueOf(userModel.getId()));
    }

    public static DeleteModel updateUser(int id) {
        return new DeleteModel(TABLE_USERS, COLUMN_USER_ID + " = ?", String.valueOf(id));
    }

    public static ArrayList<UserModel> getUserList(Cursor cursor) {
        ArrayList<UserModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            UserModel model = new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5));
            arrayList.add(model);
        }
        return arrayList;
    }

    public static ArrayList<SubCategoryModel> getSubCategoryList(Cursor cursor) {
        ArrayList<SubCategoryModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            SubCategoryModel model = new SubCategoryModel(cursor.getInt(0), cursor.getString(1), new CategoryModel(), 0, "");
            arrayList.add(model);
        }
        return arrayList;
    }

    public static ArrayList<AccountModel> getDepositList(Cursor cursor) {
        ArrayList<AccountModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            AccountModel model = new AccountModel(cursor.getInt(0), new UserModel(), new SubCategoryModel(), cursor.getInt(1), DEPOSIT, cursor.getString(2));
            arrayList.add(model);
        }
        return arrayList;
    }

    public static ArrayList<AccountModel> getExpenseList(Cursor cursor) {
        ArrayList<AccountModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return arrayList;
        }
        while (cursor.moveToNext()) {
            AccountModel model = new AccountModel(cursor.getInt(0), new UserModel(),
                    new SubCategoryModel(cursor.getInt(2), cursor.getString(3), new CategoryModel(), ACTIVE, ""),
                    cursor.getInt(1), DEPOSIT, cursor.getString(4));
            arrayList.add(model);
        }
        return arrayList;
    }

    public static UserModel getSingleUserData(Cursor cursor) {
        UserModel model = new UserModel();
        if (cursor.getCount() == 0) {
            return model;
        }
        while (cursor.moveToNext()) {
            model = new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5));
        }
        return model;
    }

    public static SubCategoryModel getSingleSubCategoryData(Cursor cursor) {
        SubCategoryModel model = new SubCategoryModel();
        if (cursor.getCount() == 0) {
            return model;
        }
        while (cursor.moveToNext()) {
            model = new SubCategoryModel(cursor.getInt(0), cursor.getString(1), new CategoryModel(cursor.getInt(2), "", ACTIVE, ""), ACTIVE, "");
        }
        return model;
    }

    public static int getTotalCostOfToday(Cursor cursor) {
        ArrayList<AccountModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return 0;
        }
        int amount = 0;
        while (cursor.moveToNext()) {
            amount += cursor.getInt(0);
        }
        return amount;
    }

    public static int getTotalDepositOfThisMonth(Cursor cursor) {
        ArrayList<AccountModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return 0;
        }
        int amount = 0;
        while (cursor.moveToNext()) {
            amount += cursor.getInt(0);
        }
        return amount;
    }

    public static int getTotalWithdrawnOfThisMonth(Cursor cursor) {
        ArrayList<AccountModel> arrayList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            return 0;
        }
        int amount = 0;
        while (cursor.moveToNext()) {
            amount += cursor.getInt(0);
        }
        return amount;
    }

    public static String TotalCostOfTodayQuery() {
        String date = "";
        SimpleDateFormat fromUser = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.format(fromUser.parse(String.valueOf(new Date())));
        } catch (ParseException e) {
        }
        return "Select " + COLUMN_ACCOUNT_PRICE + " from  " + TABLE_ACCOUNTS + " where " + COLUMN_ACCOUNT_USER_ID + "=" + USER_ID + " AND " + COLUMN_ACCOUNT_TRANSACTION_TYPE + "=" + WITHDRAW + " AND " + COLUMN_ACCOUNT_DATE + "=date('now');";
    }

    public static String getTotalDepositOfThisMonthQuery() {
        return "Select " + COLUMN_ACCOUNT_PRICE + " from  " + TABLE_ACCOUNTS + " where " + COLUMN_ACCOUNT_USER_ID + "=" + USER_ID + " AND " + COLUMN_ACCOUNT_TRANSACTION_TYPE + "=" + DEPOSIT + " AND " + COLUMN_ACCOUNT_DATE + " BETWEEN date('now','start of month','+0 month','-0 day') AND date('now','start of month','+1 month','-1 day');";
    }

    public static String getTotalWithdrawnOfThisMonthOfQuery() {
        return "Select " + COLUMN_ACCOUNT_PRICE + " from  " + TABLE_ACCOUNTS + " where " + COLUMN_ACCOUNT_USER_ID + "=" + USER_ID + " AND " + COLUMN_ACCOUNT_TRANSACTION_TYPE + "=" + WITHDRAW + " AND " + COLUMN_ACCOUNT_DATE + " BETWEEN date('now','start of month','+0 month','-0 day') AND date('now','start of month','+1 month','-1 day');";
    }

    public static String loginQueryBuilder(String username, String password) {
        return "Select * from  " + TABLE_LOGIN + " where \"" + COLUMN_LOGIN_USERNAME + "\"=\"" + username + "\" AND \"" + COLUMN_LOGIN_PASSWORD + "\"=\"" + password + "\";";
    }

    public static String getSingleUserQuery(int id) {
        return "Select * from  " + TABLE_USERS + " where \"" + COLUMN_USER_ID + "\"=\"" + id + "\";";
    }

    public static String getSingleSubCategoryQuery(int id) {
        return "Select * from  " + TABLE_SUB_CATEGORIES + " where \"" + COLUMN_SUB_CATEGORY_ID + "\"=\"" + id + "\";";
    }

    public static String getSubCategories(int id) {
        return "Select " + COLUMN_SUB_CATEGORY_ID + ", " + COLUMN_SUB_CATEGORY_NAME + " from  " + TABLE_SUB_CATEGORIES + " where \"" + COLUMN_SUB_CATEGORY_CATEGORY_ID + "\"=\"" + id + "\";";
    }

    public static String getAllDepositQuery() {
        return "Select " + COLUMN_ACCOUNT_ID + ", " + COLUMN_ACCOUNT_PRICE + ", " + COLUMN_ACCOUNT_DATE + " from  " + TABLE_ACCOUNTS + " where \"" + COLUMN_ACCOUNT_TRANSACTION_TYPE + "\"=\"" + DEPOSIT + "\" AND \"" + COLUMN_ACCOUNT_USER_ID + "\"=\"" + USER_ID + "\" ;";
    }

    public static String getAllExpenseQuery() {
        return "Select " +
                TABLE_ACCOUNTS + "." + COLUMN_ACCOUNT_ID + ", " +
                TABLE_ACCOUNTS + "." + COLUMN_ACCOUNT_PRICE + ", " +
                TABLE_SUB_CATEGORIES + "." + COLUMN_SUB_CATEGORY_ID + ", " +
                TABLE_SUB_CATEGORIES + "." + COLUMN_SUB_CATEGORY_NAME + ", " +
                TABLE_ACCOUNTS + "." + COLUMN_ACCOUNT_DATE +
                " from  " + TABLE_ACCOUNTS +
                " LEFT JOIN " + TABLE_SUB_CATEGORIES + " ON " + TABLE_SUB_CATEGORIES + "." + COLUMN_SUB_CATEGORY_ID + "=" + TABLE_ACCOUNTS + "." + COLUMN_ACCOUNT_SUB_CATEGORY_ID +
                " where " + TABLE_ACCOUNTS + "." + COLUMN_ACCOUNT_TRANSACTION_TYPE + "=" + WITHDRAW + " AND " + TABLE_ACCOUNTS + "." + COLUMN_ACCOUNT_USER_ID + "=" + USER_ID + ";";
    }

    public static int getLoginUserCredential(Cursor cursor) {
        LoginModel model = new LoginModel();
        if (cursor.getCount() == 0) {
            return 0;
        }
        while (cursor.moveToNext()) {
            return cursor.getInt(3);
        }
        return 0;
    }
}
