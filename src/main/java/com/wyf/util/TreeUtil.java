package com.wyf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wyf.urp.TreeBean;
import com.wyf.bean.Permission;

public class TreeUtil {
	
	public static List<TreeBean> permisssion2Tree(List<Permission> all,List<Permission> has){
		
		List<TreeBean> tree = new ArrayList<>();
		
		Map<Integer,Permission> map = new HashMap<>();
		
		if(has!=null){
			for(Permission p:has){
				map.put(p.getId(), p);
			}
		}
		if(all!=null)
		for(Permission p:all){
			TreeBean tb = new TreeBean();
			tb.setId(p.getId());
			tb.setpId(p.getPid());
			tb.setName(p.getName());
			if(map.get(p.getId())!=null){
				tb.setChecked(true);
			}
			tree.add(tb);
		}
		return tree;
	}
}
