package cache.ehcache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.ehcache.Ehcache;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.CacheableInterceptor;

public class ReturnTypeCacheableInterceptor implements CacheableInterceptor {

	public boolean postInvokeCacheable(Ehcache ehcache, MethodInvocation methodInvocation, Serializable key, Object methodReturnValue) {

		if (ehcache.isKeyInCache(key)) {
			// return all the individual cache as a list
			List<Object> allCacheValues = new ArrayList<Object>();
			for (Object o : ehcache.getKeys()) {
				allCacheValues.add(ehcache.get(o).getValue());
			}			
			new CacheWapper(ehcache).put(key, allCacheValues);
		} else {
			// iterate the result and put it as individual cache
			if (methodReturnValue instanceof Collection) {
				for (Object o : (Collection) methodReturnValue) {
					if (o instanceof CacheDetails) {
						CacheDetails ckd = (CacheDetails) o;
						new CacheWapper(ehcache).put("" + ckd.getCacheKey(), ckd.getCacheValue());
						/* why key name is string ? */
					}
				}
			}
			if(methodReturnValue instanceof CacheDetails ){
				CacheDetails ckd = (CacheDetails) methodReturnValue;
				new CacheWapper(ehcache).put("" + ckd.getCacheKey(), ckd.getCacheValue());
			}
		}
		
		
		return true;
	}

	public boolean postInvokeCacheableException(Ehcache arg0, MethodInvocation arg1, Serializable arg2, Throwable arg3) {
		return false;
	}

	public boolean preInvokeCachable(Ehcache ehcache, MethodInvocation arg1, Serializable arg2, Object arg3) {
		return false;
	}

	public boolean preInvokeCacheableException(Ehcache arg0, MethodInvocation arg1, Serializable arg2, Throwable arg3) {
		return false;
	}

}
