package com.antonsarov.mrfj;

import org.eclipse.jface.wizard.Wizard;

import com.antonsarov.mrfj.page.CreateDependenciesPage;
import com.antonsarov.mrfj.page.DefineRepositoryPage;
import com.antonsarov.mrfj.page.ExecuteCommandsPage;
import com.antonsarov.mrfj.page.GetPOMPage;
import com.antonsarov.mrfj.page.SelectJarsPage;

/**
 * @author Anton Sarov
 *
 */
public class MRFJWizard extends Wizard {
	
	private DefineRepositoryPage defineRepositoryPage;
	private SelectJarsPage selectJarsPage;
	private CreateDependenciesPage createDependenciesPage;
	private ExecuteCommandsPage executeCommandsPage;
	private GetPOMPage pomPage;
	
	@Override
	public void addPages() {
		defineRepositoryPage = new DefineRepositoryPage("Define Project Repository");
		selectJarsPage = new SelectJarsPage("Select JAR files");
		createDependenciesPage = new CreateDependenciesPage("Create Maven artifacts");
		executeCommandsPage = new ExecuteCommandsPage("Execute commands");
		pomPage = new GetPOMPage("Get POM");
		
		addPage(defineRepositoryPage);
		addPage(selectJarsPage);
		addPage(createDependenciesPage);
		addPage(executeCommandsPage);
		addPage(pomPage);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	public void refreshCreateDependenciesPage() {
		createDependenciesPage.refreshTable();
	}

	public void refreshPOMPage() {
		pomPage.refreshTexts();
	}
}
