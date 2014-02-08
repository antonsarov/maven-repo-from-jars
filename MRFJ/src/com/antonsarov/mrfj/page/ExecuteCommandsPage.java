package com.antonsarov.mrfj.page;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.antonsarov.mrfj.MRFJWizard;
import com.antonsarov.mrfj.model.Dependency;
import com.antonsarov.mrfj.model.WizardInput;
import com.antonsarov.mrfj.util.CommandExecutor;

/**
 * @author Anton Sarov
 *
 */
public class ExecuteCommandsPage extends WizardPage {

	private Composite composite;
	private Label status;

	public ExecuteCommandsPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Execute commands");
		
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		final Button startInstallButton = new Button(composite, SWT.PUSH);
		startInstallButton.setText("Start installation");
		GridData gd = new GridData();
		gd.verticalSpan = 2;
		startInstallButton.setLayoutData(gd);
		startInstallButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				startInstallButton.setEnabled(false);
				
				final CommandExecutor ce = new CommandExecutor();
				ce.setLocalRepositoryPath(WizardInput.getRepo().getUrl()+File.separator+WizardInput.getRepo().getId());
				
				new File(WizardInput.getRepo().getUrl()+File.separator+WizardInput.getRepo().getId()).mkdir();
				
				try {
					new ProgressMonitorDialog(getShell()).run(true, false, new IRunnableWithProgress() {
						
						@Override
						public void run(IProgressMonitor monitor) throws InvocationTargetException,
								InterruptedException {
							monitor.beginTask("Installing", WizardInput.getDependencies().size());
							
							for (final Dependency d : WizardInput.getDependencies()) {
								monitor.setTaskName("Installing " + d.getGroupId() + "/" + d.getArtifactId() + "/" + d.getVersion());
								int resultOfInstall = ce.installFile(d);
								if (resultOfInstall==0) {
									monitor.worked(1);
								} else {
									return;
								}
							}
							
							monitor.done();
						}
					});
				} catch (InvocationTargetException | InterruptedException e1) {
					e1.printStackTrace();
				}
				
				((MRFJWizard) getWizard()).refreshPOMPage();
				setPageComplete(true);
			}
		});
		
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;

		status = new Label(composite, SWT.NONE);
		status.setLayoutData(gd);
		
		setControl(composite);
	}

}
