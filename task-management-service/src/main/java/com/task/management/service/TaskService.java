package com.task.management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.task.management.entity.Task;
import com.task.management.request.CreateTaskRequest;
import com.task.management.response.TaskResponse;

public interface TaskService {
	
	TaskResponse createTask(final CreateTaskRequest createTaskRequest);
	
	TaskResponse updateTask(final CreateTaskRequest createTaskRequest, final Long id);
	
	void deteleTask(final Long id);
	
	TaskResponse getTaskById(final Long id);
	
	Page<Task> getAllTask(Pageable pageable);
}
