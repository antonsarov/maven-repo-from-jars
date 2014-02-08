package com.antonsarov.mrfj.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.antonsarov.mrfj.model.WizardInput;

/**
 * @author Anton Sarov
 *
 */
public class DefineRepositoryPage extends WizardPage {
	
	private Composite composite;
	
	private Text repoName;
	private Text repoLocation;

	public DefineRepositoryPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("This step creates a folder with the specified name under the specified location");
		
		setPageComplete(false);
		
		WizardInput.createRepo();
	}

	@Override
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		
		new Label(composite, SWT.NONE).setText("Repository name");
		repoName = new Text(composite, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		repoName.setLayoutData(gd);
		repoName.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (e.getSource() instanceof Text) {
					String s = ((Text) e.getSource()).getText();
					if (!s.trim().isEmpty()) {
						WizardInput.getRepo().setId(s);
					}
					getWizard().getContainer().updateButtons();
				}
			}
		});
		
		new Label(composite, SWT.NONE).setText("Repository location");
		repoLocation = new Text(composite, SWT.BORDER);
		repoLocation.setEditable(false);
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		repoLocation.setLayoutData(gd);
		Button browseLocation = new Button(composite, SWT.PUSH);
		browseLocation.setText("Browse...");
		browseLocation.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
				String parentDir = directoryDialog.open();
				repoLocation.setText(parentDir);
				
				WizardInput.getRepo().setUrl(parentDir);
				
				getWizard().getContainer().updateButtons();
			}
		});
		
		setControl(composite);
	}

	@Override
	public boolean canFlipToNextPage() {
		return !repoName.getText().trim().isEmpty() && !repoLocation.getText().trim().isEmpty();
	}
}
