package com.lym.spring.framework.service.v2;

import com.lym.spring.framework.dao.v2.AccuntDao;
import com.lym.spring.framework.dao.v2.ItemDao;

public class ZooService {

	private AccuntDao accuntDao;
	
	private ItemDao itemDao;
	
	private String owner;
	
	private int version;

	public AccuntDao getAccuntDao() {
		return accuntDao;
	}

	public void setAccuntDao(AccuntDao accuntDao) {
		this.accuntDao = accuntDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
