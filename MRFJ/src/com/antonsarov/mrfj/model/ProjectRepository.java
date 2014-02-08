package com.antonsarov.mrfj.model;

/**
 * Represents a Maven project repository
 * 
 * @author Anton Sarov
 *
 */
public class ProjectRepository {

	private String id;
	
	private String url;
	
	public ProjectRepository(String id, String url) {
		this.id = id;
		this.url = url;
	}

	public ProjectRepository() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
