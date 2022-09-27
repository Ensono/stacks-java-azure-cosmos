package com.amido.stacks.workloads.menu.service.impl;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amido.stacks.workloads.menu.domain.Menu;
import com.amido.stacks.workloads.menu.domain.utility.MenuHelper;
import com.amido.stacks.workloads.menu.repository.CosmosMenuRepository;
import com.amido.stacks.workloads.menu.service.MenuQueryService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Tag("Unit")
class CosmosMenuQueryServiceTest {

  @Test
  void findById() {

    CosmosMenuRepository repository = mock(CosmosMenuRepository.class);
    MenuQueryService menuQueryServiceImpl = new CosmosMenuQueryService(repository);

    Menu menu = MenuHelper.createMenu(1);

    when(repository.findById(any())).thenReturn(Optional.of(menu));

    Optional<Menu> optFound = menuQueryServiceImpl.findById(randomUUID());

    assertNotNull(optFound);
    assertNotNull(optFound.get());

    assertEquals(menu.getId(), optFound.get().getId());
  }

  @Test
  void findAll() {

    CosmosMenuRepository repository = mock(CosmosMenuRepository.class);
    MenuQueryService menuQueryServiceImpl = new CosmosMenuQueryService(repository);

    Pageable pageable = mock(Pageable.class);

    List<Menu> results = MenuHelper.createMenus(2);
    Page<Menu> page1 = new PageImpl<>(results, pageable, 2);
    Page<Menu> page2 = new PageImpl<>(results, pageable, 2);

    // Given
    given(repository.findAll(any(Pageable.class))).willReturn(page1);
    given(repository.findAll(eq(pageable))).willReturn(page2);

    // When
    List<Menu> actualResults = menuQueryServiceImpl.findAll(2, 5);

    // Then
    then(actualResults).isEqualTo(results);
  }

  @Test
  void findAllByRestaurantId() {

    CosmosMenuRepository repository = mock(CosmosMenuRepository.class);
    MenuQueryService menuQueryServiceImpl = new CosmosMenuQueryService(repository);

    Pageable pageable = mock(Pageable.class);

    List<Menu> menus = MenuHelper.createMenus(2);
    Page<Menu> page1 = new PageImpl<>(menus, pageable, 2);

    when(repository.findAllByRestaurantId(any(), any())).thenReturn(page1);

    List<Menu> found = menuQueryServiceImpl.findAllByRestaurantId(randomUUID(), 10, 0);

    assertNotNull(found);
    assertEquals(2, found.size());
  }

  @Test
  void findAllByNameContaining() {

    CosmosMenuRepository repository = mock(CosmosMenuRepository.class);
    MenuQueryService menuQueryServiceImpl = new CosmosMenuQueryService(repository);

    Pageable pageable = mock(Pageable.class);

    List<Menu> menus = MenuHelper.createMenus(2);
    Page<Menu> page1 = new PageImpl<>(menus, pageable, 2);

    when(repository.findAllByNameContaining(any(), any())).thenReturn(page1);

    List<Menu> found = menuQueryServiceImpl.findAllByNameContaining("", 10, 0);

    assertNotNull(found);
    assertEquals(2, found.size());
  }

  @Test
  void findAllByRestaurantIdAndNameContaining() {

    CosmosMenuRepository repository = mock(CosmosMenuRepository.class);
    MenuQueryService menuQueryServiceImpl = new CosmosMenuQueryService(repository);

    Pageable pageable = mock(Pageable.class);

    List<Menu> menus = MenuHelper.createMenus(2);
    Page<Menu> page1 = new PageImpl<>(menus, pageable, 2);

    when(repository.findAllByRestaurantIdAndNameContaining(any(), any(), any())).thenReturn(page1);

    List<Menu> found =
        menuQueryServiceImpl.findAllByRestaurantIdAndNameContaining(randomUUID(), "", 10, 0);

    assertNotNull(found);
    assertEquals(2, found.size());
  }
}
