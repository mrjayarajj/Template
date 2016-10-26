package com.baseframework.web.security.access;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baseframework.biz.security.access.FunctionService;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.vo.JSONDetails;

public class FunctionAction implements JSONDetails {
	
	public static final Logger Log = LoggerFactory.getLogger(FunctionAction.class);

	private FunctionForm functionForm = null;

	private FunctionService functionService = null;

	public String onLoad() {
		List<Module> moduleList = getFunctionService().selectAllModule();
		List<Function> functionList = getFunctionService().selectAllFunctionsWithModule();
		FunctionForm f = getFunctionForm() == null ? new FunctionForm() : getFunctionForm();
		f.setFunctionList(functionList);
		f.setModuleList(moduleList);
		setFunctionForm(f);
		return "success";
	}

	public String addFunction() {
		Function r = getFunctionForm().getFunction();
		getFunctionService().insertFunction(r);
		return "redirect_onLoad";
	}

	public String selectFunction() {
		Function f = getFunctionForm().getFunction();
		Function loadedFunction = getFunctionService().selectFunction(f.getFunctionId());
		getFunctionForm().setFunction(loadedFunction);
		getFunctionForm().setAction("update");
		return "onLoad";
	}

	public String updateFunction() {
		Function f = getFunctionForm().getFunction();
		getFunctionService().updateFunction(f);
		return "redirect_onLoad";
	}

	public String deleteFunctions() {
		List<Integer> functionList = getFunctionForm().getSelectedFunctionList();
		
		List<Integer> selectedFunctionList_ = new ArrayList<Integer>();
		for(Object o : functionList){
			try{
				Integer i = Integer.parseInt(o.toString());
				selectedFunctionList_.add(i);
			}catch(NumberFormatException e){
				//ignore this
			}
		}
		
		getFunctionService().deleteFunction(selectedFunctionList_);
		return "redirect_onLoad";
	}

	public FunctionForm getFunctionForm() {
		return functionForm;
	}

	public void setFunctionForm(FunctionForm functionForm) {
		this.functionForm = functionForm;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public Object getJavaValue() {
		return getFunctionForm();
	}

}
