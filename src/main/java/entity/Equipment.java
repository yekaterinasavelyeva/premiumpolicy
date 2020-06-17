package entity;

import java.math.BigDecimal;

public class Equipment {

	private final String name;
	private final BigDecimal sumInsured;
	private final RiskType riskType;

	public Equipment(String name, BigDecimal sumInsured, RiskType riskType) {
		this.name = name;
		this.sumInsured = sumInsured;
		this.riskType = riskType;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getSumInsured() {
		return sumInsured;
	}

	public RiskType getRiskType() {
		return riskType;
	}
}
