/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.utils.fx;

import static cu.jsoft.j_utilsfx.utils.SUB_UtilsFileIO.FileExists;
import cu.jsoft.j_utilsfx.utils.fx.UtilsFX_OS.OSTYPE;
import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author joe1962
 */
public class UtilsFX_FileIO {

	public File getDirectory(String title, File defaultDir, Window parent) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File fallbackDir = FileSystemView.getFileSystemView().getDefaultDirectory();

		UtilsFX_OS FX_OS = new UtilsFX_OS();

		if (FX_OS.getFXOS() == OSTYPE.LINUX) {
			String documentsDir = fallbackDir.getPath() + "/Documents";
			if (FileExists(fallbackDir.getPath() + "/Documents")) {
				fallbackDir = new File(documentsDir);
			}
		}

		directoryChooser.setInitialDirectory(defaultDir != null ? defaultDir : fallbackDir);
		directoryChooser.setTitle(title);
		File selectedDirectory = directoryChooser.showDialog(parent);
		return selectedDirectory;
	}

}
