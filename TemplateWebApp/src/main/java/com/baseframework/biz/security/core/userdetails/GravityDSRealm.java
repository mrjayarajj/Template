package com.baseframework.biz.security.core.userdetails;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.baseframework.domain.security.core.userdetails.MyAccInfoTokenHandler;
import com.baseframework.domain.security.core.userdetails.User;

public class GravityDSRealm extends AuthorizingRealm {

	@Autowired
	private UserDetailsService userService = null;
	
	public GravityDSRealm() {
		setAuthenticationTokenClass(MyAccInfoTokenHandler.class);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		MyAccInfoTokenHandler myAcInfoToken = (MyAccInfoTokenHandler) token;

		String userAddress = myAcInfoToken.getHost();
		String cookieValue = (String) myAcInfoToken.getPrincipal(); // XYZ

		//https://host/validate?cookie=XYZ&ip=127.0.0.1&applicationId=123&applicationAdminPassword=pwd&fetch=grantedAuthority;firstName;lastName;userId
		
		User sUserDetails = (User)userService.loadUserByUsername("mrjayarajj");
		
		sUserDetails.setToken(myAcInfoToken.getMyAcInfoToken());
		return new SimpleAuthenticationInfo(sUserDetails, myAcInfoToken.getMyAcInfoToken(), getName());

	}

}
