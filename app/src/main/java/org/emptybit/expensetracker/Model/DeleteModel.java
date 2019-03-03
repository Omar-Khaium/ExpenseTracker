package org.emptybit.expensetracker.Model;

import android.content.ContentValues;

public class DeleteModel {
    private String tableName;
    private String query;
    private String id;

    public DeleteModel() {
    }

    public DeleteModel(String tableName, String query, String id) {
        this.tableName = tableName;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
