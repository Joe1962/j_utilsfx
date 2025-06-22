/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.jsoft.j_utilsfxlite.subs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsFX {

	public static void setupTableView(TableView tblMaster, String dbtable, boolean debugMode) {
//		// First we generate the columns from sys_field_titles:
//		RS_SysFieldTitles RSSysFieldTitles = new RS_SysFieldTitles();
//		RSSysFieldTitles.setMyTableName(dbtable);
//		RSSysFieldTitles.selectByPK(debugMode);
//		boolean retBool = true;
//		tblMaster.getColumns().clear();
//		while (retBool) {
//			TYP_SysFieldTitle MySysFieldTitle = (TYP_SysFieldTitle) RSSysFieldTitles.getCurrent();
//			String fieldTitle = MySysFieldTitle.getField_title();
//			if (fieldTitle != null && !fieldTitle.equals(CONSTS.EMPTY_STRING)) {
//				TableColumn col = new TableColumn(MySysFieldTitle.getField_title());
//				col.setPrefWidth(MySysFieldTitle.getField_prefwidth());
//				col.setCellValueFactory(new PropertyValueFactory(MySysFieldTitle.getField_name()));
//				col.setStyle(getTableColumnTextAlignment(MySysFieldTitle.getField_datatype()));
//				col.setEditable(false);
//				tblMaster.getColumns().add(col);
//			}
//			retBool = RSSysFieldTitles.goNext();
//		}
//		tblMaster.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

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

	public static void cmbOnScrollHandler(ScrollEvent event, ComboBox cmbBox) {
		int SelPos = cmbBox.getSelectionModel().getSelectedIndex();
		int LastPos = cmbBox.getItems().size() - 1;
		double Delta = event.getDeltaY() * -1;
		if (Delta > 0) {
			if (SelPos < LastPos) {
				cmbBox.getSelectionModel().selectNext();
			}
		} else {
			if (SelPos > 0) {
				cmbBox.getSelectionModel().selectPrevious();
			}
		}
	}

	public static void chbOnScrollHandler(ScrollEvent event, ChoiceBox chbBox) {
		int SelPos = chbBox.getSelectionModel().getSelectedIndex();
		int LastPos = chbBox.getItems().size() - 1;
		double Delta = event.getDeltaY() * -1;
		if (Delta > 0) {
			if (SelPos < LastPos) {
				chbBox.getSelectionModel().selectNext();
			}
		} else {
			if (SelPos > 0) {
				chbBox.getSelectionModel().selectPrevious();
			}
		}
	}

	

	public static void toFrontHelper(StackPane stp, String PaneID) {
		for (int i = 0; i < stp.getChildrenUnmodifiable().size(); i++) {
			if (stp.getChildrenUnmodifiable().get(i).getId().equals(PaneID)) {
				stp.getChildren().get(i).toFront();
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
		//fadeOut.playFromStart();
		fadeOut.play();
	}

	public static void doFadeOut(Node fromNode, Duration fadeOutDelay, Duration fadeOutDuration, double Value) {
		FadeTransition fadeOut = new FadeTransition(fadeOutDuration, fromNode);
		fadeOut.setToValue(Value);			// Full fade out = 0...
		fadeOut.setDelay(fadeOutDelay);
		//fadeOut.playFromStart();
		fadeOut.play();
	}

	public static void doFadeIn(Node toNode, Duration fadeInDelay, Duration fadeInDuration, double Value) {
		FadeTransition fadeIn = new FadeTransition(fadeInDuration, toNode);
		fadeIn.setToValue(Value);			// Full fade in = 1...
		fadeIn.setDelay(fadeInDelay);
		//fadeIn.playFromStart();
		fadeIn.play();
	}

}
