package calculators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import entity.Equipment;
import entity.RiskType;

class TheftRiskCalculatorTest {

	private static final int DEFAULT_SCALE = 2;
	private RiskCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new TheftRiskCalculator();
	}

	@Test
	void testEquipmentFiltering() {
		Collection<Equipment> filteredEquipment = calculator.filterEquipment(CalculatorTestData.getListOfEquipment());
		filteredEquipment.forEach(e -> assertEquals(RiskType.THEFT, e.getRiskType()));
	}

	@ParameterizedTest
	@MethodSource("getCoefficientsOfSum")
	void testGetCoefficient(BigDecimal sumInsured, BigDecimal expectedCoefficient) {
		BigDecimal coefficient = calculator.getCoefficient(sumInsured);
		assertEquals(expectedCoefficient, coefficient);
	}

	@Test
	void testRiskCalculator() {
		BigDecimal theftPremium1 = calculator.calculate(CalculatorTestData.getEquipmentSetSimple());
		BigDecimal theftPremium2 = calculator.calculate(CalculatorTestData.getEquipmentSetComplex());

		assertEquals(BigDecimal.valueOf(0.75).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP), theftPremium1);
		assertEquals(BigDecimal.valueOf(5.78).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP), theftPremium2);
	}

	@Test
	void testEmptyEquipmentData() {
		BigDecimal premium = calculator.calculate(Collections.emptyList());
		assertEquals(BigDecimal.valueOf(0.00).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP), premium);
	}

	private static Stream<Arguments> getCoefficientsOfSum() {
		return Stream.of(
				Arguments.of(BigDecimal.valueOf(14.99), BigDecimal.valueOf(0.11)),
				Arguments.of(BigDecimal.ZERO, BigDecimal.valueOf(0.11)),
				Arguments.of(BigDecimal.valueOf(15.01), BigDecimal.valueOf(0.05)),
				Arguments.of(BigDecimal.valueOf(15.00), BigDecimal.valueOf(0.05))
		);
	}

}