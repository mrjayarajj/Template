package cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class CacheWapper {

	private Cache cache = null;

	public CacheWapper(Cache cache) {
		this.cache = cache;
	}

	public CacheWapper(Ehcache ehcache) {
		this.cache = (Cache) ehcache;
	}

	public void put(Object k, Object v) {
		this.cache.put(new Element(k, v));
	}

	public Object get(Object k) {
		Element e = this.cache.get(k);
		return e != null ? e.getValue() : null;
	}

	public Cache getCache() {
		return cache;
	}

}
