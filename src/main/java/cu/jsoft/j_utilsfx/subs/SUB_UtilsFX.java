/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.subs;

import cu.jsoft.j_utilsfx.global.CONSTS;
import cu.jsoft.j_utilsfx.global.types.TYP_retLoadFXML;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Skin;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsFX {

	public static TYP_retLoadFXML loadFXML(Class callingClass, Pane container, String path, String parentID, double opacity, Font font) throws IOException {
		FXMLLoader loader = new FXMLLoader(callingClass.getResource(path));
		Parent node = loader.load();
		node.setOpacity(opacity);
		node.setId(parentID);
		container.getChildren().add(node);
		setDefaultFontToAll(node, font);
		Object controller = loader.getController();
		return new TYP_retLoadFXML(node, controller);
	}

	public static String getTableColumnTextAlignment(String field_datatype) {
		switch (field_datatype.toLowerCase()) {
			case "boolean":
				return "-fx-alignment: CENTER;";
			case "int":
				return "-fx-alignment: CENTER-RIGHT;";
			case "integer":
				return "-fx-alignment: CENTER-RIGHT;";
			case "long":
				return "-fx-alignment: CENTER-RIGHT;";
			case "double":
				return "-fx-alignment: CENTER-RIGHT;";
			case "bigdecimal":
				return "-fx-alignment: CENTER-RIGHT;";
			case "string":
				return "-fx-alignment: CENTER-LEFT;";
			case "date":
				return "-fx-alignment: CENTER;";
			default:
				return "-fx-alignment: CENTER-LEFT;";
		}
	}

	public static void setupComboBoxScrolling(ComboBox<?> comboBox) {
		comboBox.addEventFilter(ScrollEvent.SCROLL, event -> {
			handleComboBoxScroll(event, comboBox);
		});
	}

	private static void handleComboBoxScroll(ScrollEvent event, ComboBox<?> comboBox) {
		// Only process every Nth event or use a cooldown
		int currentIndex = comboBox.getSelectionModel().getSelectedIndex();
		int itemCount = comboBox.getItems().size();

		if (itemCount < 1) {
			return;
		}

		// Round the delta to get discrete steps
		int scrollSteps = (int) Math.signum(event.getDeltaY());

		if (scrollSteps != 0) {
			int newIndex = currentIndex - scrollSteps; // Invert for natural scrolling
			newIndex = Math.max(0, Math.min(itemCount - 1, newIndex));

			if (newIndex != currentIndex) {
				comboBox.getSelectionModel().select(newIndex);
			}
		}

		event.consume();
	}

	public static void setupChoiceBoxScrolling(ChoiceBox<?> chbBox) {
		chbBox.addEventFilter(ScrollEvent.SCROLL, event -> {
			handleChoiceBoxScroll(event, chbBox);
		});
	}

	private static void handleChoiceBoxScroll(ScrollEvent event, ChoiceBox<?> chbBox) {
		// Only process every Nth event or use a cooldown
		int currentIndex = chbBox.getSelectionModel().getSelectedIndex();
		int itemCount = chbBox.getItems().size();

		if (itemCount <= 1) {
			return;
		}

		// Round the delta to get discrete steps
		int scrollSteps = (int) Math.signum(event.getDeltaY());

		if (scrollSteps != 0) {
			int newIndex = currentIndex - scrollSteps; // Invert for natural scrolling
			newIndex = Math.max(0, Math.min(itemCount - 1, newIndex));

			if (newIndex != currentIndex) {
				chbBox.getSelectionModel().select(newIndex);
			}
		}

		event.consume();
	}

	public static void toFrontHelper(StackPane stp, String PaneID) {
		for (int i = 0; i < stp.getChildrenUnmodifiable().size(); i++) {
			if (stp.getChildrenUnmodifiable().get(i).getId().equals(PaneID)) {
				stp.getChildren().get(i).toFront();
				return;
			}
		}
	}

	public static void toBackHelper(StackPane stp, String PaneID) {
		for (int i = 0; i < stp.getChildrenUnmodifiable().size(); i++) {
			if (stp.getChildrenUnmodifiable().get(i).getId().equals(PaneID)) {
				stp.getChildren().get(i).toBack();
				return;
			}
		}
	}

	/**
	 * {@link ToolTipDefaultsFixer}
	 *
	 * @author <a href="mailto:daniel.armbrust.list@gmail.com">Dan Armbrust</a>
	 *
	 * Returns true if successful. Current defaults are 1000, 5000, 200;
	 *
	 * @param openDelay
	 * @param visibleDuration
	 * @param closeDelay
	 * @return
	 */
	public static boolean setTooltipTimers(long openDelay, long visibleDuration, long closeDelay) {
		// Note: current JavaFX defaults are (1000, 5000, 200).
		try {
			Field f = Tooltip.class.getDeclaredField("BEHAVIOR");
			f.setAccessible(true);

			Class[] classes = Tooltip.class.getDeclaredClasses();
			for (Class clazz : classes) {
				if (clazz.getName().equals("javafx.scene.control.Tooltip$TooltipBehavior")) {
					Constructor ctor = clazz.getDeclaredConstructor(Duration.class, Duration.class, Duration.class, boolean.class);
					ctor.setAccessible(true);
					Object tooltipBehavior = ctor.newInstance(new Duration(openDelay), new Duration(visibleDuration), new Duration(closeDelay), false);
					f.set(null, tooltipBehavior);
					break;
				}
			}
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			Logger.getLogger(SUB_UtilsFX.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		return true;
	}

	public static void doFadeInOut(Node fromNode, Node toNode, Duration fadeOutDelay, Duration fadeOutDuration, Duration fadeInDelay, Duration fadeInDuration) {
		FadeTransition fadeOut = new FadeTransition(fadeOutDuration);
		fadeOut.setFromValue(1.0);
		fadeOut.setToValue(0);
		fadeOut.setNode(fromNode);
		fadeOut.setDelay(fadeOutDelay);
		fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				doFadeIn(toNode, fadeInDelay, fadeInDuration, 1);
				fadeOut.setOnFinished(null);
			}
		});

		Platform.runLater(() -> {
			fadeOut.playFromStart();
			//fadeOut.play();
		});

	}

	public static void doFadeOut(Node fromNode, Duration fadeOutDelay, Duration fadeOutDuration, double Value) {
		FadeTransition fadeOut = new FadeTransition(fadeOutDuration, fromNode);
		fadeOut.setToValue(Value);			// Full fade out = 0...
		fadeOut.setDelay(fadeOutDelay);
		//fadeOut.playFromStart();
		fadeOut.play();
	}

	public static void doFadeIn(Node toNode, Duration fadeInDelay, Duration fadeInDuration, double Value) {
		FadeTransition fadeIn = new FadeTransition(fadeInDuration);
		fadeIn.setNode(toNode);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(Value);			// Full fade in = 1...
		fadeIn.setDelay(fadeInDelay);

		Platform.runLater(() -> {
			fadeIn.playFromStart();
			//fadeIn.play();
		});
	}

	/**
	 * Schedules a focus request for a text input control
	 *
	 * @param control The text field or text area to focus
	 * @param maxAttempts Maximum number of animation frames to try (typically
	 * 30-60 for 0.5-1 second)
	 */
	public static void scheduleFocusRequest(TextInputControl control, int maxAttempts) {
		AtomicInteger attempts = new AtomicInteger(0);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (control.isVisible() && control.getScene() != null && control.getScene().getWindow() != null) {
					control.requestFocus();
					control.selectAll();
					this.stop();
				} else if (attempts.incrementAndGet() >= maxAttempts) {
					this.stop(); // Give up after max attempts
				}
			}
		};
		timer.start();
	}

	/**
	 * Convenience method to schedules a focus request for a text input control
	 * with default attempts (50 frames ~= 0.8 seconds at 60fps)
	 *
	 * @param control
	 */
	public static void scheduleFocusRequest(TextInputControl control) {
		scheduleFocusRequest(control, 50);
	}

	/**
	 * Schedules a focus request with selection for a text input control
	 *
	 * @param control The text field or text area to focus
	 * @param maxAttempts Maximum number of animation frames to try (typically
	 * 30-60 for 0.5-1 second)
	 */
	public static void scheduleFocusRequestAndSelectAll(TextInputControl control, int maxAttempts) {
		AtomicInteger attempts = new AtomicInteger(0);

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (control.isVisible() && control.getScene() != null && control.getScene().getWindow() != null) {
					control.requestFocus();
					this.stop();
				} else if (attempts.incrementAndGet() >= maxAttempts) {
					this.stop(); // Give up after max attempts
				}
			}
		};
		timer.start();
	}

	/**
	 * Convenience method to schedules a focus request with selection for a text
	 * input control with default attempts (50 frames ~= 0.8 seconds at 60fps)
	 *
	 * @param control
	 */
	public static void scheduleFocusRequestAndSelectAll(TextInputControl control) {
		scheduleFocusRequestAndSelectAll(control, 50);
	}

	public static void doDatePickerHighlightDates(DatePicker datePicker, ArrayList<LocalDate> DatesToHighlight, boolean cssIsDarkTheme, String cssStyle, String toolTip) {
		StringBuilder cssHighlight = new StringBuilder();
		cssHighlight.append(cssStyle);
		cssHighlight.append(" ");
		if (cssIsDarkTheme) {
			cssHighlight.append(CONSTS.cssDarkCalendarHighlite);
		} else {
			cssHighlight.append(CONSTS.cssLightCalendarHighlite);
		}

		DatePickerHighlightDates(datePicker, DatesToHighlight, cssHighlight.toString(), toolTip);
	}

	private static void DatePickerHighlightDates(DatePicker datePicker, ArrayList<LocalDate> DatesToHighlight, String cssStyle, String toolTip) {
		// Apply highlighting
		datePicker.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date != null && !empty && DatesToHighlight.contains(date)) {
					setStyle(cssStyle);
					setTooltip(new Tooltip(toolTip));
				}
			}
		});
	}

	public static void DatePickerFormatSQL(DatePicker datePicker) {
		String pattern = "yyyy-MM-dd";

		datePicker.setPromptText(pattern.toLowerCase());

		datePicker.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		});
	}

	public static void setDefaultFontToAll(Parent parent, Font font) {
		// Apply to this node
		applyFontViaCSS(parent, font);

		// Handle special container types
		if (parent instanceof TitledPane) {
			// Style TitledPane title:
			TitledPane titledPane = (TitledPane) parent;
			titledPane.setFont(font);

			// Style TitledPane contents:
			Node content = ((TitledPane) parent).getContent();
			if (content instanceof Parent) {
				setDefaultFontToAll((Parent) content, font);
			}
			return;
		}

		if (parent instanceof TabPane) {
			// Style tab titles:
			TabPane tabPane = (TabPane) parent;
			styleTabPaneTitles(tabPane, font);

			// Style tab contents:
			for (Tab tab : ((TabPane) parent).getTabs()) {
				Node content = tab.getContent();
				if (content instanceof Parent) {
					setDefaultFontToAll((Parent) content, font);
				}
			}
			return;
		}

		if (parent instanceof SplitPane) {
			for (Node node : ((SplitPane) parent).getItems()) {
				if (node instanceof Parent) {
					setDefaultFontToAll((Parent) node, font);
				}
			}
			return;
		}

		if (parent instanceof ScrollPane) {
			Node content = ((ScrollPane) parent).getContent();
			if (content instanceof Parent) {
				setDefaultFontToAll((Parent) content, font);
			}
			return;
		}

		// Handle Accordion (container of TitledPanes)
		if (parent instanceof Accordion) {
			for (TitledPane pane : ((Accordion) parent).getPanes()) {
				setDefaultFontToAll(pane, font);
			}
			return;
		}

		if (parent instanceof ButtonBar) {
			ButtonBar buttonBar = (ButtonBar) parent;
			for (Node node : buttonBar.getButtons()) {
				applyFontViaCSS(node, font);
				if (node instanceof Parent) {
					setDefaultFontToAll((Parent) node, font);
				}
			}
			return;
		}

		if (parent instanceof ToolBar) {
			ToolBar toolBar = (ToolBar) parent;
			for (Node node : toolBar.getItems()) {
				applyFontViaCSS(node, font);
				if (node instanceof Parent) {
					setDefaultFontToAll((Parent) node, font);
				}
			}
			return;
		}

		if (parent instanceof MenuBar) {
			MenuBar menuBar = (MenuBar) parent;
			// Apply font via CSS to the entire MenuBar
			applyFontViaCSS(menuBar, font);
			//menuBar.setStyle(prepFontCSS(font));
			return;
		}

		if (parent instanceof BorderPane) {
			BorderPane borderPane = (BorderPane) parent;
			applyToBorderPaneRegions(borderPane, font);
			return;
		}

		// Regular container processing
		if (!parent.getChildrenUnmodifiable().isEmpty()) {
			for (Node node : parent.getChildrenUnmodifiable()) {
				applyFontViaCSS(node, font);
				if (node instanceof Parent) {
					setDefaultFontToAll((Parent) node, font);
				}
			}
		}
	}

	private static String prepFontCSS(Font font) {
		return String.format(
				  "-fx-font-family: '%s'; -fx-font-size: %fpx;",
				  font.getFamily(), font.getSize()
		);
	}

	private static void applyFontViaCSS(Node node, Font font) {
		String fontCSS = prepFontCSS(font);

		// Append to existing style
		String existingStyle = node.getStyle();
		node.setStyle((existingStyle != null && !existingStyle.isEmpty() ? " " + existingStyle : "") + fontCSS);
	}

	private static void styleTabPaneTitles(TabPane tabPane, Font font) {
		for (Tab tab : tabPane.getTabs()) {
			// Apply style to each tab:
			tab.setStyle(prepFontCSS(font));

			// Also apply font to tab content if it has any:
			Node content = tab.getContent();
			if (content instanceof Parent) {
				setDefaultFontToAll((Parent) content, font);
			}
		}
	}

	private static void applyToBorderPaneRegions(BorderPane borderPane, Font font) {
		if (borderPane.getTop() instanceof Parent) {
			setDefaultFontToAll((Parent) borderPane.getTop(), font);
		}
		if (borderPane.getBottom() instanceof Parent) {
			setDefaultFontToAll((Parent) borderPane.getBottom(), font);
		}
		if (borderPane.getLeft() instanceof Parent) {
			setDefaultFontToAll((Parent) borderPane.getLeft(), font);
		}
		if (borderPane.getRight() instanceof Parent) {
			setDefaultFontToAll((Parent) borderPane.getRight(), font);
		}
		if (borderPane.getCenter() instanceof Parent) {
			setDefaultFontToAll((Parent) borderPane.getCenter(), font);
		}
	}

	public static Tab createStyledTab(String tabNick, String tabTitle, Font font) {
		// Create new tab:
		Tab newTab = new Tab();

		// Apply style for tab title:
		newTab.setStyle(prepFontCSS(font));

		// Save tab NICK:
		newTab.setUserData(tabNick);

		// Set tab title:
		newTab.setText(tabTitle);

		// Return new styled tab:
		return newTab;
	}

	/**
	 * Creates an HBox containing labels that mirror the visible columns of the
	 * given TableView. The HBox can be placed below the table as a totals row.
	 *
	 * @param tableView the TableView whose columns to mirror
	 * @param syncScroll
	 * @return an HBox with labels, ready to be styled and updated
	 */
	public static ScrollPane createTotalsBar(TableView<?> tableView, boolean syncScroll) {
		HBox totalsBar = new HBox();
		totalsBar.getStyleClass().add("totals-bar");
		totalsBar.setSpacing(0);

		List<TableColumn<?, ?>> visibleCols = tableView.getColumns().stream()
				  .filter(TableColumn::isVisible)
				  .collect(Collectors.toList());

		for (int i = 0; i < visibleCols.size(); i++) {
			TableColumn<?, ?> col = visibleCols.get(i);
			Label label = new Label();
			label.prefWidthProperty().bind(col.widthProperty());
			label.setMinWidth(Region.USE_PREF_SIZE);
			label.setMaxWidth(Region.USE_PREF_SIZE);

			// Joe1962: this is for use in updateTotals:
			label.setUserData(col.getUserData());

			// Copy alignment from column style or default to center-right
			String colStyle = col.getStyle();
			if (colStyle != null && colStyle.contains("-fx-alignment")) {
				label.setStyle(colStyle);
			} else {
				label.setAlignment(Pos.CENTER_RIGHT);
			}

			// Add right border for all but last
			if (i < visibleCols.size() - 1) {
				label.setStyle(label.getStyle()
						  + ";-fx-border-color: transparent #ccc transparent transparent;"
						  + "-fx-border-width: 0 1px 0 0;-fx-border-style: solid;");
			}

			//label.setPadding(new Insets(5, 8, 5, 8));
			label.setPadding(new Insets(0, 3, 0, 3));
			totalsBar.getChildren().add(label);
		}

		ScrollPane scrollPane = new ScrollPane(totalsBar);
		//scrollPane.setFitToHeight(true);
		scrollPane.setFitToHeight(false);
		scrollPane.setFitToWidth(false);
		scrollPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
		scrollPane.setMinHeight(Region.USE_PREF_SIZE);
		scrollPane.setMaxHeight(Region.USE_PREF_SIZE);

		// Hide the horizontal scrollbar – it will still scroll via binding
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		//scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.getStyleClass().add("totals-scroll-pane");

		// Disable the vertical scrollbar (visible but not scrollable)
		Platform.runLater(() -> {
			Node vScrollBar = scrollPane.lookup(".scroll-bar:vertical");
			if (vScrollBar instanceof ScrollBar) {
				((ScrollBar) vScrollBar).setDisable(true);
			}
		});

		// Remove any default background/border
		scrollPane.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");

		scrollPane.prefWidthProperty().bind(tableView.widthProperty());

		if (syncScroll) {
			synchronizeTotalsScroll(tableView, scrollPane);
		}

		return scrollPane;
	}

	private static void synchronizeTotalsScroll(TableView<?> tableView, ScrollPane totalsScrollPane) {
		Platform.runLater(() -> {
			Node hScrollBarNode = tableView.lookup(".scroll-bar:horizontal");
			if (hScrollBarNode instanceof ScrollBar tableHScroll) {
				attachScrollSync(tableView, totalsScrollPane, tableHScroll);
			} else {
				// Store a reference to the listener so we can remove it later
				ChangeListener<Skin<?>> skinListener = new ChangeListener<Skin<?>>() {
					@Override
					public void changed(ObservableValue<? extends Skin<?>> obs, Skin<?> oldSkin, Skin<?> newSkin) {
						Platform.runLater(() -> {
							Node sb = tableView.lookup(".scroll-bar:horizontal");
							if (sb instanceof ScrollBar scrollBar) {
								attachScrollSync(tableView, totalsScrollPane, scrollBar);
								// Now remove this listener using the stored reference
								tableView.skinProperty().removeListener(this);
							}
						});
					}
				};
				tableView.skinProperty().addListener(skinListener);
			}
		});
	}

	private static void attachScrollSync(TableView<?> tableView, ScrollPane totalsScrollPane, ScrollBar tableHScroll) {
		HBox totalsBar = (HBox) totalsScrollPane.getContent();

		// Force layout to get accurate widths
		totalsBar.applyCss();
		totalsBar.layout();

		// Bind totals scroll pane width to table's width (viewport matches)
		totalsScrollPane.prefWidthProperty().bind(tableView.widthProperty());

		// Listener to update totals scroll position when table scrolls
		tableHScroll.valueProperty().addListener((obs, oldVal, newVal) -> {
			double tableMax = tableHScroll.getMax();
			if (tableMax <= 0) {
				return;
			}

			double proportion = newVal.doubleValue() / tableMax; // assuming min=0
			// Clamp proportion to [0,1]
			proportion = Math.max(0, Math.min(1, proportion));

			// Set totals hvalue directly
			totalsScrollPane.setHvalue(proportion);
		});

		// Also trigger when table's scrollable width changes (columns resized)
		tableHScroll.maxProperty().addListener((obs, oldMax, newMax) -> {
			// Force totals bar layout to recompute its content width
			Platform.runLater(() -> {
				totalsBar.applyCss();
				totalsBar.layout();
				// Optionally, reapply current scroll position
				double proportion = tableHScroll.getValue() / tableHScroll.getMax();
				totalsScrollPane.setHvalue(proportion);
			});
		});

		// Listen to column width changes to keep totals bar content width updated
		for (TableColumn<?, ?> col : tableView.getColumns()) {
			col.widthProperty().addListener((obs, oldW, newW) -> {
				Platform.runLater(() -> {
					totalsBar.applyCss();
					totalsBar.layout();
				});
			});
		}
	}

	/**
	 * Updates the text of each label in the totals bar with the provided values.
	 * Values array must correspond to the order of visible columns.
	 *
	 * @param totalsScrollPane
	 * @param columnValues an array of strings to set on each label (must match
	 * column count)
	 */
	public static void updateTotals(ScrollPane totalsScrollPane, String... columnValues) {
		// Get the content of the ScrollPane, which should be the HBox
		Node content = totalsScrollPane.getContent();
		if (!(content instanceof HBox totalsBar)) {
			return; // or throw an exception
		}

		int childIndex = 0;
		for (Node node : totalsBar.getChildren()) {
			if (node instanceof Label && childIndex < columnValues.length) {
				((Label) node).setText(columnValues[childIndex]);
				childIndex++;
			}
		}
	}

	/**
	 * Updates the totals bar labels using a map of field names to total values.
	 * Only labels whose userData matches a key in the map will be updated.
	 *
	 * @param totalsScrollPane the ScrollPane containing the totals bar HBox
	 * @param totalsMap a map where keys are field names (matching label's
	 * userData) and values are the total strings to display
	 */
	public static void updateTotals(ScrollPane totalsScrollPane, Map<String, String> totalsMap) {
		Node content = totalsScrollPane.getContent();
		if (!(content instanceof HBox totalsBar)) {
			return;
		}

		for (Node node : totalsBar.getChildren()) {
			if (node instanceof Label label) {
				Object userData = label.getUserData();
				if (userData instanceof String fieldName) {
					String value = totalsMap.get(fieldName);
					if (value != null) {
						label.setText(value);
					}
				}
			}
		}
	}

}
