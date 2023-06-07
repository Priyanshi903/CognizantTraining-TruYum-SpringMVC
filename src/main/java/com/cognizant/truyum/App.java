package com.cognizant.truyum;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cognizant.truyum.model.MenuItem;

/**
 * Hello world!
 *
 */
public class App {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
		MenuItem m = (MenuItem) ctx.getBean("sandwich");
		System.out.println(m.getDateOfLaunch());
		JdbcTemplate jdbcTemp = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		long id = 101L;
		String sql = "select * from menu_item where menu_id=?";
		String sql2 = "select * from menu_item";
		MenuItem m1 = jdbcTemp.queryForObject(sql, new Object[] { id }, new RowMapper<MenuItem>() {

			@Override
			public MenuItem mapRow(ResultSet rslt, int rowNum) throws SQLException {

				long id = rslt.getLong("menu_id");
				String name = rslt.getString("menu_name");
				float price = rslt.getFloat("menu_price");
				String active = rslt.getString("menu_active");
				Date date = rslt.getDate("date_of_launch");
				String category = rslt.getString("menu_category");
				String free_delivery = rslt.getString("free_delivery");
				boolean fd = false;
				boolean act = false;
				if (active.equalsIgnoreCase("YES")) {
					act = true;
				}
				if (free_delivery.equalsIgnoreCase("YES")) {
					fd = true;
				}
				MenuItem m2 = new MenuItem(id, name, price, act, date, category, fd);

				return m2;
			}
		});
		List<MenuItem> li = jdbcTemp.queryForList(sql2, new RowMapper<MenuItem>() {

			@Override
			public List<MenuItem> mapRow(ResultSet rslt, int rowNum) throws SQLException {
				List<MenuItem> menuItemList = new ArrayList<>();

				while (rslt.next()) {

					long id = rslt.getLong("menu_id");
					String name = rslt.getString("menu_name");
					float price = rslt.getFloat("menu_price");
					String active = rslt.getString("menu_active");
					Date date = rslt.getDate("date_of_launch");
					String category = rslt.getString("menu_category");
					String free_delivery = rslt.getString("free_delivery");
					boolean fd = false;
					boolean act = false;
					if (active.equalsIgnoreCase("YES")) {
						act = true;
					}
					if (free_delivery.equalsIgnoreCase("YES")) {
						fd = true;
					}

					MenuItem m = new MenuItem(id, name, price, act, date, category, fd);
					menuItemList.add(m);

				}
				return menuItemList;
			}
		});
		System.out.println(m1);
		System.out.println(li);
	}

}
