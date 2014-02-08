package com.antonsarov.mrfj.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.antonsarov.mrfj.model.WizardInput;
import com.antonsarov.mrfj.util.POMGenerator;

/**
 * @author Anton Sarov
 *
 */
public class GetPOMPage extends WizardPage {

	private Composite composite;

	private Text repoText;
	private Text dependenciesText;

	public GetPOMPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Copy these to your project's pom.xml");
	}

	@Override
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());

		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;

		new Label(composite, SWT.NONE).setText("Repository");
		repoText = new Text(composite, SWT.MULTI | SWT.BORDER);
		repoText.setLayoutData(gd);
		repoText.setEditable(false);

		new Label(composite, SWT.NONE).setText("Dependencies");
		dependenciesText = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		dependenciesText.setLayoutData(gd);
		dependenciesText.setEditable(false);

		setControl(composite);
	}

	public void refreshTexts() {
		repoText.setText(POMGenerator.generateProjectRepository(WizardInput.getRepo()));
		dependenciesText.setText(POMGenerator.generateDependencies(WizardInput.getDependencies()));
	}

}
