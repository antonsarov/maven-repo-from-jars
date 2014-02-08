package com.antonsarov.mrfj.jface;

import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.antonsarov.mrfj.model.Dependency;

/**
 * @author Anton Sarov
 *
 */
public class DependencyLabelProvider extends ColumnLabelProvider {

	private int columnIndex;
	
	public DependencyLabelProvider(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	@Override
	public String getText(Object element) {
		Dependency d = (Dependency) element;
		switch (columnIndex) {
		case 0:
			return d.getFile();
		case 1:
			return d.getGroupId();
		case 2:
			return d.getArtifactId();
		case 3:
			return d.getVersion();
		}
		return null;
	}
}
