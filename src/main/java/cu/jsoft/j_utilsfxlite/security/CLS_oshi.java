/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.jsoft.j_utilsfxlite.security;

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
