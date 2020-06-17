package calculators;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import entity.Asset;
import entity.Equipment;
import entity.RiskType;

class CalculatorTestData {

	 static List<Equipment> getListOfEquipment() {
		Equipment tv = new Equipment("TV", BigDecimal.ZERO, RiskType.FIRE);
		Equipment computer = new Equipment("MAC", BigDecimal.ZERO, RiskType.THEFT);
		Equipment navigator = new Equipment("Navigator", BigDecimal.ZERO, RiskType.FIRE);
		Equipment camera = new Equipment("Camera", BigDecimal.ZERO, RiskType.THEFT);
		return Arrays.asList(tv, computer, navigator, camera);
	}

	 static List<Equipment> getEquipmentSetSimple() {
		Equipment tv = new Equipment("TV", BigDecimal.valueOf(100.01), RiskType.FIRE);
		Equipment computer = new Equipment("MAC", BigDecimal.valueOf(15.01), RiskType.THEFT);
		return Arrays.asList(tv, computer);
	}

	 static List<Equipment> getEquipmentSetComplex() {
		Equipment tv = new Equipment("TV", BigDecimal.valueOf(99.99), RiskType.FIRE);
		Equipment computer = new Equipment("MAC", BigDecimal.valueOf(14.99), RiskType.THEFT);
		Equipment navigator = new Equipment("Navigator", BigDecimal.valueOf(152.21), RiskType.FIRE);
		Equipment camera = new Equipment("Camera", BigDecimal.valueOf(100.51), RiskType.THEFT);
		return Arrays.asList(tv, computer, navigator, camera);
	}

	static Asset getAssetSetOne() {
		Equipment tv = new Equipment("TV", BigDecimal.valueOf(100.00), RiskType.FIRE);
		Equipment computer = new Equipment("MAC", BigDecimal.valueOf(8.00), RiskType.THEFT);
		List<Equipment> equipmentSetHouse = Arrays.asList(tv, computer);
		return new Asset("House", equipmentSetHouse);
	}

	static List<Asset> getAssetSetTwo() {
		Equipment tv = new Equipment("TV", BigDecimal.valueOf(100.00), RiskType.FIRE);
		Equipment computer = new Equipment("MAC", BigDecimal.valueOf(8.00), RiskType.THEFT);
		Equipment navigator = new Equipment("Navigator", BigDecimal.valueOf(400.00), RiskType.FIRE);
		Equipment camera = new Equipment("Camera", BigDecimal.valueOf(94.51), RiskType.THEFT);
		List<Equipment> equipmentSetHouse = Arrays.asList(tv, computer);
		List<Equipment> equipmentSetCar = Arrays.asList(navigator, camera);
		return Arrays.asList(new Asset("House", equipmentSetHouse), new Asset("Car", equipmentSetCar));
	}
}
