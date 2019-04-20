package com.cognizant.iiht.fsd.casestudy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.iiht.fsd.casestudy.dao.TaskRepository;
import com.cognizant.iiht.fsd.casestudy.model.ParentTaskDo;
import com.cognizant.iiht.fsd.casestudy.model.Task;
import com.cognizant.iiht.fsd.casestudy.model.TaskDto;
import com.cognizant.iiht.fsd.casestudy.service.TaskService;


@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepository taskrepository;
	
	@Override
	public Task addTask(TaskDto taskDto) {
		Task taskDo = new Task();
		taskDo.setTaskId(taskDto.getTaskId());
		taskDo.setTask(taskDto.getTask());
		taskDo.setStartDate(taskDto.getStartDate());
		taskDo.setEndDate(taskDto.getEndDate());
		taskDo.setPriority(taskDto.getPriority());
		
		
		System.out.println("taskDto.getParentName:"+taskDto.getParentName());
		
		if(taskDto.getParentName()!=null &&
				 ! taskDto.getParentName().isEmpty() ){
			
			Task taskDoTemp = findTaskByName(taskDto.getParentName());
			System.out.println("taskDoTemp:"+taskDoTemp);
			ParentTaskDo parentTaskDo = new ParentTaskDo( taskDoTemp.getTaskId() , taskDoTemp.getTask() );
			
			taskDo.setParentTaskDo(parentTaskDo);
			parentTaskDo.setTaskdo(taskDo);
			
			System.out.println("taskDo111:"+taskDo.getParentTaskDo().getParentId());
			System.out.println("ParentTaskDo222:"+parentTaskDo.getTaskdo().getParentTaskDo().getParentId());
			
			
		}
		
		return taskrepository.save(taskDo);
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) {
		Task taskDo = findById(taskDto.getTaskId());
		if(taskDo!=null){
			BeanUtils.copyProperties(taskDto, taskDo);
			taskrepository.save(taskDo);
		}
		
		return taskDto;
		
	}

	@Override
	public void deleteTask(long taskId) {
		taskrepository.deleteById(taskId);
		
	}

	@Override
	public Task findTask(long taskId) {
		return findById(taskId);
	}
	
	public Task findById(long taskId) {
		
		Optional<Task> taskDo = taskrepository.findById(taskId);
		return taskDo.isPresent() ? taskDo.get() : null;
	}

	@Override
	public TaskDto updateParentTask(TaskDto taskDto) {
		return null;
	}

	@Override
	public List<Task> findAllTasks() {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findAll().iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}

	@Override
	public Task findTaskByName(String taskName) {
		Optional<Task> optionalTasks = taskrepository.findTaskByName(taskName);
		Task taskDoTemp = optionalTasks.isPresent() ? optionalTasks.get() : null;
		return taskDoTemp;
	}
	
	@Override
	public List<Task> findTaskByParentTask(String taskName) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTaskByParentTask(taskName).iterator().forEachRemaining(listOfTasks :: add);
		 		
		return listOfTasks;
	}

	@Override
	public List<Task> findTaskByPriority(int lowPriority, int highPriority) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTaskByPriority( lowPriority, highPriority).iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}

	@Override
	public List<Task> findTaskByDates(String startDate, String endDate) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.findTaskByDates(startDate, endDate).iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}

	@Override
	public List<Task> searchTask(String taskName) {
		List<Task> listOfTasks = new ArrayList<Task>();
		taskrepository.searchTask(taskName).iterator().forEachRemaining(listOfTasks :: add);
		return listOfTasks;
	}
	

}
