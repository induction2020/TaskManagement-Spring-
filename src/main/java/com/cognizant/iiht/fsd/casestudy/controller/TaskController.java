package com.cognizant.iiht.fsd.casestudy.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.iiht.fsd.casestudy.model.ApiResponse;
import com.cognizant.iiht.fsd.casestudy.model.Task;
import com.cognizant.iiht.fsd.casestudy.model.TaskDto;
import com.cognizant.iiht.fsd.casestudy.service.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600,  allowedHeaders = "*")
@RestController
@EnableAutoConfiguration
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping(value ="tasks123")
	public String getTaskList(){
		TaskDto taskDto = new TaskDto();
		taskDto.setTask("Thiruappathi");
		taskDto.setStartDate("Date1");
		taskDto.setEndDate("Date2");
		taskDto.setPriority(10);
		taskDto.setParentName("Thiruppathi Madhu Upd");
		taskService.addTask(taskDto);
		
		List<Task> listOfTasks = taskService.findAllTasks();
		System.out.println("listOfTasks:"+listOfTasks);
		
		Task taskDo = taskService.findTask(2);
		System.out.println("taskDo: "+taskDo);
		
		
		taskDto.setTaskId(3);
		taskDto.setTask("Thiruppathi Madhu Upd");
		taskService.updateTask(taskDto);
		
		//taskService.deleteTask(4);	
		
		return "All Task Listed here";
		
	}
	
			
	/**
	 * 
	 * Implementation of Rest Services
	 * 
	 */
	
	
	//Retrieve All Tasks
	 @GetMapping
	 public List<Task> listTasks(){
		 System.out.println("listTasks");
		return taskService.findAllTasks();
	 }
	 
	 //Retrieve One Task
	 @GetMapping("/{id}")
     public TaskDto getTask(@PathVariable int id){
		 Task task = taskService.findTask(id);
		 TaskDto taskDto = new TaskDto();
		 BeanUtils.copyProperties(task, taskDto);
		 if(task.getParentTaskDo()!=null){
			 taskDto.setParentName(task.getParentTaskDo().getParentTask());	 
		 }else{
			 taskDto.setParentName("");
		 }
		 
        return taskDto;
     }
	
	// Add Task
	@PostMapping
	public ApiResponse<Task> saveTask(@RequestBody TaskDto taskDto){
	    return new ApiResponse<Task>( HttpStatus.OK.value() , "Task saved successfully.", taskService.addTask(taskDto));
	}
	
	
	 //Update Task
	 @PutMapping("/{id}")
     public TaskDto update(@RequestBody TaskDto taskDto) {
         return taskService.updateTask(taskDto);
     }
	 
	 
	 //Delete Task
	 @DeleteMapping("/{id}")
	 public ApiResponse<Void> delete(@PathVariable int id) {
		 taskService.deleteTask(id);
	    return new ApiResponse<>(HttpStatus.OK.value(), "Task deleted successfully.", null);
	 }
			
	 //Search Task By Name
	 @RequestMapping(value="/searchByTask/{taskName}")
	 public ApiResponse<List<Task>> searchTaskByName(@PathVariable("taskName") String taskName ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByName(taskName));
		
		//Example: http://localhost:8098/tasks/searchByTask/Thiruppathi%20Madhu%20Upd
	 }
	
	//Search Task By Parent Task
	 @RequestMapping(value="/searchByParentTask/{taskName}")
	 public ApiResponse<List<Task>> searchTaskByParentTask(@PathVariable("taskName") String taskName ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByParentTask(taskName) );
		
		//Example: http://localhost:8098/tasks/searchByParentTask/Thiruppathi%20Madhu
	 }
	 
	 
	//Search Task By Priority
	 @RequestMapping(value="/searchTaskByPriority/{lowPriority}/{highPriority}")
	 public ApiResponse<List<Task>> searchTaskByPriority(@PathVariable("lowPriority") int lowPriority,
			 					@PathVariable("highPriority") int highPriority  ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByPriority(lowPriority, highPriority) );
		
		//Example: http://localhost:8098/tasks/searchByParentTask/Thiruppathi%20Madhu
	 }
	 
	//Search Task By Date
	 @RequestMapping(value="/searchTaskByDates/{startDate}/{endDate}")
	 public ApiResponse<List<Task>> searchTaskByDates(@PathVariable("startDate") String startDate,
			 					@PathVariable("endDate") String endDate  ){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tasks fetched successfully.", taskService.findTaskByDates(startDate, endDate) );
		
		//Example: http://localhost:8098/tasks/searchByParentTask/Thiruppathi%20Madhu
	 }
	

}
