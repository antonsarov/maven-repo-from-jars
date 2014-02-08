package com.antonsarov.mrfj.jface;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import com.antonsarov.mrfj.MRFJWizard;


/**
 * @author Anton Sarov
 *
 */
public class LaunchWizardHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveShell(event), new MRFJWizard());
		wizardDialog.open();
		return null;
	}

}
