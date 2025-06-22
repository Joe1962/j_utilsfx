/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.jsoft.j_utilsfxlite.subs;

import static cu.jsoft.j_utilsfxlite.global.CONSTS.EMPTY_STRING;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author joe1962
 */
public class SUB_PopupsFX {

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @param prompt the value of prompt
	 * @return
	 */
	public static String MsgInputStringFX(Window parent, String title, String header, String content, String prompt) {
		TextInputDialog dialog = new TextInputDialog(prompt);
		dialog.setTitle(title != null ? title : "INTRODUZCA...");
		dialog.setHeaderText(header);
		dialog.setContentText(content != null ? content : EMPTY_STRING);
		dialog.initOwner(parent);
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	/**
	 *
	 * @param title the value of title
	 * @param MyMsg the value of MyMsg
	 * @param MyDuration the value of MyDuration
	 * @param MyPos the value of MyPos
	 * @param isDarkStyle the value of isDarkStyle
	 * @param MyImage the value of MyImage
	 */
	public static void NotificationWarningOKFX(String title, String MyMsg, int MyDuration, Pos MyPos, boolean isDarkStyle, String MyImage) {
		NotificationFXHelper(MyMsg, title != null ? title : "ALERTA...", MyDuration, MyPos, isDarkStyle, MyImage).showWarning();
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @return the boolean
	 */
	public static boolean MsgWarningYesNoFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title != null ? title : "ALERTA...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param title the value of title
	 * @param MyMsg the value of MyMsg
	 * @param MyDuration the value of MyDuration
	 * @param MyPos the value of MyPos
	 * @param isDarkStyle the value of isDarkStyle
	 * @param MyImage the value of MyImage
	 */
	public static void NotificationErrorOKFX(String title, String MyMsg, int MyDuration, Pos MyPos, boolean isDarkStyle, String MyImage) {
		NotificationFXHelper(MyMsg, title != null ? title : "ERROR...", MyDuration, MyPos, isDarkStyle, MyImage).showWarning();
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @return the boolean
	 */
	public static boolean MsgQuestionYesNoFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title != null ? title : "AVISO...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @return the boolean
	 */
	public static boolean MsgErrorYesNoFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title != null ? title : "ERROR...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == ButtonType.OK;
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 */
	public static void MsgErrorOKFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title != null ? title : "ERROR...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		alert.showAndWait();
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @return the boolean
	 */
	public static boolean MsgErrorOKCancelFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title != null ? title : "ERROR...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param title the value of title
	 * @param MyMsg the value of MyMsg
	 * @param MyDuration the value of MyDuration
	 * @param MyPos the value of MyPos
	 * @param isDarkStyle the value of isDarkStyle
	 * @param MyImage the value of MyImage
	 */
	private static Notifications NotificationFXHelper(String title, String MyMsg, int MyDuration, Pos MyPos, boolean isDarkStyle, String MyImage) {
		Notifications notificationBuilder = Notifications.create().title(title).text(MyMsg).hideAfter(Duration.seconds(MyDuration)).position(MyPos);
		if (isDarkStyle) {
			notificationBuilder.darkStyle();
		}
		if (MyImage != null) {
			notificationBuilder.graphic(new ImageView(new Image(MyImage)));
		}
		return notificationBuilder;
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 */
	public static void MsgWarningOKFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title != null ? title : "ALERTA...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		alert.showAndWait();
	}

	/**
	 *
	 * @param title the value of title
	 * @param MyMsg the value of MyMsg
	 * @param MyDuration the value of MyDuration
	 * @param MyPos the value of MyPos
	 * @param isDarkStyle the value of isDarkStyle
	 * @param MyImage the value of MyImage
	 */
	public static void NotificationInfoOKFX(String title, String MyMsg, int MyDuration, Pos MyPos, boolean isDarkStyle, String MyImage) {
		NotificationFXHelper(MyMsg, title != null ? title : "AVISO...", MyDuration, MyPos, isDarkStyle, MyImage).showInformation();
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @return the boolean
	 */
	public static boolean MsgQuestionOKCancelFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title != null ? title : "AVISO...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 * @return the boolean
	 */
	public static boolean MsgWarningOKCancelFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title != null ? title : "ALERTA...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param parent the value of parent
	 * @param title the value of title
	 * @param header the value of header
	 * @param content the value of content
	 */
	public static void MsgInfoOKFX(Window parent, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title != null ? title : "AVISO...");
		alert.setHeaderText(header);
		alert.setContentText(content != null ? content : EMPTY_STRING);
		alert.initOwner(parent);
		alert.showAndWait();
	}

	public static SUB_PopupsFX getInstance() {
		return SUB_PopupsFXHolder.INSTANCE;
	}

	private SUB_PopupsFX() {
	}

	private static class SUB_PopupsFXHolder {

		private static final SUB_PopupsFX INSTANCE = new SUB_PopupsFX();
	}
}
