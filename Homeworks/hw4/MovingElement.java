package hw4;
/**
* An element in which the <code>update</code> method updates the position each
* frame according to a <em>velocity</em> vector (deltaX, deltaY). The units are
* assumed to be "pixels per frame".
*
* @author Abby Taylor
* @version 1
*/
public class MovingElement extends SimpleElement{
	/*
	 * Instance double variable for delta x
	 */
	private double deltaX;
	/*
	 * Instance double variable for delta y
	 */
	private double deltaY;
	
	/**
	 * Constructs a MovingElement with a default velocity of zero in both
	 * directions.
	 *
	 * @param x      x-coordinate of upper left corner
	 * @param y      y-coordinate of upper left corner
	 * @param width  object's width
	 * @param height object's height
	 */
	public MovingElement(double x, double y, int width, int height) {
		super(x,y,width,height);
	}
	
	/*
	 * Returns the x-componenet of the object's velocity
	 * @return double deltaX
	 */
	public double getDeltaX() {
		return deltaX;
	}
	
	/*
	 * Returns the y-component of the object's velocity
	 * @return double deltaY
	 */
	public double getDeltaY() {
		return deltaY;
	}
	
	/*
	 * Sets the velocity for this object
	 * @param double deltaX
	 * @param double deltaY
	 */
	public void setVelocity(double deltaX, double deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	/*
	 * Update method adds (deltaX, deltaY) to the current position
	 */
	public void update() {
		super.update();
		super.setPosition(getXReal() + deltaX, getYReal() + deltaY);
	}
}
