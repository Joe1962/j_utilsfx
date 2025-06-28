/*
 * Copyright Joe1962
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
