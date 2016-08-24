package cache.ehcache;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;
import com.googlecode.ehcache.annotations.key.HashCodeCacheKeyGenerator;

public class CacheKeyFinder implements CacheKeyGenerator<Serializable> {

	public Serializable generateKey(Object... objArr) {
		StringBuffer sbf = null;
		if (objArr.length > 0) {
			for (Object o : objArr) {
				if (o instanceof CacheDetails) {
					CacheDetails cacheKey = (CacheDetails) o;
					if (sbf == null) {
						sbf = new StringBuffer();
					}
					sbf.append(cacheKey.getCacheKey());
				}
			}
		}

		if (sbf == null) {
			sbf = new StringBuffer();
			sbf.append(new HashCodeCacheKeyGenerator().generateKey(objArr));
		}

		return sbf.toString();
	}

	public Serializable generateKey(MethodInvocation methodInvocation) {
		Object objArr[] = methodInvocation.getArguments();
		return generateKey(objArr);
	}

}
