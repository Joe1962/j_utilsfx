/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.subs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author joe1962
 */
public class SUB_UtilsFXResources {
	
	public static ImageView getResourceImage(String thePath) {
		return new ImageView(new Image(SUB_UtilsFXResources.class.getClassLoader().getResourceAsStream(thePath)));
	}

}
