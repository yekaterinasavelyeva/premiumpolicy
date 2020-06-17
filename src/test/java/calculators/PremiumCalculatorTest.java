package calculators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import entity.Asset;
import entity.Policy;
import entity.PolicyStatus;

class PremiumCalculatorTest {

	private static final int DEFAULT_SCALE = 2;
	private PremiumCalculator premiumCalculator;

	@BeforeEach
	void setUp() {
		List<RiskCalculator> calculators = Arrays.asList(new FireRiskCalculator(), new TheftRiskCalculator());
		premiumCalculator = new PremiumCalculator(calculators);
	}

	@ParameterizedTest
	@MethodSource("getDifferentCombinations")
	void testCalculate_Success(Policy policy, BigDecimal expectedPremium) {

		BigDecimal premium = premiumCalculator.calculate(policy);
		assertEquals(expectedPremium, premium);
	}

	private static Stream<Arguments> getDifferentCombinations() {

		Collection<Asset> emptyEquipmentAssets = Arrays.asList(new Asset("House", Collections.emptyList()), new Asset("Garden", Collections.emptyList()));

		return Stream.of(
				Arguments.of(new Policy("LV20-02-100000-1", PolicyStatus.REGISTERED, Collections.singletonList(CalculatorTestData.getAssetSetOne())),
						BigDecimal.valueOf(2.28)),
				Arguments.of(new Policy("LV20-02-100000-2", PolicyStatus.REGISTERED, CalculatorTestData.getAssetSetTwo()),
						BigDecimal.valueOf(17.13)),
				Arguments.of(new Policy("LV20-02-100000-2", PolicyStatus.REGISTERED, Collections.emptyList()),
						BigDecimal.valueOf(0.00).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP)),
				Arguments.of(new Policy("LV20-02-100000-2", PolicyStatus.REGISTERED, emptyEquipmentAssets),
						BigDecimal.valueOf(0.00).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP)),
				Arguments.of(new Policy("LV20-02-100000-2", PolicyStatus.APPROVED),
						BigDecimal.valueOf(0.00).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP))
		);
	}

}