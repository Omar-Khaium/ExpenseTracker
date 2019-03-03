package org.emptybit.expensetracker.Model;

import android.content.ContentValues;

public class UpdateModel {
    private String tableName;
    private ContentValues contentValues;
    private String query;
    private String id;

    public UpdateModel() {
    }

    public UpdateModel(String tableName, ContentValues contentValues, String query, String id) {
        this.tableName = tableName;
        this.contentValues = contentValues;
        this.query = query;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
