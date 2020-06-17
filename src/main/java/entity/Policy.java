package entity;

import java.util.Collection;
import java.util.Collections;

public class Policy {

	private final String policyNumber;
	private PolicyStatus policyStatus;
	private Collection<Asset> policyAssets;


	public Policy(String policyNumber, PolicyStatus policyStatus) {
		this.policyNumber = policyNumber;
		this.policyStatus = policyStatus;
		policyAssets = Collections.emptyList();
	}

	public Policy(String policyNumber, PolicyStatus policyStatus, Collection<Asset> policyAssets) {
		this.policyNumber = policyNumber;
		this.policyStatus = policyStatus;
		this.policyAssets = policyAssets;
	}

	public void addAssets(Collection<Asset> assets) {
		policyAssets.addAll(assets);
	}

	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}

	public Collection<Asset> getPolicyAssets() {
		return policyAssets;
	}
}
