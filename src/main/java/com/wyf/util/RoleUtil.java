package com.wyf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wyf.urp.RoleBean;
import com.wyf.entity.Role;

public class RoleUtil {
	public static List<RoleBean> role2RoleBean(List<Role> roles,List<Role> userRole){
		Map<Integer,Role> map = new HashMap<>();
		for(Role role:userRole){
			map.put(role.getId(), role);
		}
		List<RoleBean> roleBeans = new ArrayList<>();
		for(Role role:roles){
			RoleBean rb = new RoleBean();
			rb.setId(role.getId());
			rb.setName(role.getName());
			if(map.get(role.getId())!=null){
				rb.setChecked(true);
			}else{
				rb.setChecked(false);
			}
			roleBeans.add(rb);
		}
		return roleBeans;
	}
}
