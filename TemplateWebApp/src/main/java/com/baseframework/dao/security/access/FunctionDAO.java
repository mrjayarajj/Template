package com.baseframework.dao.security.access;

import java.util.List;
import java.util.Set;

import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;

public interface FunctionDAO {
	
	public void insertFunction(Function f);

	public List<Function> selectAllFunctionsWithModule();
	
	public List<Function> selectFunctionWithRoles(Module module);

	public Function selectFunction(int functionId);

	public void updateFunction(Function sourceFunction);

	public void deleteFunction(List<Integer> functionList);

	public List<Function> selectFunctions(Module module);
}
