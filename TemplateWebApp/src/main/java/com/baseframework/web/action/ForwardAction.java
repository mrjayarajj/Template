package com.baseframework.web.action;

public class ForwardAction  {

	private String forwardTo;

	public void setForwardTo(String forwardTo) {
		this.forwardTo = forwardTo;
	}

	public String getForwardTo() {
		return forwardTo;
	}

	public String forward() throws Exception {

		String url = getForwardTo();
		
		if (url != null) {
			return "dynamicForward";
		} else {
			throw new IllegalArgumentException("forwardTo param is not set ");
		}
	}


}
