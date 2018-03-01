package com.wyf.urp;

public class TreeBean {
	//{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},

	private int id;
	private int pId;
	private String name;
	private boolean checked=false;
	private boolean open=true;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}



}
