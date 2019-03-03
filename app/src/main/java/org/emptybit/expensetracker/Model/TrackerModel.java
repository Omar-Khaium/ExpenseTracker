package org.emptybit.expensetracker.Model;

public class TrackerModel {
    private int id;
    private UserModel User;
    private SubCategoryModel SubCategory;
    private String date;

    public TrackerModel() {
    }

    public TrackerModel(int id, UserModel user, SubCategoryModel subCategory, String date) {
        this.id = id;
        User = user;
        SubCategory = subCategory;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return User;
    }

    public void setUser(UserModel user) {
        User = user;
    }

    public SubCategoryModel getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(SubCategoryModel subCategory) {
        SubCategory = subCategory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
