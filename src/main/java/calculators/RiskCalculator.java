package calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import entity.Equipment;

public abstract class RiskCalculator {

	private static final int DEFAULT_SCALE = 2;

	BigDecimal calculate(Collection<Equipment> equipment) {
		Collection<Equipment> equipmentFiltered = filterEquipment(equipment);
		BigDecimal sumInsured = equipmentFiltered.stream()
							 .map(Equipment::getSumInsured)
							 .reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal coefficient = getCoefficient(sumInsured);
		return sumInsured.multiply(coefficient).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP);
	}

	protected abstract BigDecimal getCoefficient(BigDecimal sumInsured);

	protected abstract Collection<Equipment> filterEquipment(Collection<Equipment> equipment);

}
