package com.antonsarov.mrfj;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UI {

	public static void main(String[] args) {
		Display d = new Display();
		Shell s = new Shell(d);
		
		WizardDialog wizardDialog = new WizardDialog(s, new MRFJWizard());
		int open = wizardDialog.open();
		s.dispose();
		d.dispose();
	}
}
