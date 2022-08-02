package com.amido.stacks.workloads.menu.domain.utility;

import com.amido.stacks.workloads.menu.domain.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryHelper {

  private CategoryHelper() {
    // Utility class
  }

  public static List<Category> createCategories(int count) {
    List<Category> categoryList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      categoryList.add(createCategory(i));
    }
    return categoryList;
  }

  public static Category createCategory(int counter) {
    return new Category(
        UUID.randomUUID().toString(),
        counter + " Category",
        counter + " Menu Description",
        new ArrayList<>());
  }
}
