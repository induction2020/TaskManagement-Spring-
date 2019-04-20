package com.cognizant.iiht.fsd.casestudy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="ParentTaskDo")
@Table(name="PARENT_TASK")
public class ParentTaskDo implements Serializable{

	public ParentTaskDo(){
		
	}
	
	public ParentTaskDo(long parentId, String parentTask) {
		super();
		this.parentId = parentId;
		this.parentTask = parentTask;
	}
	
	
	@Id
	@Column(name="PARENT_ID")
	private long parentId;
	
	@Column(name="PARENT_TASK")
	private String parentTask;
	
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getParentTask() {
		return parentTask;
	}
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "TASK_ID", nullable = false)
	@JsonIgnore
	private Task task;

	@JsonIgnore
	public Task getTaskdo() {
		return task;
	}

	public void setTaskdo(Task taskdo) {
		this.task = taskdo;
	}
    
	
}
