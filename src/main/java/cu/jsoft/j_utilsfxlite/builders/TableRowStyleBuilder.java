/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author joe1962
 * @param <T>
 */
public class TableRowStyleBuilder<T> {

	private final List<StyleRule<T>> rules = new ArrayList<>();

	public TableRowStyleBuilder<T> addRule(Predicate<T> condition, String style) {
		rules.add(new StyleRule<>(condition, style));
		return this;
	}

	public Callback<TableView<T>, TableRow<T>> build() {
		return tableView -> new TableRow<T>() {
			@Override
			protected void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setStyle("");
					return;
				}

				for (StyleRule<T> rule : rules) {
					if (rule.condition.test(item)) {
						setStyle(rule.style);
						return;
					}
				}
				setStyle("");
			}
		};
	}

	private static class StyleRule<T> {

		final Predicate<T> condition;
		final String style;

		StyleRule(Predicate<T> condition, String style) {
			this.condition = condition;
			this.style = style;
		}
	}
}
