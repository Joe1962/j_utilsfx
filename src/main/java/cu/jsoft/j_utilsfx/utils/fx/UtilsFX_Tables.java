/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.utils.fx;

import com.opencsv.CSVWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author joe1962
 */
public class UtilsFX_Tables {
	
	public static <T> void exportTableViewToCSVRawJava(TableView<T> tableView, Writer writer) throws IOException {
		List<TableColumn<T, ?>> visibleColumns = tableView.getColumns().stream()
				  .filter(TableColumn::isVisible)
				  .collect(Collectors.toList());

		// Write header
		writer.write(visibleColumns.stream().map(TableColumn::getText).collect(Collectors.joining(",")));
		writer.write("\n");

		// Write rows
		for (T item : tableView.getItems()) {
			List<String> rowValues = new ArrayList<>();
			for (TableColumn<T, ?> column : visibleColumns) {
				// Use raw type to bypass generic issues
				@SuppressWarnings("rawtypes")
				TableColumn.CellDataFeatures features = new TableColumn.CellDataFeatures(tableView, column, item);
				ObservableValue<?> observable = column.getCellValueFactory().call(features);
				Object value = observable.getValue();
				String text = value != null ? value.toString() : "";
				// CSV escaping
				text = text.replace("\"", "\"\"");
				if (text.contains(",") || text.contains("\"") || text.contains("\n")) {
					text = "\"" + text + "\"";
				}
				rowValues.add(text);
			}
			writer.write(String.join(",", rowValues));
			writer.write("\n");
		}
	}

	public static <T> void exportTableViewToCSV(TableView<T> tableView, Writer writer) {
		// Get visible columns in display order
		List<TableColumn<T, ?>> visibleColumns = tableView.getColumns().stream()
				  .filter(TableColumn::isVisible)
				  .collect(Collectors.toList());

		try (CSVWriter csvWriter = new CSVWriter(writer,
				  CSVWriter.DEFAULT_SEPARATOR,
				  CSVWriter.NO_QUOTE_CHARACTER, // we let OpenCSV decide quoting
				  CSVWriter.DEFAULT_ESCAPE_CHARACTER,
				  CSVWriter.DEFAULT_LINE_END)) {

			// Write header row
			String[] header = visibleColumns.stream()
					  .map(TableColumn::getText)
					  .toArray(String[]::new);
			csvWriter.writeNext(header);

			// Write data rows
			for (T item : tableView.getItems()) {
				String[] rowData = new String[visibleColumns.size()];
				for (int i = 0; i < visibleColumns.size(); i++) {
					TableColumn<T, ?> column = visibleColumns.get(i);
					// Retrieve the cell value via the column's cell value factory
					@SuppressWarnings({"unchecked", "rawtypes"})
					TableColumn.CellDataFeatures features = new TableColumn.CellDataFeatures(tableView, column, item);
					ObservableValue<?> observable = column.getCellValueFactory().call(features);
					Object value = observable.getValue();
					rowData[i] = value != null ? value.toString() : "";
				}
				csvWriter.writeNext(rowData); // OpenCSV handles escaping automatically
			}
		} catch (IOException e) {
			e.printStackTrace();
			// handle exception appropriately
		}
	}

}
