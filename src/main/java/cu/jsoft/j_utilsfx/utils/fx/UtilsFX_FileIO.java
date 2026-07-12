/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.utils.fx;

import static cu.jsoft.j_utilsfx.utils.SUB_UtilsFileIO.FileExists;
import cu.jsoft.j_utilsfx.utils.SUB_UtilsOS.OSTYPE;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import javax.swing.filechooser.FileSystemView;
import static cu.jsoft.j_utilsfx.utils.SUB_UtilsOS.getOSType;

/**
 *
 * @author joe1962
 */
public class UtilsFX_FileIO {

	public File getDirectory(String title, File defaultDir, Window parent) {
		// If defaultDir is null, use OS default:
		File fallbackDir = FileSystemView.getFileSystemView().getDefaultDirectory();

		// In Linux, force default directory to ~/Documents instead of user home:
		if (getOSType() == OSTYPE.LINUX) {
			String documentsDir = fallbackDir.getPath() + "/Documents";
			if (FileExists(fallbackDir.getPath() + "/Documents")) {
				fallbackDir = new File(documentsDir);
			}
		}

		// Instantiate a DirectoryChooser and set parameters:
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(defaultDir != null ? defaultDir : fallbackDir);
		directoryChooser.setTitle(title);

		// Show DirectoryChooser and return selection:
		File selectedDirectory = directoryChooser.showDialog(parent);
		return selectedDirectory;
	}

}
