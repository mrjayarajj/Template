package cache.ehcache;

import java.io.Serializable;

public interface CacheDetails {
	
	public Serializable getCacheKey();
	
	public Serializable getCacheValue();

}
