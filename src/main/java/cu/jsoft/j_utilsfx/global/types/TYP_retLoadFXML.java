/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.global.types;

import java.util.Objects;
import javafx.scene.Parent;

/**
 *
 * @author joe1962
 */
public class TYP_retLoadFXML {
	private Parent node;
	private Object controller;

	public TYP_retLoadFXML() {
	}

	public TYP_retLoadFXML(Parent node, Object controller) {
		this.node = node;
		this.controller = controller;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public Parent getNode() {
		return node;
	}

	public void setNode(Parent node) {
		this.node = node;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 73 * hash + Objects.hashCode(this.node);
		hash = 73 * hash + Objects.hashCode(this.controller);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TYP_retLoadFXML other = (TYP_retLoadFXML) obj;
		if (!Objects.equals(this.node, other.node)) {
			return false;
		}
		return Objects.equals(this.controller, other.controller);
	}

}
