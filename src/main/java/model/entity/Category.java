package model.entity;

import java.util.Objects;

public class Category {
    private long id;
    private String categoryName;
    private String uaCategoryName;

    public String getUaCategoryName() {
        return uaCategoryName;
    }

    public void setUaCategoryName(String uaCategoryName) {
        this.uaCategoryName = uaCategoryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return getCategoryName().equals(category.getCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryName());
    }

}
