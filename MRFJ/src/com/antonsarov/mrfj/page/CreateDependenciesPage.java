package com.antonsarov.mrfj.page;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.antonsarov.mrfj.jface.DependencyEditingSupport;
import com.antonsarov.mrfj.jface.DependencyLabelProvider;
import com.antonsarov.mrfj.model.WizardInput;

/**
 * @author Anton Sarov
 *
 */
public class CreateDependenciesPage extends WizardPage {

	private Composite composite;
	private TableViewer tableViewer;

	public CreateDependenciesPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Edit groupId, artifactId and version where needed");
	}

	@Override
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		Table table = new Table(composite, SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableViewer = new TableViewer(table);
		
		String[] columnNames = new String[]{"JAR file", "Group Id", "Artifact Id", "Version"};
		int[] bounds = {100, 100, 100, 100};
		
		for (int i=0; i<columnNames.length; i++) {
			TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
			viewerColumn.getColumn().setText(columnNames[i]);
			viewerColumn.getColumn().setWidth(bounds[i]);
			viewerColumn.setLabelProvider(new DependencyLabelProvider(i));
			viewerColumn.setEditingSupport(new DependencyEditingSupport(tableViewer, i)); 
		}
		
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(WizardInput.getDependencies());
		
		setControl(composite);
	}

	public void refreshTable() {
		tableViewer.setInput(WizardInput.getDependencies());
	}
}
