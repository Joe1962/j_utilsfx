/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu.jsoft.j_utilsfxlite.preferences;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cu.jsoft.j_utilsfxlite.interfaces.SettingsHandlerJSON;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joe1962
 */
public class SUB_SettingsHandlerJSON implements SettingsHandlerJSON {

	private final ObjectMapper mapper;
	private Map<String, Object> configData;

	public SUB_SettingsHandlerJSON() {
		this.mapper = new ObjectMapper()
			.enable(SerializationFeature.INDENT_OUTPUT);
		this.configData = new LinkedHashMap<>();
	}

	@Override
	public void load(Path configPath) throws IOException {
		configData = mapper.readValue(configPath.toFile(), Map.class);
	}

	@Override
	public void save(Path configPath) throws IOException {
		mapper.writeValue(configPath.toFile(), configData);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getValue(String section, String key, Class<T> type, T defaultValue) {
		try {
			Map<String, Object> sectionMap = (Map<String, Object>) configData.get(section);
			if (sectionMap == null) {
				return defaultValue;
			}
			Object value = sectionMap.get(key);
			return value != null ? mapper.convertValue(value, type) : defaultValue;
		} catch (ClassCastException e) {
			return defaultValue;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setValue(String section, String key, Object value) {
		Map<String, Object> sectionMap = (Map<String, Object>) configData.computeIfAbsent(
			section, k -> new LinkedHashMap<>());
		sectionMap.put(key, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getArray(String section, Class<T> elementType) {
		try {
			List<Object> items = (List<Object>) configData.get(section);
			if (items == null) {
				return new ArrayList<>();
			}

			List<T> result = new ArrayList<>();
			for (Object item : items) {
				result.add(mapper.convertValue(item, elementType));
			}
			return result;
		} catch (ClassCastException e) {
			return new ArrayList<>();
		}
	}

	@Override
	public void setArray(String section, List<?> items) {
		configData.put(section, new ArrayList<>(items));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSection(String section) {
		Map<String, Object> sectionMap = (Map<String, Object>) configData.get(section);
		return sectionMap != null ? new LinkedHashMap<>(sectionMap) : new LinkedHashMap<>();
	}

	@Override
	public Path getDefaultConfigPathJSON(String appName) {
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

		return configDir.resolve("config.json");
	}

}
