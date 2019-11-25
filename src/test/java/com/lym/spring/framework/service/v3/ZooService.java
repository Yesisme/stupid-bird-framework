package com.lym.spring.framework.service.v3;

import com.lym.spring.framework.dao.v3.AccuntDao;
import com.lym.spring.framework.dao.v3.ItemDao;

public class ZooService {

	private AccuntDao accuntDao;
	
	private ItemDao itemDao;
	
	private int version;
	
	public ZooService(AccuntDao accuntDao,ItemDao itemDao) {
		this.accuntDao = accuntDao;
		this.itemDao = itemDao;
		this.version=1;
	}
	
	public ZooService(AccuntDao accuntDao,ItemDao itemDao,int version) {
		this.accuntDao = accuntDao;
		this.itemDao = itemDao;
		this.version = version;
	}

	public AccuntDao getAccuntDao() {
		return accuntDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public int getVersion() {
		return version;
	}
	
}
