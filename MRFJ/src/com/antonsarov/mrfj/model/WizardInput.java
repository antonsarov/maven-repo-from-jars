package com.antonsarov.mrfj.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Anton Sarov
 *
 */
public class WizardInput {
	
	private static Set<Dependency> dependencies;

	private static ProjectRepository repo;
	
	public static Set<Dependency> getDependencies() {
		return dependencies;
	}

	public static void createDependencies(String path, String[] selectedFiles) {
		dependencies = new HashSet<>();
		for (String s : selectedFiles) {
			dependencies.add(new Dependency(path+File.separator+s));
		}
	}
	
	public static ProjectRepository createRepo(String id, String url) {
		repo = new ProjectRepository(id, url);
		return repo;
	}
	
	public static void createRepo() {
		repo = new ProjectRepository();
	}
	
	public static ProjectRepository getRepo() {
		return repo;
	}

}
