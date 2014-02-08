package com.antonsarov.mrfj.jface;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import com.antonsarov.mrfj.model.Dependency;

/**
 * @author Anton Sarov
 *
 */
public class DependencyEditingSupport extends EditingSupport {

	private int columnIndex;

	private final TableViewer viewer;

	private final CellEditor editor;

	public DependencyEditingSupport(TableViewer viewer, int columnIndex) {
		super(viewer);
		this.viewer = viewer;
		this.editor = new TextCellEditor(viewer.getTable());
		this.columnIndex = columnIndex;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return editor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return columnIndex != 0;
	}

	@Override
	protected Object getValue(Object element) {
		Dependency d = (Dependency) element;
		switch (columnIndex) {
		case 1:
			return checkNull(d.getGroupId());
		case 2:
			return checkNull(d.getArtifactId());
		case 3:
			return checkNull(d.getVersion());
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		Dependency d = (Dependency) element;
		switch (columnIndex) {
		case 1:
			d.setGroupId(String.valueOf(value));
			break;
		case 2:
			d.setArtifactId(String.valueOf(value));
			break;
		case 3:
			d.setVersion(String.valueOf(value));
			break;
		}
		viewer.update(d, null);
	}

	private String checkNull(String string) {
		return string==null?"":string;
	}
}
