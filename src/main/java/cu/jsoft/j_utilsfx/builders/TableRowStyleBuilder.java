/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * Flexible table row factory with selection-state-aware styling
 *
 * @param <T> The table model type
 */
public class TableRowStyleBuilder<T> {
	private final List<StyleRule<T>> rules = new ArrayList<>();

	public enum ApplyState {
		UNSELECTED_ONLY, // Apply only when row is not selected
		SELECTED_ONLY, // Apply only when row is selected  
		ALWAYS, // Apply regardless of selection state
		NEVER               // Never apply (useful for conditional disabling)
	}

	/**
	 * Add a styling rule
	 *
	 * @param condition Predicate that returns true for rows to style
	 * @param style CSS style string to apply
	 * @param applyState When to apply the style (selection state)
	 * @return
	 */
	public TableRowStyleBuilder<T> addRule(Predicate<T> condition, String style, ApplyState applyState) {
		rules.add(new StyleRule<>(condition, style, applyState));
		return this;
	}

	/**
	 * Convenience method: default to UNSELECTED_ONLY
	 *
	 * @param condition
	 * @param style
	 * @return
	 */
	public TableRowStyleBuilder<T> addRule(Predicate<T> condition, String style) {
		return addRule(condition, style, ApplyState.UNSELECTED_ONLY);
	}

	/**
	 * Build the row factory
	 *
	 * @return
	 */
	public Callback<TableView<T>, TableRow<T>> build() {
		return tableView -> new TableRow<T>() {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);
				updateStyle(item, empty);
			}

			@Override
			public void updateSelected(boolean selected) {
				super.updateSelected(selected);
				updateStyle(getItem(), isEmpty());
			}

			private void updateStyle(T item, boolean empty) {
				if (empty || item == null) {
					setStyle("");
					return;
				}

				StringBuilder styleBuilder = new StringBuilder();
				boolean isSelected = isSelected();

				for (StyleRule<T> rule : rules) {
					if (rule.condition.test(item)) {
						boolean shouldApply = false;
						switch (rule.applyState) {
							case UNSELECTED_ONLY:
								shouldApply = !isSelected;
								break;
							case SELECTED_ONLY:
								shouldApply = isSelected;
								break;
							case ALWAYS:
								shouldApply = true;
								break;
							case NEVER:
								shouldApply = false;
								break;
						}
						if (shouldApply) {
							styleBuilder.append(rule.style);
							if (!styleBuilder.toString().endsWith(";")) {
								styleBuilder.append(";");
							}
						}
					}
				}

				setStyle(styleBuilder.toString());
			}
		};
	}

	private static class StyleRule<T> {
		final Predicate<T> condition;
		final String style;
		final ApplyState applyState;

		StyleRule(Predicate<T> condition, String style, ApplyState applyState) {
			this.condition = condition;
			this.style = style;
			this.applyState = applyState;
		}
	}
}
