package calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import entity.Equipment;
import entity.Policy;

public class PremiumCalculator {

	private List<RiskCalculator> riskCalculators;

	public PremiumCalculator(List<RiskCalculator> riskCalculators) {
		this.riskCalculators = riskCalculators;
	}

	public BigDecimal calculate(Policy policy) {
		List<Equipment> policyEquipmentList = policy.getPolicyAssets()
												  .stream()
												  .flatMap(asset -> asset.getEquipmentList().stream())
												  .distinct()
												  .collect(Collectors.toList());
		return riskCalculators.stream()
				.map(riskCalculator -> riskCalculator.calculate(policyEquipmentList))
				.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
	}
}
