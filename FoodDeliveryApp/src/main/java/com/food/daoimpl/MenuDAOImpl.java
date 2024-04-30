package com.food.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.food.dao.MenuDAO;
import com.food.model.Menu;

public class MenuDAOImpl implements MenuDAO {

    // Simulating database storage
    private Map<Integer, Menu> menuDatabase = new HashMap<>();

    @Override
    public void addMenu(Menu menu) {
        menuDatabase.put(menu.getMenuId(), menu);
        System.out.println("Menu added successfully: " + menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        if (menuDatabase.containsKey(menu.getMenuId())) {
            menuDatabase.put(menu.getMenuId(), menu);
            System.out.println("Menu updated successfully: " + menu);
        } else {
            System.out.println("Menu with ID " + menu.getMenuId() + " not found, cannot update.");
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        if (menuDatabase.containsKey(menuId)) {
            menuDatabase.remove(menuId);
            System.out.println("Menu with ID " + menuId + " deleted successfully.");
        } else {
            System.out.println("Menu with ID " + menuId + " not found, cannot delete.");
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        if (menuDatabase.containsKey(menuId)) {
            return menuDatabase.get(menuId);
        } else {
            System.out.println("Menu with ID " + menuId + " not found.");
            return null;
        }
    }

    @Override
    public List<Menu> getAllMenus(int restaurantId) {
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : menuDatabase.values()) {
            if (menu.getRestaurantId() == restaurantId) {
                menus.add(menu);
            }
        }
        return menus;
    }
}
