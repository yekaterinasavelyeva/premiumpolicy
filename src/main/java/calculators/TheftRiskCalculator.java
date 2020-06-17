package calculators;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import entity.Equipment;
import entity.RiskType;

public class TheftRiskCalculator extends RiskCalculator {

	private static final RiskType RISK = RiskType.THEFT;
	private static final BigDecimal COEFFICIENT_SUM_DEFAULT = BigDecimal.valueOf(0.11);
	private static final BigDecimal COEFFICIENT_SUM_15 = BigDecimal.valueOf(0.05);
	private static final BigDecimal SUM_RANGE_15 = BigDecimal.valueOf(15.00);

	@Override
	protected Collection<Equipment> filterEquipment(Collection<Equipment> equipment) {
		return equipment.stream()
					   .filter(e -> e.getRiskType().equals(RISK))
					   .collect(Collectors.toList());
	}

	@Override
	protected BigDecimal getCoefficient(BigDecimal sumInsured) {
		if (sumInsured.compareTo(SUM_RANGE_15) >= 0) {
			return COEFFICIENT_SUM_15;
		}
		return COEFFICIENT_SUM_DEFAULT;
	}
}
