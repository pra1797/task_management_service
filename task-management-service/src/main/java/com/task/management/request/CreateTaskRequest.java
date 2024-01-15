package com.task.management.request;

import javax.validation.constraints.NotBlank;

import com.task.management.exception.ApplicationErrorMessages;

import lombok.Data;

@Data
public class CreateTaskRequest {
	
	@NotBlank(message = ApplicationErrorMessages.MSG_TASK_NAME_REQUIRED)
	private String taskName;
	
	@NotBlank(message = ApplicationErrorMessages.MSG_DESCRIPTION_REQUIRED)
	private String description;
	
	@NotBlank(message = ApplicationErrorMessages.MSG_ASSIGN_TO_REQUIRED)
	private String assignTo;
}
