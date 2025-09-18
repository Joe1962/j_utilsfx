/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsFX {

	public static String getTableColumnTextAlignment(String field_datatype) {
		switch (field_datatype) {
			case "boolean":
			case "Boolean":
				return "-fx-alignment: CENTER;";
			case "int":
			case "Integer":
			case "long":
			case "Long":
				return "-fx-alignment: CENTER-RIGHT;";
			case "double":
			case "Double":
			case "BigDecimal":
				return "-fx-alignment: CENTER-RIGHT;";
			case "String":
				return "-fx-alignment: CENTER-LEFT;";
			case "Date":
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
	 */
	public static void scheduleFocusRequestAndSelectAll(TextInputControl control) {
		scheduleFocusRequestAndSelectAll(control, 50);
	}

}
