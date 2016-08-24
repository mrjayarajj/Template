package cache.ehcache;

import java.io.Serializable;
import java.util.Collection;

import net.sf.ehcache.Ehcache;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.TriggersRemoveInterceptor;

public class ArgumentTriggersRemoveInteceptorName implements TriggersRemoveInterceptor {

	public boolean preInvokeTriggersRemove(Ehcache ehcache, MethodInvocation methodInvocation, Serializable arg3) {
		return false;
	}

	private void remove(Ehcache ehcache, Object o) {
		if (o instanceof CacheDetails) {
			CacheDetails key = (CacheDetails) o;
			StringBuffer sbf = new StringBuffer();
			sbf.append(key.getCacheKey());
			ehcache.remove(sbf.toString(), true);

		}
	}

	public boolean preInvokeTriggersRemoveAll(Ehcache ehcache, MethodInvocation methodInvocation) {
		Object objArr[] = methodInvocation.getArguments();
		if (objArr.length > 0) {
			for (Object o : objArr) {
				if (o instanceof Collection) {
					for (Object inO : (Collection) o) {
						remove(ehcache, inO);
					}
				}
				remove(ehcache, o);
			}
		}
		return false;
	}

}
