package com.antonsarov.mrfj.model;

/**
 * Represents a Maven dependency
 * 
 * @author Anton Sarov
 *
 */
public class Dependency {
	
	private String groupId;
	
	private String artifactId;
	
	private String version;
	
	private String file;
	
	public Dependency(String groupId, String artifactId, String version) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
	}
	
	public Dependency(String file) {
		this.file = file;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
}
