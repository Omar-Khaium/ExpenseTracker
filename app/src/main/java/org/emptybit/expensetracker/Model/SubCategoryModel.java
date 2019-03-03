package org.emptybit.expensetracker.Model;

public class SubCategoryModel {
    private int id;
    private String name;
    private CategoryModel Category;
    private int isActive;
    private String creation_date;

    public SubCategoryModel(int id, String name, CategoryModel category, int isActive, String creation_date) {
        this.id = id;
        this.name = name;
        Category = category;
        this.isActive = isActive;
        this.creation_date = creation_date;
    }

    public SubCategoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryModel getCategory() {
        return Category;
    }

    public void setCategory(CategoryModel category) {
        Category = category;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
