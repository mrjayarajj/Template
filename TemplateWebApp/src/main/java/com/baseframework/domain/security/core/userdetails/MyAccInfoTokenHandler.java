package com.baseframework.domain.security.core.userdetails;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class MyAccInfoTokenHandler implements HostAuthenticationToken, RememberMeAuthenticationToken {

	/** The my ac info token. */
	private String myAcInfoToken;

	/** The host. */
	private String host;

	public MyAccInfoTokenHandler(String myAcInfoToken, String host) {
		super();
		this.myAcInfoToken = myAcInfoToken;
		this.host = host;
	}

	@Override
	public Object getPrincipal() {
		return myAcInfoToken;
	}

	@Override
	public Object getCredentials() {
		return myAcInfoToken;
	}

	@Override
	public boolean isRememberMe() {
		return false;
	}

	@Override
	public String getHost() {
		return host;
	}

	/**
	 * Gets the my ac info token.
	 *
	 * @return the my ac info token
	 */
	public String getMyAcInfoToken() {
		return myAcInfoToken;
	}

}
