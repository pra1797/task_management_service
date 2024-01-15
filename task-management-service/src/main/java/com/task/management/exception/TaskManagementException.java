package com.task.management.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class TaskManagementException extends RuntimeException{

	private static final long serialVersionUID = 5869611088661388254L;

	public TaskManagementException(String message) {
		super(message);
	}
}
