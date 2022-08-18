package com.amido.stacks.workloads.menu.domain.utility;

import com.amido.stacks.workloads.menu.domain.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MenuHelper {

  private MenuHelper() {
    // Utility class
  }

  public static List<Menu> createMenus(int count) {
    List<Menu> menuList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      menuList.add(createMenu(i));
    }
    return menuList;
  }

  public static Menu createMenu(int counter) {
    return new Menu(
        UUID.randomUUID().toString(),
        UUID.randomUUID().toString(),
        counter + " Menu",
        counter + " Menu Description",
        new ArrayList<>(),
        true);
  }
}
