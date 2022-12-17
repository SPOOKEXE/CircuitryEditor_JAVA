package main.widgets.objects;

import main.enumerations.AspectRatioType;
import main.enumerations.DominantAxis;
import main.math.Vector2int;

public class AspectRatioConstraint extends Instance {

	// Fields //
	public float aspectRatio;
	public AspectRatioType aspectType;
	public DominantAxis dominantAxis;

	// Constructors //
	public AspectRatioConstraint() {
		this.setDefault();
	}

	// Class Methods //
	private void setDefault() {
		this.aspectRatio = 1;
		this.aspectType = AspectRatioType.ScaleWithParent;
		this.dominantAxis = DominantAxis.Width;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public AspectRatioType getAspectType() {
		return aspectType;
	}

	public void setAspectType(AspectRatioType aspectType) {
		this.aspectType = aspectType;
	}

	public DominantAxis getDominantAxis() {
		return dominantAxis;
	}

	public void setDominantAxis(DominantAxis dominantAxis) {
		this.dominantAxis = dominantAxis;
	}

	public Vector2int calculateDimensions() {
		if (this.parent == null || !(this.parent instanceof GuiObject)) {
			return new Vector2int();
		}

		Vector2int prntAbsSize = ((GuiObject) this.parent).absoluteSize;

		// TODO: implement AspectRatioType.FitWithinMaxSize
		
//		if (this.aspectType == AspectRatioType.FitWithinMaxSize) {
//			float boundMult = Math.max( prntAbsSize.x/scaledVector.x, prntAbsSize.y/scaledVector.y );
//			scaledVector.mult(boundMult);
//		} else {
		
//		}

		// SCALE WITH MAX SIZE
		if (this.dominantAxis == DominantAxis.Width) {
			return new Vector2int( prntAbsSize.x, prntAbsSize.x / this.aspectRatio );
		}
		return new Vector2int( prntAbsSize.y / this.aspectRatio, prntAbsSize.y );
	}

	@Override
	public String toString() {
		String superToStr = super.toString();
		superToStr = superToStr.substring(0, superToStr.length()-1);
		return
			superToStr + ", aspectRatio=" + aspectRatio + ", aspectType=" +
			aspectType + ", dominantAxis=" + dominantAxis + "]";
	}
	
}
