package com.wyf.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wyf.urp.Menu;
import com.wyf.bean.Permission;

public class MenuUtil {
	public static List<Menu> permission2Menu(List<Permission> pers){
		Map<Integer,Menu> map = new HashMap<>();
		try {
			for (int i = 0; i < pers.size(); i++) {
				Permission p = pers.get(i);
				if (p.getPid() == 0) {
					Menu menu = new Menu();
					menu.setId(p.getId());
					menu.setName(p.getName());
					menu.setPid(p.getPid());
					menu.setSort(p.getSort());
					menu.setType(p.getType());
					menu.setUrl(p.getUrl());
					map.put(p.getId(), menu);
				}
			} 
		} catch (Exception e) {
			System.out.println("error");
		}

		try {
			for (int i = 0; i < pers.size(); i++) {
				Permission p = pers.get(i);
				if (p.getPid() != 0) {
					Menu menu = new Menu();
					menu.setId(p.getId());
					menu.setName(p.getName());
					menu.setPid(p.getPid());
					menu.setSort(p.getSort());
					menu.setType(p.getType());
					menu.setUrl(p.getUrl());

					Menu pmenu = map.get(p.getPid());

					pmenu.getChildren().add(menu);
				}
			} 
		} catch (Exception e) {
			System.out.println("error");
		}
		return map2menu(map);
	}
	private static List<Menu> map2menu(Map<Integer,Menu> map){
		List<Menu> list = new ArrayList<>();
		Collection<Menu> collection = map.values();
		Iterator<Menu> iter = collection.iterator();
		while(iter.hasNext()){
			Menu m = iter.next();

			Collections.sort(m.getChildren());
			list.add(m);
		}

		Collections.sort(list);
		return list;
	}

}
