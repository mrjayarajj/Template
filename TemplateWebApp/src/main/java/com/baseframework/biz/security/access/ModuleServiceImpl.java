package com.baseframework.biz.security.access;

import java.util.List;

import com.baseframework.dao.security.access.ModuleDAO;
import com.baseframework.domain.security.access.Module;

public class ModuleServiceImpl implements ModuleService {
	
	private ModuleDAO moduleDAO = null;

	public void deleteModule(List<Integer> moduleList) {
		getModuleDAO().deleteModule(moduleList);
	}

	public void insertModule(Module m) {
		getModuleDAO().insertModule(m);
	}

	public List<Module> selectAllModule() {		
		return getModuleDAO().selectAllModule();
	}

	public Module selectModule(int id) {		
		return getModuleDAO().selectModule(id);
	}

	public void updateModule(Module m) {
		getModuleDAO().updateModule(m);
	}

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

}
