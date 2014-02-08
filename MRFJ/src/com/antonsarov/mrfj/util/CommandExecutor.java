package com.antonsarov.mrfj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.antonsarov.mrfj.model.Dependency;

/**
 * @author Anton Sarov
 *
 */
public class CommandExecutor {
	
	private String localRepositoryPath;

	public int installFile(Dependency dependency) {
		//mvn install:install-file -DlocalRepositoryPath=repo -DcreateChecksum=true 
		//-Dpackaging=jar -Dfile=[your-jar] -DgroupId=[...] -DartifactId=[...] -Dversion=[...]
		
		List<String> command = new LinkedList<>();
		command.add("cmd");
		command.add("/c");
		command.add("mvn");
		command.add("install:install-file");
		command.add("-DlocalRepositoryPath="+localRepositoryPath);
		command.add("-DcreateChecksum=true");
		command.add("-Dpackaging=jar");
		command.add("-Dfile="+dependency.getFile());
		command.add("-DgroupId="+dependency.getGroupId());
		command.add("-DartifactId="+dependency.getArtifactId());
		command.add("-Dversion="+dependency.getVersion());
		
//		StringBuilder sb = new StringBuilder();
//		sb.append("mvn install:install-file -DlocalRepositoryPath=");
//		sb.append(localRepositoryPath);
//		sb.append(" -DcreateChecksum=true -Dpackaging=jar -Dfile=");
//		sb.append(dependency.getFile());
//		sb.append(" -DgroupId=");
//		sb.append(dependency.getGroupId());
//		sb.append(" -DartifactId=");
//		sb.append(dependency.getArtifactId());
//		sb.append(" -Dversion=");
//		sb.append(dependency.getVersion());
		
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", sb.toString());
		ProcessBuilder pb = new ProcessBuilder(command);
		pb.redirectErrorStream(true);
		
		Process p;
		try {
			p = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
			    System.out.println(line);
			}
			
			return p.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String getLocalRepositoryPath() {
		return localRepositoryPath;
	}

	public void setLocalRepositoryPath(String localRepositoryPath) {
		this.localRepositoryPath = localRepositoryPath;
	}
}
