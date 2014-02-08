package com.antonsarov.mrfj.page;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;

import com.antonsarov.mrfj.MRFJWizard;
import com.antonsarov.mrfj.model.WizardInput;

/**
 * @author Anton Sarov
 *
 */
public class SelectJarsPage extends WizardPage {
	
	private Composite composite;
	private List fileList;

	public SelectJarsPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Select JAR files to include in the project repository");
		
		setPageComplete(false);
	}
	
	@Override
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		Button browseButton = new Button(composite, SWT.PUSH);
		fileList = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		fileList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		browseButton.setText("Browse...");
		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(getShell(), SWT.MULTI);
				fileDialog.setFilterExtensions(new String[]{"*.jar"});
				fileDialog.open();
				String[] selectedFiles = fileDialog.getFileNames();
				String filterPath = fileDialog.getFilterPath();
				
				for (String file : selectedFiles) {
					fileList.add(filterPath+File.separator+file);
				}
				
				if (selectedFiles.length>0) {
					setPageComplete(true);

					WizardInput.createDependencies(filterPath, selectedFiles);
					((MRFJWizard) getWizard()).refreshCreateDependenciesPage();
				}
			}
		});
		
		setControl(composite);
	}

}
