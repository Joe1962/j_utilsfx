/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cu.jsoft.j_utilsfxlite.interfaces.callbacks;

import java.util.ArrayList;

/**
 *
 * @author joe1962
 */
public interface CallbackForSelection {
	void callbackSelectionChanged();
	void callbackSelectionSingle(Object MySelection);
	void callbackSelectionMultiple(ArrayList<Object> MySelections);
	void callbackSelectionAll();
	void callbackSelectionCancelled();
}
