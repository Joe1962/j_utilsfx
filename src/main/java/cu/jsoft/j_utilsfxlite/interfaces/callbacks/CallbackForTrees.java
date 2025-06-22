/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cu.jsoft.j_utilsfxlite.interfaces.callbacks;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author joe1962
 */
public interface CallbackForTrees {
	void callbackSelectionChanged(DefaultMutableTreeNode SelectedNode);
	void callbackSetSelectedNode(DefaultMutableTreeNode SelectedNode);
}
