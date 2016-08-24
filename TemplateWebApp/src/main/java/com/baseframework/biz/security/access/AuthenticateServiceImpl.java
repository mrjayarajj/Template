package com.baseframework.biz.security.access;

import java.util.List;

import com.baseframework.dao.security.access.AuthenticateDAO;
import com.baseframework.domain.security.access.Authenticate;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Role;

public class AuthenticateServiceImpl implements AuthenticateService {

	private AuthenticateDAO authenticateDAO = null;

	public AuthenticateDAO getAuthenticateDAO() {
		return authenticateDAO;
	}

	public void setAuthenticateDAO(AuthenticateDAO authenticateDAO) {
		this.authenticateDAO = authenticateDAO;
	}

	private boolean checkFunction(Function declaredFunction,
			int permittedFunction) {
		return declaredFunction.getFunctionId() == permittedFunction;
	}

	private boolean checkRole(Role declaredRole, int permittedRole) {
		return declaredRole.getRoleId() == permittedRole;
	}

	public List<Authenticate> selectAuthenticatedList() {
		return getAuthenticateDAO().selectAuthenticatedList();
	}
	
	public void insertAuthenticate(List<Authenticate> grantList) {
		getAuthenticateDAO().insertAuthenticate(grantList);
	}

}