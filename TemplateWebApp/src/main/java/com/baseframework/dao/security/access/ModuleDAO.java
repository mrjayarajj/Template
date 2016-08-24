package com.baseframework.dao.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Module;

public interface ModuleDAO {
	public void insertModule(Module m);

	public List<Module> selectAllModule();

	public Module selectModule(int id);

	public void updateModule(Module sourceModule);

	public void deleteModule(List<Integer> moduleList);
}
