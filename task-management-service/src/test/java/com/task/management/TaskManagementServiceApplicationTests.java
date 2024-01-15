package com.task.management;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Assertions;
import com.task.management.entity.Task;
import com.task.management.exception.ApplicationErrorMessages;
import com.task.management.exception.TaskManagementException;
import com.task.management.repository.TaskRepository;
import com.task.management.request.CreateTaskRequest;
import com.task.management.response.TaskResponse;
import com.task.management.service.TaskService;

@SpringBootTest
class TaskManagementServiceApplicationTests {

	@Autowired
	TaskService taskService;

	@MockBean
	TaskRepository taskRepository;
	
	/**
	 * Create task test case
	 */

	@Test
	public void createTask() {

		Task task = new Task();
		task.setTaskName("test");
		task.setDescription("test");
		task.setAssignTo("test@gmail.com");

		CreateTaskRequest request = new CreateTaskRequest();
		request.setTaskName("test");
		request.setDescription("test");
		request.setAssignTo("test@gmail.com");

		when(taskRepository.save(task)).thenReturn(task);
		Assertions.assertEquals(TaskResponse.fromTask.apply(task), taskService.createTask(request));

	}

	/**
	 * update task exception test case
	 */
	@Test
	public void updateTaskException() {

		CreateTaskRequest request = new CreateTaskRequest();
		request.setTaskName("test");
		request.setDescription("test");
		request.setAssignTo("test@gmail.com");

		TaskManagementException exception = Assertions.assertThrows(TaskManagementException.class, () -> {
			taskService.updateTask(request, 1L);
		});
		Assertions.assertEquals(ApplicationErrorMessages.TASK_ID_NOT_FOUND, exception.getMessage());
	}

	/**
	 * delete task exception test case
	 */
	@Test
	public void deleteTaskException() {

		CreateTaskRequest request = new CreateTaskRequest();
		request.setTaskName("test");
		request.setDescription("test");
		request.setAssignTo("test@gmail.com");

		TaskManagementException exception = Assertions.assertThrows(TaskManagementException.class, () -> {
			taskService.deteleTask(1L);
		});
		Assertions.assertEquals(ApplicationErrorMessages.TASK_ID_NOT_FOUND, exception.getMessage());
	}

}
