package com.shopme.admin.menu;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Menu;
import com.shopme.common.exception.MenuItemNotFoundException;

@Service
@Transactional
public class MenuService {

   @Autowired private MenuRepository repo;
	
	public List<Menu> listAll() {
		return repo.findAllByOrderByTypeAscPositionAsc();
	}	
	
	public void save(Menu menu) {
		setDefaultAlias(menu);
		
		if (menu.getId() == null) {
			setPositionForNewMenu(menu);
		} else {
			setPositionForEditedMenu(menu);
		}
		
		repo.save(menu);
	}
	
	private void setPositionForEditedMenu(Menu menu) {
		Menu existMenu = repo.findById(menu.getId()).get();
		
		if (!existMenu.getType().equals(menu.getType())) {
			// if the menu type changed, then set its position at the last
			setPositionForNewMenu(menu);
		}
	}

	private void setPositionForNewMenu(Menu newMenu) {
		// newly added menu always has position at the last
		Long newPosition = repo.countByType(newMenu.getType()) + 1;
		newMenu.setPosition(newPosition.intValue());		
	}
	
	private void setDefaultAlias(Menu menu) {
		if (menu.getAlias() == null || menu.getAlias().isEmpty()) {
			menu.setAlias(menu.getTitle().replaceAll(" ", "-"));
		}		
	}
	
	public Menu get(Integer id) throws MenuItemNotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new MenuItemNotFoundException("Could not find any menu item with ID " + id);
		}
	}
	
	public void updateEnabledStatus(Integer id, boolean enabled) throws MenuItemNotFoundException {
		if (!repo.existsById(id)) {
			throw new MenuItemNotFoundException("Could not find any menu item with ID " + id);
		}
		repo.updateEnabledStatus(id, enabled);
	}
	
	public void delete(Integer id) throws MenuItemNotFoundException {
		if (!repo.existsById(id)) {
			throw new MenuItemNotFoundException("Could not find any menu item with ID " + id);
		}
		repo.deleteById(id);
	}
	
	public void moveMenu(Integer id, MenuMoveDirection direction) throws MenuUnmoveableException, MenuItemNotFoundException {
		if (direction.equals(MenuMoveDirection.UP)) {
			moveMenuUp(id);
		} else {
			moveMenuDown(id);
		}
	}
	
	private void moveMenuUp(Integer id) throws MenuUnmoveableException, MenuItemNotFoundException {
		Optional<Menu> findById = repo.findById(id);
		if (!findById.isPresent()) {
			throw new MenuItemNotFoundException("Could not find any menu item with ID " + id);
		}
		
		Menu currentMenu = findById.get();
		List<Menu> allMenusOfSameType = repo.findByTypeOrderByPositionAsc(currentMenu.getType());
		int currentMenuIndex = allMenusOfSameType.indexOf(currentMenu);
		
		if (currentMenuIndex == 0) {
			throw new MenuUnmoveableException("The menu ID " + id + " is already in the first position");
		}
		
		// swap current menu item with the previous one, thus the given menu is moved up
		int previousMenuIndex = currentMenuIndex - 1;
		Menu previousMenu = allMenusOfSameType.get(previousMenuIndex);
		
		currentMenu.setPosition(previousMenuIndex + 1);
		allMenusOfSameType.set(previousMenuIndex, currentMenu);
		
		previousMenu.setPosition(currentMenuIndex + 1);
		allMenusOfSameType.set(currentMenuIndex, previousMenu);
		
		// update all menu items of the same type
		repo.saveAll(Arrays.asList(currentMenu, previousMenu));
	}
	
	private void moveMenuDown(Integer id) throws MenuUnmoveableException, MenuItemNotFoundException {
		Optional<Menu> findById = repo.findById(id);
		if (!findById.isPresent()) {
			throw new MenuItemNotFoundException("Could not find any menu item with ID " + id);
		}
		
		Menu currentMenu = findById.get();
		List<Menu> allMenusOfSameType = repo.findByTypeOrderByPositionAsc(currentMenu.getType());
		int currentMenuIndex = allMenusOfSameType.indexOf(currentMenu);
		
		if (currentMenuIndex == allMenusOfSameType.size() - 1) {
			throw new MenuUnmoveableException("The menu ID " + id + " is already in the last position");
		}
		
		// swap current menu item with the next one, thus the given menu is moved down
		int nextMenuIndex = currentMenuIndex + 1;
		Menu nextMenu = allMenusOfSameType.get(nextMenuIndex);
		
		currentMenu.setPosition(nextMenuIndex + 1);
		allMenusOfSameType.set(nextMenuIndex, currentMenu);
		
		nextMenu.setPosition(currentMenuIndex + 1);
		allMenusOfSameType.set(currentMenuIndex, nextMenu);

		// update all menu items of the same type
		repo.saveAll(Arrays.asList(currentMenu, nextMenu));
	}
	
	public enum MenuMoveDirection { UP, DOWN }
}
