package ru.alibaev.myTest.model;

import java.time.LocalDateTime;

public class Documents {

	private int id;
	private String title;
	private String author;
	private String src;
	private LocalDateTime createTime;
	private LocalDateTime editTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getEditTime() {
		return editTime;
	}
	public void setEditTime(LocalDateTime editTime) {
		this.editTime = editTime;
	}

	
}
