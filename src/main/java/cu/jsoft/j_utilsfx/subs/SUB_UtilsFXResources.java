/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.subs;

import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsFXResources {
	
	public static ImageView getResourceImage(String thePath) {
		return new ImageView(new Image(SUB_UtilsFXResources.class.getClassLoader().getResourceAsStream(thePath)));
	}

	public static Font getResourceFont(String thePath, float theSize) {
		InputStream theFontStream = ClassLoader.getSystemClassLoader().getResourceAsStream(thePath);
		return Font.loadFont(theFontStream, theSize);
	}

}
