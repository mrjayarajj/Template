package com.baseframework.web.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;

/**
 * @author Administrator
 *
 */
public class FunctionForm {

	private String action = "add";

	private Function Function = null;

	private List<Function> functionList = null;
	
	private List<Module> moduleList = null;

	private List<Integer> selectedFunctionList = null;
	
	public Function getFunction() {
		return Function;
	}

	public void setFunction(Function Function) {
		this.Function = Function;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public List<Integer> getSelectedFunctionList() {
		return selectedFunctionList;
	}

	public void setSelectedFunctionList(List<Integer> selectedFunctionList) {
		this.selectedFunctionList = selectedFunctionList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

}
