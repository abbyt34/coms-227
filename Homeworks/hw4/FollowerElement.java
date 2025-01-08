package hw4;
import api.AbstractElement;
/**
* A follower element is one that is associated with another "base" element such
* as a PlatformElement or LiftElement. Specifically, the follower element's
* movement is determined by the movement of the base element, when the base
* move up 10 pixels, the follower moves up 10 pixels. However, the follower may
* not always be at a fixed location relative to the base. When the horizontal
* velocity of the follower is set to a non-zero value, the follower will
* oscillate between the left and right edges of the PlatformElement or
* LiftElement it is associated with.
*
* @author Abby Taylor
* @version 1
*/
public class FollowerElement extends PlatformElement{
	/*
	 * Instance variable for the initial offset of the function
	 */
	private int initialOffset;
	/*
	 * Instance variable for the base of the function
	 */
	private AbstractElement base;
	/**
	 * Constructs a new FollowerElement. Before being added to a "base" element such
	 * as a PlatformElement or LiftElement, the x and y coordinates are zero. When a
	 * base element is set, the initial x-coordinate becomes the base's
	 * x-coordinate, plus the given offset, and the y-coordinate becomes the base's
	 * y-coordinate, minus this element's height.
	 *
	 * @param width         element's width
	 * @param height        element's height
	 * @param initialOffset when added to a base, this amount will be added to the
	 *                      bases's x-coordinate to calculate this element's initial
	 *                      x-coordinate
	 */
	public FollowerElement(int width, int height, int initialOffset) {
		super(0, 0, width, height);
		this.initialOffset = initialOffset;
	}
	
	/*
	 * Updates this element's positions to move horixontally according to its velocity (possibly zero)
	 * relative to the parent object, and remain "resting" on the parent object
	 * (provided that a parent has been set)
	 */
	@Override
	public void update() {
		super.update();
		if (base!= null) {
			setBounds(base.getXReal(), base.getXReal() + base.getWidth());
			setPosition(getXReal() + ((MovingElement) base).getDeltaX(), base.getYReal() - getHeight());
			if (getXReal() + getWidth() >= super.getMax()) {
				setPosition(super.getMax() - getWidth(), base.getYReal() - getHeight());
				setVelocity(getDeltaX() * -1, getDeltaY());
			}
			else if (getXReal() <= super.getMin()) {
				setPosition(super.getMin() + initialOffset, base.getYReal() - getHeight());
				setVelocity(getDeltaX() * -1, getDeltaY());
			}
		}
	}
	/*
	 * Set's this element's base element
	 * @param AbstractElement b
	 */
	public void setBase(AbstractElement b) {
			this.base = b;
			setPosition(base.getXReal() + initialOffset, base.getYReal() - super.getHeight());	
			setBounds(base.getXReal(), base.getXReal() + base.getWidth());
	}
}
