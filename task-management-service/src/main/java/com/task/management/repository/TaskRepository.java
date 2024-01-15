package com.task.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.task.management.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	
//	@Query("Select * from task where task.id=?1")
//	Optional<Task> findById(Long id);
	
	Page<Task> findAll(Pageable pageable);

}
