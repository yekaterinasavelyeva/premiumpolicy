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

class FireRiskCalculatorTest {

	private static final int DEFAULT_SCALE = 2;
	private RiskCalculator calculator;

	@BeforeEach
	void setUp() {
		calculator = new FireRiskCalculator();
	}

	@Test
	void testEquipmentFiltering() {
		Collection<Equipment> filteredEquipment = calculator.filterEquipment(CalculatorTestData.getListOfEquipment());
		filteredEquipment.forEach(e -> assertEquals(RiskType.FIRE, e.getRiskType()));
	}

	@ParameterizedTest
	@MethodSource("getCoefficientsOfSum")
	void testGetCoefficient(BigDecimal sumInsured, BigDecimal expectedCoefficient) {
		BigDecimal coefficient = calculator.getCoefficient(sumInsured);
		assertEquals(expectedCoefficient, coefficient);
	}

	@Test
	void testRiskCalculator() {
		BigDecimal firePremium1 = calculator.calculate(CalculatorTestData.getEquipmentSetSimple());
		BigDecimal firePremium2 = calculator.calculate(CalculatorTestData.getEquipmentSetComplex());

		assertEquals(BigDecimal.valueOf(2.40).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP), firePremium1);
		assertEquals(BigDecimal.valueOf(6.05).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP), firePremium2);
	}

	@Test
	void testEmptyEquipmentData() {
		BigDecimal premium = calculator.calculate(Collections.emptyList());
		assertEquals(BigDecimal.valueOf(0.00).setScale(DEFAULT_SCALE, RoundingMode.HALF_UP), premium);
	}

	private static Stream<Arguments> getCoefficientsOfSum() {
		return Stream.of(
				Arguments.of(BigDecimal.valueOf(99.99), BigDecimal.valueOf(0.014)),
				Arguments.of(BigDecimal.ZERO, BigDecimal.valueOf(0.014)),
				Arguments.of(BigDecimal.valueOf(100.01), BigDecimal.valueOf(0.024)),
				Arguments.of(BigDecimal.valueOf(100.00), BigDecimal.valueOf(0.014))
		);
	}

}