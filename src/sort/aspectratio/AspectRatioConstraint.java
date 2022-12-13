package sort.aspectratio;

import main.display.objects.GuiBase;
import main.enumerations.DominantAxis;
import main.math.Vector2int;

public class AspectRatioConstraint {

	protected GuiBase parent;

	protected float aspectRatio;
	protected AspectRatioType aspectType;
	protected DominantAxis dominantAxis;

	public AspectRatioConstraint() {
		this.setDefault();
	}

	private void setDefault() {
		this.parent = null;
		this.aspectRatio = 1;
		this.aspectType = AspectRatioType.FitWithinMaxSize;
		this.dominantAxis = DominantAxis.Width;
	}

	public Vector2int calculateDimensions() {
		if (this.parent == null) {
			return null;
		}

		Vector2int prntAbsSize = this.parent.getAbsoluteSize();

		// Calculate the scaled vector
		Vector2int scaledVector = null;
		if (this.dominantAxis == DominantAxis.Width) {
			scaledVector = new Vector2int( prntAbsSize.x, prntAbsSize.x * this.aspectRatio );
		} else {
			scaledVector = new Vector2int( prntAbsSize.y * this.aspectRatio, prntAbsSize.y );
		}

		if (this.aspectType == AspectRatioType.FitWithinMaxSize) {
			float boundMult = Math.max( prntAbsSize.x/scaledVector.x, prntAbsSize.y/scaledVector.y );
			scaledVector.mult(boundMult);
		}
		return scaledVector;
	}

}
