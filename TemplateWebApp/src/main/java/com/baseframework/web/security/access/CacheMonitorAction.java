package com.baseframework.web.security.access;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

public class CacheMonitorAction {

	private CacheManager cacheManager = null;

	public String onLoad() {
		Ehcache e1 = cacheManager.getEhcache("REQUEST_CACHE");
		show(e1);
		Ehcache e2 = cacheManager.getEhcache("FORWARD_CACHE");
		show(e2);
		Ehcache e3 = cacheManager.getEhcache("INCLUDE_CACHE");
		show(e3);
		return "success";
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void show(Ehcache c) {
		System.out.println();
		System.out.println("-------------------------------------");
		if (c != null) {
			for (Object o : c.getKeys()) {
				if (c.get(o) != null)
					System.out.println(o + "=" + c.get(o).getValue());
				else
					System.out.println(o + "=" + null);
			}
		}
		System.out.println("-------------------------------------");

	}

}
