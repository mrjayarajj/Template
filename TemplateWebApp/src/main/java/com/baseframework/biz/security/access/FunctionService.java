package com.baseframework.biz.security.access;

import java.util.List;
import java.util.Set;

import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;

public interface FunctionService {
	
	public void insertFunction(Function f);

	public List<Function> selectFunctionWithRoles(Module module);
	
	public List<Function> selectAllFunctionsWithModule();
	
	public List<Module> selectAllModule();

	public Function selectFunction(int id);

	public void updateFunction(Function f);

	public void deleteFunction(List<Integer> functionList);

	public List<Function> selectFunctions(Module module);
}
