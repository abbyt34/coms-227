package hw4;
/**
* Moving element in which the vertical velocity is adjusted each frame by a
* gravitational constant to simulate gravity. The element can be set to
* "grounded", meaning gravity will no longer influence its velocity.
*
* @author Abby Taylor
* @version 1
*/
public class FlyingElement extends MovingElement{
	/*
	 * Instance variable for the function's gravity
	 */
	private double gravity;
	/*
	 * Instance variable for the functions grounded boolean
	 */
	private boolean grounded;
	
	
	/**
	 * Constructs a new FlyingElement. By default it should be grounded, meaning
	 * gravity does not influence its velocity.
	 *
	 * @param x      x-coordinate of upper left corner
	 * @param y      y-coordinate of upper left corner
	 * @param width  element's width
	 * @param height element's height
	 */
	public FlyingElement(double x, double y, int width, int height) {
		super(x, y, width, height);
	}
	/*
	 * Marks if the object is grounded and not moving up or down
	 * @return grounded boolean
	 */
	public boolean isGrounded() {
		return grounded;
	}
	
	/*
	 * Sets the boolean grounded to true if it is marked to set
	 * @param boolean grounded
	 */
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	/*
	 * Updates the position and adds the gravitational constant to the y-component of
	 * the velocity
	 */
	public void update() {
		super.update();
		if (!grounded) {
			super.setVelocity(super.getDeltaX(), super.getDeltaY() + gravity);
		}
		super.setPosition(super.getXReal(), super.getYReal());
	}
	
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
}
