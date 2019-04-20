package com.cognizant.iiht.fsd.casestudy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.iiht.fsd.casestudy.model.Task;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	@Query("SELECT t FROM Task t WHERE task= :name ") 
	Optional<Task> findTaskByName(@Param("name") String name);
	
	@Query("SELECT t FROM Task t WHERE task= :name ") 
	List<Task> searchTask(@Param("name") String name);
	
	@Query("SELECT t FROM Task t, ParentTaskDo p WHERE p.parentTask= :name and t.taskId = p.task.taskId ") 
	List<Task> findTaskByParentTask(@Param("name") String name);
	
	
	@Query("SELECT t FROM Task t WHERE t.priority >= :lowPriority and t.priority <= :highPriority ") 
	public List<Task> findTaskByPriority(@Param("lowPriority") int lowPriority, @Param("highPriority") int highPriority);

	@Query("SELECT t FROM Task t WHERE t.startDate >= :startDate and t.endDate 	<= :endDate ")
	public List<Task> findTaskByDates(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	
} 
