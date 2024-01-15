package com.task.management.response;

import java.io.Serializable;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.task.management.entity.Task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class TaskResponse implements Serializable {

	private static final long serialVersionUID = 643215803831940858L;
	
	private Long id;
	
	private String taskName;
	
	private String description;
	
	private String assignTo;
	
	public static final Function<Task, TaskResponse> fromTask = entity -> 
	TaskResponse.builder()
	.id(entity.getId())
	.taskName(entity.getTaskName())
	.description(entity.getDescription())
	.assignTo(entity.getAssignTo())
	.build();
}
