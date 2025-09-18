/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfx.interfaces.callbacks;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author joe1962
 */
public interface CallbackForTrees {
	void callbackSelectionChanged(DefaultMutableTreeNode SelectedNode);
	void callbackSetSelectedNode(DefaultMutableTreeNode SelectedNode);
}
