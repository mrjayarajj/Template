package com.baseframework.biz.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Authenticate;

public interface AuthenticateService {

	public List<Authenticate> selectAuthenticatedList();

	public void insertAuthenticate(List<Authenticate> grantList);

}
