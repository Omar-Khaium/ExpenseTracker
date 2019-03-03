package org.emptybit.expensetracker.Model;

public class AccountModel {
    private int id;
    private UserModel User;
    private SubCategoryModel SubCategory;
    private int price;
    private int type;
    private String date;

    public AccountModel() {
    }

    public AccountModel(int id, UserModel user, SubCategoryModel subCategory, int price, int type, String date) {
        this.id = id;
        User = user;
        SubCategory = subCategory;
        this.price = price;
        this.type = type;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
