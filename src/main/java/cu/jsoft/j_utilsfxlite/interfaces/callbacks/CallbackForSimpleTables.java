/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cu.jsoft.j_utilsfxlite.interfaces.callbacks;

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
