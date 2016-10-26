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

import com.baseframework.biz.security.access.ModuleService;
import com.baseframework.domain.security.access.Module;
import com.baseframework.domain.vo.JSONDetails;

@Controller
@Scope("prototype")
@RequestMapping(value = "/security/access")
public class ModuleController implements JSONDetails {

	private static final Logger LOG = LoggerFactory.getLogger(ModuleController.class);

	private ModuleForm moduleForm = null;

	@Autowired
	private ModuleService moduleService = null;

	@RequestMapping(value = "/modules", method = RequestMethod.GET)
	public ModelAndView onLoad() {
		LOG.info("onload of Module Action");
		List<Module> moduleList = getModuleService().selectAllModule();
		ModuleForm f = getModuleForm() == null ? new ModuleForm() : getModuleForm();
		f.setModuleList(moduleList);
		setModuleForm(f);

		ModelAndView model = new ModelAndView("/jsp/base/security/module.jsp");
		model.addObject("moduleForm", f);
		return model;
	}

	@RequestMapping(value = "/modules", method = RequestMethod.POST)
	public ModelAndView addModule(@ModelAttribute("moduleForm") ModuleForm moduleForm) {
		Module m = moduleForm.getModule();
		getModuleService().insertModule(m);
		return new ModelAndView("redirect:/mvc/security/access/modules");
	}

	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.GET)
	public ModelAndView selectModule(@PathVariable Integer moduleId) {
		Module loadedModule = getModuleService().selectModule(moduleId);
		setModuleForm(new ModuleForm());
		getModuleForm().setModule(loadedModule);
		getModuleForm().setAction("update");
		return onLoad();
	}

	@RequestMapping(value = "/module/{moduleId}", method = RequestMethod.POST)
	public ModelAndView updateModule(@ModelAttribute("moduleForm") ModuleForm moduleForm) {
		Module m = moduleForm.getModule();
		getModuleService().updateModule(m);
		return new ModelAndView("redirect:/mvc/security/access/modules");
	}

	@RequestMapping(value = "/modules/delete", method = RequestMethod.POST)
	public ModelAndView deleteModules(@ModelAttribute("moduleForm") ModuleForm moduleForm) {
		List<Integer> moduleList = moduleForm.getSelectedModuleList();

		List<Integer> selectedModuleList_ = new ArrayList<Integer>();
		for (Object o : moduleList) {
			try {
				if (o == null) {
					continue;
				}

				Integer i = Integer.parseInt(o.toString());
				selectedModuleList_.add(i);
			} catch (NumberFormatException e) {
				// ignore this
			}
		}

		getModuleService().deleteModule(selectedModuleList_);
		return new ModelAndView("redirect:/mvc/security/access/modules");
	}

	public ModuleForm getModuleForm() {
		return moduleForm;
	}

	public void setModuleForm(ModuleForm moduleForm) {
		this.moduleForm = moduleForm;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public Object getJavaValue() {
		return getModuleForm();
	}

}
