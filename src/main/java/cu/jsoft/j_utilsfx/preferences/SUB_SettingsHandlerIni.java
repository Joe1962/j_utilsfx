/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.preferences;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.ini4j.Profile;
import org.ini4j.Wini;
import org.ini4j.spi.EscapeTool;

/**
 *
 * @author joe1962
 */
public class SUB_SettingsHandlerIni {
	private final Path configPath;
	private Wini ini;
	private final EscapeTool escapeTool = new EscapeTool();

	public SUB_SettingsHandlerIni(String appName) throws IOException {
		this(getDefaultConfigPathINI(appName));
	}

	public SUB_SettingsHandlerIni(Path configPath) throws IOException {
		this.configPath = configPath;
		load();
	}

	private static Path getDefaultConfigPathINI(String appName) {
		String os = System.getProperty("os.name").toLowerCase();
		Path configDir;

		if (os.contains("win")) {
			configDir = Paths.get(System.getenv("APPDATA"), appName);
		} else if (os.contains("mac")) {
			configDir = Paths.get(System.getProperty("user.home"), "Library", "Application Support", appName);
		} else {
			configDir = Paths.get(System.getProperty("user.home"), ".config", appName);
		}

		if (!Files.exists(configDir)) {
			try {
				Files.createDirectories(configDir);
			} catch (IOException e) {
				throw new RuntimeException("Could not create config directory", e);
			}
		}

		return configDir.resolve("config.ini");
	}

	public synchronized void load() throws IOException {
		if (Files.exists(configPath)) {
			ini = new Wini(configPath.toFile());
		} else {
			ini = new Wini();
			ini.add("database");
			ini.add("settings");
		}
	}

	public synchronized void save() throws IOException {
		if (!Files.exists(configPath.getParent())) {
			Files.createDirectories(configPath.getParent());
		}
		ini.store(configPath.toFile());
	}

	public String getString(String section, String key, String defaultValue) {
		String value = (String) ini.get(section, key, String.class);
		return value != null ? escapeTool.unescape(value) : defaultValue;
	}

	public int getInt(String section, String key, int defaultValue) {
		Integer value = (Integer) ini.get(section, key, Integer.class);
		return value != null ? value : defaultValue;
	}

	public boolean getBoolean(String section, String key, boolean defaultValue) {
		Boolean value = (Boolean) ini.get(section, key, Boolean.class);
		return value != null ? value : defaultValue;
	}

	public void setString(String section, String key, String value) {
		ini.put(section, key, escapeTool.escape(value));
	}

	public void setInt(String section, String key, int value) {
		ini.put(section, key, value);
	}

	public void setBoolean(String section, String key, boolean value) {
		ini.put(section, key, value);
	}

	public Set<String> getSections() {
		return ini.keySet();
	}

	/**
	 * Returns an unmodifiable view of the specified section
	 *
	 * @param section the section name to retrieve
	 * @return unmodifiable map of key-value pairs, or empty map if section
	 * doesn't exist
	 */
	public Map<String, String> getSection(String section) {
		Profile.Section sectionData = (Profile.Section) ini.get(section);
		return sectionData == null
			? Collections.emptyMap()
			: Collections.unmodifiableMap(sectionData);
	}

	public Path getConfigPath() {
		return configPath;
	}

	public void removeKey(String section, String key) {
		ini.remove(section, key);
	}

	public void removeSection(String section) {
		ini.remove(section);
	}
}
