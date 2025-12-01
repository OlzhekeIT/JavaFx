package com.example.kaspifx;

import java.util.Objects;

public class ProductCategory {
    private final int id;
    private final String name;

    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name; // ListView ішінде осылай көрінеді
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

