package org.emptybit.expensetracker.Database;

import android.content.ContentValues;
import android.database.Cursor;

import org.emptybit.expensetracker.Model.DeleteModel;
import org.emptybit.expensetracker.Model.InsertModel;
import org.emptybit.expensetracker.Model.LoginModel;
import org.emptybit.expensetracker.Model.UpdateModel;
import org.emptybit.expensetracker.Model.UserModel;

import java.util.ArrayList;
import java.util.Date;

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

    public static String loginQueryBuilder(String username, String password) {
        return "Select * from  " + TABLE_LOGIN + " where \"" + COLUMN_LOGIN_USERNAME + "\"=\"" + username + "\" AND \"" + COLUMN_LOGIN_PASSWORD + "\"=\"" + password + "\";";
    }

    public static String getSingleUserQuery(int id) {
        return "Select * from  " + TABLE_USERS + " where \"" + COLUMN_USER_ID + "\"=\"" + id + "\";";
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
