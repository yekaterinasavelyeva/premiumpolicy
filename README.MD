## Implementation Description

1. For implementing RiskCalculator classes Template Method design pattern was chosen. 
    One abstract RiskCalculator and two subclasses for Theft and Fire risks, respectively TheftRiskCalculator and FireRiskCalculator. 
    Many more can be added and steps can be overridden.
    Each of the RiskCalculator child classes has method filterEquipment() to filter Equipment by risk. 
    Filtering can change and be adjusted as needed per each risk class. 
    For now implementation of filterEquipment(..) for both classes is the same, but nevertheless it was not extracted to parent class, 
    as parent class does not need to know about risk classes and their type.
    Another method getCoefficient(..) gets right coefficient per implementation and sumInsured.
    Abstract class RiskCalculator has common logic method calculate(Collection<Equipment> equipment) which can be overridden in subclasses.
    This method provides basic calculation: retrieves equipment, gets sumInsured and coefficient and performs mathematical operation, 
    simple multiplication for now.
    Also this solution could be moved to Strategy design pattern, if there will be a need to switch behaviour at runtime.
2. For Entity classes there are:
     - Policy, main insurance policy class;
     - Asset, a property insured, there can be more assets to one Policy;
     - Equipment, an insured part of the property insured.
     - RiskType to state risk option and PolicyStatus to distinct on policy state.
     For Policy empty collection of assets can be passed, as well empty collection of equipment can be added to asset, 
     empty collection of calculators to Policy. Written solution will handle these cases as well. 
     But external code have to take care not to pass null values to any entity constructor, as values are not checked against null. 
3. For collections Collection interface was chosen, so the implementation can be adjusted as needed.
4. To calculate premium policy there should be method calculate(Property property) called from PremiumPolicy class object, 
    which gets initialized with needed calculators to acknowledge the risks and provide right calculation.
5. Test implemented.