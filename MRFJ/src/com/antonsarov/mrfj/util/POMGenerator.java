package com.antonsarov.mrfj.util;

import java.util.Collection;

import com.antonsarov.mrfj.model.Dependency;
import com.antonsarov.mrfj.model.ProjectRepository;

/**
 * @author Anton Sarov
 *
 */
public class POMGenerator {
	
	/**
	 * Generates an XML element for a specific dependency.
	 * The format is as following:<br/>
	 * 
	 * <dependency>
	 *     <groupId>your.group.id</groupId>
	 *     <artifactId>3rdparty</artifactId>
	 *     <version>X.Y.Z</version>
	 * </dependency>
	 * 
	 * @param dependency
	 * @returna a String representation of the XML element
	 */
	public static String generateDependency(Dependency dependency) {
		StringBuilder sb = new StringBuilder();
		sb.append("<dependency>");
		sb.append(System.lineSeparator());
			sb.append("<groupId>");
				sb.append(dependency.getGroupId());
			sb.append("</groupId>");
			sb.append(System.lineSeparator());
			sb.append("<artifactId>");
				sb.append(dependency.getArtifactId());
			sb.append("</artifactId>");
			sb.append(System.lineSeparator());
			sb.append("<version>");
				sb.append(dependency.getVersion());
			sb.append("</version>");
		sb.append(System.lineSeparator());
		sb.append("</dependency>");
		return sb.toString();
	}
	
	public static String generateDependencies(Collection<Dependency> dependencies) {
		StringBuilder sb = new StringBuilder();
		for (Dependency dependency : dependencies) {
			sb.append(generateDependency(dependency));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	/**
	 * Generates an XML element for a specific repository.
	 * The format is as following:<br/>
	 * 
	 * <pre>
	 * {@code
	 * <repository>
	 *     <id>my-repository</id>
	 *     <url>file://${project.basedir}/repo</url>
	 * </repository>
	 * }
	 * </pre>
	 * 
	 * @param repo the repository object
	 * @return a String representation of the XML element
	 */
	public static String generateProjectRepository(ProjectRepository repo) {
		StringBuilder sb = new StringBuilder();
		sb.append("<repository>");
		sb.append(System.lineSeparator());
			sb.append("<id>");
				sb.append(repo.getId());
			sb.append("</id>");
			sb.append(System.lineSeparator());
			sb.append("<url>");
				sb.append(repo.getUrl());
			sb.append("</url>");
		sb.append(System.lineSeparator());
		sb.append("</repository>");
		return sb.toString();
	}
}
