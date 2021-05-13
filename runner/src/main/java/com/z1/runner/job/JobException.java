package com.z1.runner.job;

import com.z1.core.CoreException;

public class JobException extends CoreException {

	private static final long serialVersionUID = 8311814911053193550L;

	public JobException(String message, Throwable cause) {
		super(message, cause);
	}

	public JobException(String message) {
		super(message);
	}

	public JobException(Throwable e) {
		super(e);
	}

}
