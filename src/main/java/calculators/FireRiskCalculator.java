package calculators;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import entity.Equipment;
import entity.RiskType;

public class FireRiskCalculator extends RiskCalculator {

	private static final RiskType RISK = RiskType.FIRE;
	private static final BigDecimal COEFFICIENT_SUM_DEFAULT = BigDecimal.valueOf(0.014);
	private static final BigDecimal COEFFICIENT_SUM_100 = BigDecimal.valueOf(0.024);
	private static final BigDecimal SUM_RANGE_100 = BigDecimal.valueOf(100.00);

	@Override
	protected Collection<Equipment> filterEquipment(Collection<Equipment> equipment) {
		return equipment.stream()
					   .filter(e -> e.getRiskType().equals(RISK))
					   .collect(Collectors.toList());
	}

	@Override
	protected BigDecimal getCoefficient(BigDecimal sumInsured) {
		if (sumInsured.compareTo(SUM_RANGE_100) > 0) {
			return COEFFICIENT_SUM_100;
		}
		return COEFFICIENT_SUM_DEFAULT;
	}
}
