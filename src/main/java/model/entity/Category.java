package model.entity;

import java.util.Objects;

public class Category {
    private int id;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return category.equals(category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
