/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.security;

import java.util.ArrayList;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

/**
 *
 * @author joe1962
 */
public class CLS_oshi {
	private SystemInfo si = new SystemInfo();;

	public String getMotherBoardSerial() {
		//this.si = new SystemInfo();
		HardwareAbstractionLayer hal = this.si.getHardware();
		return hal.getComputerSystem().getSerialNumber();
	}

	public String getCPUID() {
		//this.si = new SystemInfo();
		HardwareAbstractionLayer hal = this.si.getHardware();
		return hal.getProcessor().getProcessorIdentifier().toString();
	}

	public ArrayList<String> getDisksInfo() {
		//this.si = new SystemInfo();
		HardwareAbstractionLayer hal = this.si.getHardware();
		ArrayList<String> diskSerials = new ArrayList();
		List<HWDiskStore> diskStores = hal.getDiskStores();
		for (HWDiskStore disk : diskStores) {
			diskSerials.add(disk.getSerial());
		}
		return diskSerials;
	}

}
