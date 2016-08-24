package com.baseframework.dao.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Authenticate;

public interface AuthenticateDAO {
	public List<Authenticate> selectAuthenticatedList();

	public void insertAuthenticate(List<Authenticate> grantList);
}
