package com.baseframework.web.security.access.vote;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.baseframework.domain.security.access.Role;

public class BusinessVoter implements AccessDecisionVoter {

	private String businessPrefix = "BF_";

	public String getBusinessPrefix() {
		return businessPrefix;
	}

	public boolean supports(ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null) && attribute.getAttribute().startsWith(getBusinessPrefix())) {
			return true;
		} else {
			return false;
		}
	}

	Collection<GrantedAuthority> extractAuthorities(Authentication authentication) {
		return (Collection<GrantedAuthority>) authentication.getAuthorities();
	}

	/**
	 * This implementation supports any type of class, because it does not query
	 * the presented secure object.
	 * 
	 * @param arg0
	 *            the secure object
	 * 
	 * @return always <code>true</code>
	 */
	public boolean supports(Class arg0) {
		return true;
	}

	public int vote(Authentication authentication, Object object, Collection attributes) {
		int result = ACCESS_ABSTAIN;
		Collection<GrantedAuthority> authorities = extractAuthorities(authentication);		

		Collection<ConfigAttribute> attributes_ =  ((Collection<ConfigAttribute>)attributes);
		for (ConfigAttribute attribute : attributes_) {
			
			if (this.supports(attribute)) {				
				
				result = ACCESS_DENIED;

				// Attempt to find a matching granted authority
				for (GrantedAuthority authority : authorities) {
					
					boolean isSuperUser =  authority.getAuthority().equals("ROLE_SUPER");
					
					if(isSuperUser){
						return ACCESS_GRANTED;
					}
					
					if (attribute.getAttribute().equals(authority.getAuthority())) {
						return ACCESS_GRANTED;
					}
				}
			}
		}

		return result;
	}
}