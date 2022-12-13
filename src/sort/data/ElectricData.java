package sort.data;

public class ElectricData {

	// Fields //
	protected float Power = 0; 		// (P) power
	protected float Voltage = 0; 	// (V) voltage going through wire
	protected float Amperage = 0; 	// (I) amperage through wire
	protected float Resistance = 1; // (R) resistance (ohms)

	protected float MaxVoltage = (float) 1e3;
	protected float MaxAmperage = (float) 1e3;
	protected float MaxPower = (float) 1e3;
	
	// Constructors //
	public ElectricData() {}
	
	// Class Methods //
	@Override
	public String toString() {
		return "ElectricData [Power=" + Power + ", Voltage=" + Voltage + ", Amperage=" + Amperage + ", Resistance="
				+ Resistance + ", MaxVoltage=" + MaxVoltage + ", MaxAmperage=" + MaxAmperage + ", MaxPower=" + MaxPower
				+ "]";
	}
	
}
