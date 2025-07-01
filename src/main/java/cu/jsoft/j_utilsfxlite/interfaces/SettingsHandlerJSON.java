/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.interfaces;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joe1962
 */
public interface SettingsHandlerJSON {

	// Load configuration from file
	void load(Path configPath) throws IOException;

	// Save configuration to file
	void save(Path configPath) throws IOException;

	// Get simple value
	<T> T getValue(String section, String key, Class<T> type, T defaultValue);

	// Set simple value
	void setValue(String section, String key, Object value);

	// Get array/list of items (for DB configurations)
	<T> List<T> getArray(String section, Class<T> elementType);

	// Set array/list of items
	void setArray(String section, List<?> items);

	// Get entire section as map
	Map<String, Object> getSection(String section);

	// Get default config path for current user:
	Path getDefaultConfigPathJSON(String appName);

}
