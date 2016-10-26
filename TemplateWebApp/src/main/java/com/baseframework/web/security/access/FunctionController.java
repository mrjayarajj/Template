package com.baseframework.web.security.access;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baseframework.biz.security.access.FunctionService;
import com.baseframework.domain.security.access.Function;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.vo.JSONDetails;

@Controller
@Scope("prototype")
@RequestMapping(value = "/security/access")
public class FunctionController implements JSONDetails {
	
	public static final Logger Log = LoggerFactory.getLogger(FunctionController.class);

	private FunctionForm functionForm = null;

	@Autowired
	private FunctionService functionService = null;

	@RequestMapping(value = "/functions", method = RequestMethod.GET)
	public ModelAndView onLoad() {
		List<Module> moduleList = getFunctionService().selectAllModule();
		List<Function> functionList = getFunctionService().selectAllFunctionsWithModule();
		FunctionForm f = getFunctionForm() == null ? new FunctionForm() : getFunctionForm();
		f.setFunctionList(functionList);
		f.setModuleList(moduleList);
		setFunctionForm(f);
		ModelAndView model = new ModelAndView("/jsp/base/security/function.jsp");
		model.addObject("functionForm", f);
		return model;
	}

	@RequestMapping(value = "/functions", method = RequestMethod.POST)
	public ModelAndView addFunction(@ModelAttribute("functionForm") FunctionForm functionForm) {
		Function r = functionForm.getFunction();
		getFunctionService().insertFunction(r);
		return new ModelAndView("redirect:/mvc/security/access/functions");
	}

	@RequestMapping(value = "/function/{functionId}", method = RequestMethod.GET)
	public ModelAndView selectFunction(@PathVariable Integer functionId) {
		Function loadedFunction = getFunctionService().selectFunction(functionId);
		setFunctionForm(new FunctionForm());
		getFunctionForm().setFunction(loadedFunction);
		getFunctionForm().setAction("update");
		return onLoad();
	}

	@RequestMapping(value = "/function/{functionId}", method = RequestMethod.POST)
	public ModelAndView updateFunction(@ModelAttribute("functionForm") FunctionForm functionForm) {
		Function f = functionForm.getFunction();
		getFunctionService().updateFunction(f);
		return new ModelAndView("redirect:/mvc/security/access/functions");
	}

	@RequestMapping(value = "/functions/delete", method = RequestMethod.POST)
	public ModelAndView deleteFunctions(@ModelAttribute("functionForm") FunctionForm functionForm) {
		List<Integer> functionList = functionForm.getSelectedFunctionList();
		
		List<Integer> selectedFunctionList_ = new ArrayList<Integer>();
		for(Object o : functionList){
			try{
				if(o==null){
					continue;
				}
				Integer i = Integer.parseInt(o.toString());
				selectedFunctionList_.add(i);
			}catch(NumberFormatException e){
				//ignore this
			}
		}
		
		getFunctionService().deleteFunction(selectedFunctionList_);
		return new ModelAndView("redirect:/mvc/security/access/functions");
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
