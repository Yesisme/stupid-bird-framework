package com.lym.spring.framework.service.v4;

import com.lym.spring.framework.beans.factory.annotation.Autowired;
import com.lym.spring.framework.core.stereotype.Component;
import com.lym.spring.framework.dao.v4.AccountDao;
import com.lym.spring.framework.dao.v4.ItemDao;


@Component(value="zooService")
public class ZooService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ItemDao itemDao;

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}
	
	
}
