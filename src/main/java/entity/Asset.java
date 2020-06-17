package entity;

import java.util.Collection;
import java.util.Collections;

public class Asset {
	private final String name;
	private Collection<Equipment> equipmentList;

	public Asset(String name) {
		this.name = name;
		equipmentList = Collections.emptyList();
	}

	public Asset(String name, Collection<Equipment> equipmentList) {
		this.name = name;
		this.equipmentList = equipmentList == null? Collections.emptyList() : equipmentList;
	}

	public void addEquipment(Equipment equipment) {
		equipmentList.add(equipment);
	}

	public String getName() {
		return name;
	}

	public Collection<Equipment> getEquipmentList() {
		return equipmentList;
	}
}
