package com.cognizant.iiht.fsd.casestudy.service;

import java.util.List;
import java.util.Optional;

import com.cognizant.iiht.fsd.casestudy.model.Task;
import com.cognizant.iiht.fsd.casestudy.model.TaskDto;


public interface TaskService {

//CRDU operation
public Task addTask(TaskDto taskDto);
public TaskDto updateTask(TaskDto taskDto);
public void deleteTask(long taskId);
public Task findTask(long taskId);
public Task findTaskByName(String taskName);
public List<Task> searchTask(String taskName);

//Update Parent Task
public TaskDto updateParentTask(TaskDto taskDto);

//Populate all Tasks
public List<Task> findAllTasks();

//Populate all Tasks
public List<Task> findTaskByParentTask(String taskName);

//Populate Based on Priority
public List<Task> findTaskByPriority(int lowPriority, int highPriority);

//Populate Based on Start and End Date
public List<Task> findTaskByDates(String startDate, String endDate);

}
