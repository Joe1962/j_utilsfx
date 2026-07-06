/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.utils.fx;

import java.util.WeakHashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.ScrollEvent;

/**
 *
 * @author joe1962
 */
public class UtilsFX_combo {
	private static final WeakHashMap<Control, Double> lastDeltaMap = new WeakHashMap<>();

	/**
	 * Enables mouse‑wheel scrolling for ComboBox and ChoiceBox. The control will
	 * scroll one item per wheel step.
	 *
	 * @param control the ComboBox or ChoiceBox to enable scrolling on
	 * @throws IllegalArgumentException if the control is neither a ComboBox nor
	 * a ChoiceBox
	 */
	public static void setupScrolling(Control control) {
		if (!(control instanceof ComboBox || control instanceof ChoiceBox)) {
			throw new IllegalArgumentException("Only ComboBox and ChoiceBox are supported");
		}

		control.addEventFilter(ScrollEvent.SCROLL, event -> handleScroll(event, control));
	}

	private static void handleScroll(ScrollEvent event, Control control) {
		// Resolve selection model and items list based on control type
		SelectionModel<?> selectionModel;
		ObservableList<?> items;

		if (control instanceof ComboBox) {
			ComboBox<?> combo = (ComboBox<?>) control;
			selectionModel = combo.getSelectionModel();
			items = combo.getItems();
		} else { // ChoiceBox
			ChoiceBox<?> choice = (ChoiceBox<?>) control;
			selectionModel = choice.getSelectionModel();
			items = choice.getItems();
		}

		double delta = event.getDeltaY();

		// Zero‑delta events reset the stored delta (acts as a separator between scroll steps)
		if (delta == 0) {
			lastDeltaMap.put(control, null);
			event.consume();
			return;
		}

		// Skip duplicates of the same non‑zero delta within one scroll step
		Double lastDelta = lastDeltaMap.get(control);
		if (lastDelta != null && Double.compare(delta, lastDelta) == 0) {
			event.consume();
			return;
		}
		lastDeltaMap.put(control, delta);

		// Perform the selection change
		int currentIndex = selectionModel.getSelectedIndex();
		int itemCount = items.size();
		if (itemCount < 1) {
			event.consume();
			return;
		}

		int scrollSteps = (int) Math.signum(delta);
		int newIndex = currentIndex - scrollSteps; // natural scrolling (up = previous, down = next)
		newIndex = Math.max(0, Math.min(itemCount - 1, newIndex));

		if (newIndex != currentIndex) {
			selectionModel.select(newIndex);
		}

		event.consume();
	}

}
