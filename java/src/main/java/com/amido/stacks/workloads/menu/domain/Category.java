package com.amido.stacks.workloads.menu.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  private String id;

  private String name;

  private String description;

  private List<Item> items = new ArrayList<>();
}
