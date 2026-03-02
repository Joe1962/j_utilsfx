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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	 * Convenience method to schedules a focus request for a text input control with default attempts (50 frames ~= 0.8 seconds at
	 * 60fps)
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
	 * Convenience method to schedules a focus request with selection for a text input control with default attempts (50 frames ~= 0.8 seconds at
	 * 60fps)
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
		node.setStyle(fontCSS + (existingStyle != null && !existingStyle.isEmpty() ? " " + existingStyle : ""));
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

}
