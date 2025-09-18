/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfx.interfaces.callbacks;

/**
 *
 * @author joe1962
 */
public interface CallbackForSimpleTables {
	Object callbackGetData();
	void callbackSetData(Object MyData);
	void callbackSetEditingState(boolean MyState);
	void callbackSetFlagSelected(boolean MyState);
	void callbackSetFlagInserting(boolean MyState);
	boolean callbackGetFlagEditing();
	boolean callbackGetFlagRefreshing();
	boolean callbackGetFlagInserting();
}
