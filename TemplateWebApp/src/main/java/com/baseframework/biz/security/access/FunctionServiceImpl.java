package com.baseframework.biz.security.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baseframework.dao.security.access.FunctionDAO;
import com.baseframework.dao.security.access.ModuleDAO;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;

public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	private FunctionDAO functionDAO = null;
	
	@Autowired
	private ModuleDAO moduleDAO = null;

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	public void deleteFunction(List<Integer> FunctionList) {
		getFunctionDAO().deleteFunction(FunctionList);
	}

	public void insertFunction(Function f) {
		getFunctionDAO().insertFunction(f);
	}

	public List<Function> selectAllFunctionsWithModule() {		
		return getFunctionDAO().selectAllFunctionsWithModule();
	}

	public Function selectFunction(int id) {		
		return getFunctionDAO().selectFunction(id);
	}

	public void updateFunction(Function f) {
		getFunctionDAO().updateFunction(f);
	}

	public FunctionDAO getFunctionDAO() {
		return functionDAO;
	}

	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	public List<Module> selectAllModule() {		
		return getModuleDAO().selectAllModule();
	}

	public List<Function> selectFunctions(Module module) {	
		return getFunctionDAO().selectFunctions(module);
	}

	public List<Function> selectFunctionWithRoles(Module module) {		
		return getFunctionDAO().selectFunctionWithRoles(module);
	}
}