package cache.ehcache;

import java.io.Serializable;
import java.util.Collection;

import net.sf.ehcache.Ehcache;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.CacheableInterceptor;

public class ArgumentCacheableInterceptor implements CacheableInterceptor {

	public boolean postInvokeCacheable(Ehcache ehcache, MethodInvocation methodInvocation, Serializable key, Object arg3) {
		Object objArr[] = methodInvocation.getArguments();
		if (objArr.length > 0) {
			for (Object o : objArr) {
				if (o instanceof Collection) {
					for (Object innO : (Collection) o) {
						if (innO instanceof CacheDetails) {
							CacheDetails ckd = (CacheDetails) innO;
							new CacheWapper(ehcache).put("" + ckd.getCacheKey(), ckd.getCacheValue());
						}
					}
				}

				if (o instanceof CacheDetails) {
					CacheDetails ckd = (CacheDetails) o;
					new CacheWapper(ehcache).put("" + ckd.getCacheKey(), ckd.getCacheValue());
				}
			}
		}
		return false;
	}

	public boolean postInvokeCacheableException(Ehcache arg0, MethodInvocation arg1, Serializable arg2, Throwable arg3) {
		return false;
	}

	public boolean preInvokeCachable(Ehcache arg0, MethodInvocation arg1, Serializable arg2, Object arg3) {
		return false;
	}

	public boolean preInvokeCacheableException(Ehcache arg0, MethodInvocation arg1, Serializable arg2, Throwable arg3) {
		return false;
	}

}
