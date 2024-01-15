package com.task.management.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.task.management.entity.Task;
import com.task.management.exception.ApplicationErrorMessages;
import com.task.management.exception.TaskManagementException;
import com.task.management.repository.TaskRepository;
import com.task.management.request.CreateTaskRequest;
import com.task.management.response.TaskResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

	private TaskRepository taskRepository;

	public TaskServiceImpl(final TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public TaskResponse createTask(CreateTaskRequest createTaskRequest) {

		log.info("Create new task service");

		Task task = new Task();
		try {
			task.setTaskName(createTaskRequest.getTaskName());
			task.setDescription(createTaskRequest.getDescription());
			task.setAssignTo(createTaskRequest.getAssignTo());

			task = taskRepository.save(task);
		} catch (Exception e) {
			log.error("Fialed to create new task:", e);
		}

		return TaskResponse.fromTask.apply(task);
	}

	@Override
	public TaskResponse updateTask(CreateTaskRequest createTaskRequest, Long id) {

		Task task = new Task();
		Optional<Task> taskResult = taskRepository.findById(id);

		if (taskResult.isPresent()) {
			task.setId(id);
			task.setTaskName(createTaskRequest.getTaskName());
			task.setDescription(createTaskRequest.getDescription());
			task.setAssignTo(createTaskRequest.getAssignTo());
			task = taskRepository.save(task);
		} else {
			throw new TaskManagementException(ApplicationErrorMessages.TASK_ID_NOT_FOUND);
		}
		return TaskResponse.fromTask.apply(task);
	}

	@Override
	public void deteleTask(Long id) {
		Optional<Task> task = taskRepository.findById(id);

		if (task.isPresent()) {
			taskRepository.deleteById(id);
		} else {
			throw new TaskManagementException(ApplicationErrorMessages.TASK_ID_NOT_FOUND);
		}
	}

	@Override
	public TaskResponse getTaskById(Long id) {
		Optional<Task> task = taskRepository.findById(id);

		if (!task.isPresent()) {
			throw new TaskManagementException(ApplicationErrorMessages.TASK_ID_NOT_FOUND);
		}
		return TaskResponse.fromTask.apply(task.get());
	}

	@Override
	public Page<Task> getAllTask(Pageable pageable) {
		Page<Task> task = taskRepository.findAll(pageable);
		return task;
	}

}
