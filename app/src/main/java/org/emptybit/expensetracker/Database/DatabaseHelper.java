package org.emptybit.expensetracker.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.emptybit.expensetracker.Model.DeleteModel;
import org.emptybit.expensetracker.Model.InsertModel;
import org.emptybit.expensetracker.Model.UpdateModel;
import org.emptybit.expensetracker.Model.UserModel;

import static org.emptybit.expensetracker.Database.DatabaseQuery.COLUMN_LOGIN_USER_ID;
import static org.emptybit.expensetracker.Database.DatabaseQuery.COLUMN_USER_ID;
import static org.emptybit.expensetracker.Database.DatabaseQuery.CREATE_ACCOUNT_TABLE;
import static org.emptybit.expensetracker.Database.DatabaseQuery.CREATE_CATEGORY_TABLE;
import static org.emptybit.expensetracker.Database.DatabaseQuery.CREATE_LOGIN_TABLE;
import static org.emptybit.expensetracker.Database.DatabaseQuery.CREATE_SUB_CATEGORY_TABLE;
import static org.emptybit.expensetracker.Database.DatabaseQuery.CREATE_TRACKERS_HISTORY_TABLE;
import static org.emptybit.expensetracker.Database.DatabaseQuery.CREATE_USER_TABLE;
import static org.emptybit.expensetracker.Database.DatabaseQuery.DATABASE_NAME;
import static org.emptybit.expensetracker.Database.DatabaseQuery.TABLE_USERS;
import static org.emptybit.expensetracker.Database.DatabaseQuery.VERSION;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
            sqLiteDatabase.execSQL(CREATE_LOGIN_TABLE);
            sqLiteDatabase.execSQL(CREATE_CATEGORY_TABLE);
            sqLiteDatabase.execSQL(CREATE_SUB_CATEGORY_TABLE);
            sqLiteDatabase.execSQL(CREATE_TRACKERS_HISTORY_TABLE);
            sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE);
            Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(InsertModel model) {
        SQLiteDatabase database = this.getWritableDatabase();
        long rowId = database.insert(model.getTableName(), null, model.getContentValues());
        return rowId != -1;
    }

    public boolean update(UpdateModel model) {
        SQLiteDatabase database = this.getWritableDatabase();
        long rowId = database.update(model.getTableName(), model.getContentValues(), model.getQuery(), new String[]{model.getId()});
        return rowId != -1;
    }

    public boolean delete(DeleteModel model) {
        SQLiteDatabase database = this.getWritableDatabase();
        long rowId = database.delete(model.getTableName(), model.getQuery(), new String[]{model.getId()});
        return rowId != -1;
    }

    public Cursor getData(String query) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public boolean insertToLogin(InsertModel model) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT " + COLUMN_USER_ID + " FROM " + TABLE_USERS + " ORDER BY " + COLUMN_USER_ID + " LIMIT 1;", null);
        if (cursor.getCount() == 0) {
            return false;
        }
        long rowId = -1 ;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            model.getContentValues().put(COLUMN_LOGIN_USER_ID, id);
            rowId = database.insert(model.getTableName(), null, model.getContentValues());
        }
        return rowId != -1;
    }

}
