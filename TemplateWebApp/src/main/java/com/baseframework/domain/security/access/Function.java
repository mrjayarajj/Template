package com.baseframework.domain.security.access;

import org.springframework.security.core.GrantedAuthority;

public class Function implements java.io.Serializable, GrantedAuthority {

	private static final long serialVersionUID = 365876984859891L;

	private Integer functionId;
	private String functionName;
	private String functionMnemonic;
	private Module module;
	
	public Function(){
		
	}

	public Function(Integer functionId, String functionMnemonic) {
		this.functionId = functionId;
		this.functionMnemonic = functionMnemonic;
	}

	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionMnemonic() {
		return this.functionMnemonic;
	}

	public void setFunctionMnemonic(String functionMnemonic) {
		this.functionMnemonic = functionMnemonic;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getAuthority() {
		return getFunctionMnemonic();
	}

	public int compareTo(Object o) {
		if (o instanceof Function) {
			Function f = (Function) o;
			return getFunctionMnemonic().compareTo(f.getFunctionMnemonic());
		} else {
			throw new ClassCastException("Invalid object");
		}
	}

	public String toString() {
		return this.functionId + "-" + this.functionMnemonic;
	}

}
