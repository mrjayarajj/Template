package com.baseframework.biz.security.access;

import java.util.List;

import com.baseframework.domain.security.access.Module;

public interface ModuleService {
	public void insertModule(Module m);

	public List<Module> selectAllModule();

	public Module selectModule(int id);

	public void updateModule(Module m);

	public void deleteModule(List<Integer> moduleList);
}
