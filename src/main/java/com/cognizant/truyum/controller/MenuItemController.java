package com.cognizant.truyum.controller;

import javax.annotation.Generated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.dao.SystemException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;

@Controller
public class MenuItemController {
	
	@Autowired
	MenuItemService menuItemService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	
	@GetMapping("/show-menu-list-admin")
	public String showMenuItemListAdmin(ModelMap model) {
		LOGGER.info("Start");
		model.addAttribute("menuItemList",menuItemService.getMenuItemListAdmin());
		LOGGER.info("End");
		return  "menu-item-list-admin";
	}
	
	@GetMapping("/show-menu-list-customer")
	public String showMenuItemListCustomer(ModelMap model) throws Exception {
		LOGGER.info("Start");
		model.put("menuItemList", menuItemService.getMenuItemListCustomer());
		LOGGER.info("End");
		return "menu-item-list-customer";
	}
	
	@GetMapping("/show-edit-menu-item")
	public String showEditMenuItem(ModelMap model, @RequestParam("menuItemId") int id) {
		LOGGER.info("Start");
		MenuItem item = menuItemService.getMenuItem(id);
		model.addAttribute("menuItem", item);
		LOGGER.info("End");
		return "edit-menu-item";
	}
	
	@PostMapping("/edit-menu-item")
	public String editMenuItem(@ModelAttribute MenuItem menuItem, BindingResult bindingResult) {
		LOGGER.info("Start");
		if (bindingResult.hasErrors()) {
			return "edit-menu-item";
		}
		menuItemService.modifyMenuItem(menuItem);
		LOGGER.info("End");
		return "edit-menu-item-status";
	}

}
