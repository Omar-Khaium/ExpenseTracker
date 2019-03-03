package org.emptybit.expensetracker.Model;

import android.content.ContentValues;

public class InsertModel {
    private String tableName;
    private ContentValues contentValues;

    public InsertModel() {
    }

    public InsertModel(String tableName, ContentValues contentValues) {
        this.tableName = tableName;
        this.contentValues = contentValues;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ContentValues getContentValues() {
        return contentValues;
    }

    public void setContentValues(ContentValues contentValues) {
        this.contentValues = contentValues;
    }
}
