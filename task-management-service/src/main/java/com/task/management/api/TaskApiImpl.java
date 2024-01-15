package com.task.management.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.management.entity.Task;
import com.task.management.exception.ApplicationErrorMessages;
import com.task.management.exception.TaskManagementException;
import com.task.management.request.CreateTaskRequest;
import com.task.management.response.TaskResponse;
import com.task.management.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/task")
@Slf4j
@CrossOrigin(origins = "*")
public class TaskApiImpl implements TaskApi {

	private final TaskService taskService;

	public TaskApiImpl(final TaskService taskService) {
		this.taskService = taskService;
	}
	
	/**
	 * Create new task
	 */
	@Override
	@PostMapping(path = "/create")
	public TaskResponse createTask(@RequestBody final CreateTaskRequest createTaskRequest) {
		log.info("Create a new task with request:[{}]", createTaskRequest);
		return taskService.createTask(createTaskRequest);
	}

	/**
	 * Update existing task data by id
	 */
	@Override
	@PutMapping(path = "/{id}")
	public TaskResponse updateTask(@RequestBody final CreateTaskRequest createTaskRequest, @PathVariable("id") final Long id) {
		log.info("Update task data for id:[{}]", id);
		if(id == null) {
			throw new TaskManagementException(ApplicationErrorMessages.TASK_ID_REQUIRED);
		}
		return taskService.updateTask(createTaskRequest, id);
	}

	/**
	 * Delete task by id
	 */
	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{id}")
	public void deleteTask(@PathVariable("id") final Long id) {
		log.info("Delete task of id:[{}]", id);
		if(id == null) {
			throw new TaskManagementException(ApplicationErrorMessages.TASK_ID_REQUIRED);
		}
		taskService.deteleTask(id);
	}

	/**
	 * Get perticuler task data by id
	 */
	@Override
	@GetMapping(path = "/{id}")
	public TaskResponse getTaskById(@PathVariable("id") Long id) {
		log.info("Get task data for id:[{}]", id);
		if(id == null) {
			throw new TaskManagementException(ApplicationErrorMessages.TASK_ID_REQUIRED);
		}
		return taskService.getTaskById(id);
	}

	/**
	 * Gat all tasks data
	 */
	@Override
	@GetMapping(path = "/getAllTask")
	public Page<Task> getAllTask(@PageableDefault(sort = {"id"}) final Pageable pageable) {
		return taskService.getAllTask(pageable);
	}
}
