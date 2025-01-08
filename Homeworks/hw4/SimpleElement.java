package hw4;
import java.awt.Rectangle;
import api.AbstractElement;
// TODO:
// Special documentation requirement: (see page 11 of documentation)
// you must add a comment to the top of the SimpleElement class
// with a couple of sentences explaining how you decided to organize
// the class hierarchy for the elements.
/**
* Minimal concrete extension of AbstractElement. The <code>update</code> method
* in this implementation just increments the frame count.
*
* @author Abby Taylor
* @version 1
*/
public class SimpleElement extends AbstractElement{
	/*
	 * Instance double variable for the x-coordinate
	 */
	private double xInt;
	/*
	 * Instance double variable for the y-coordinate
	 */
	private double yInt;
	/*
	 * Instance integer variable for width
	 */
	private int width;
	/*
	 * Instance interger variable for height
	 */
	private int height;
	/*
	 * Instance interger variable for the frame count
	 */
	private int frameCount;
	/*
	 * Instance boolean variable for the delete functions
	 */
	private boolean delete;
	/**
	 * Constructs a new SimpleElement.
	 *
	 * @param x      x-coordinate of upper left corner
	 * @param y      y-coordinate of upper left corner
	 * @param width  element's width
	 * @param height element's height
	 */
	public SimpleElement(double x, double y, int width, int height) {
		this.xInt = x;
		this.yInt = y;
		this.width = width;
		this.height = height;
		
	}
	/*
	 * Returns the horizontal coordinate of the upper-left corner of this object's
	 * bounding rectangle, rounded to the nearest interger
	 * @return xIntUpdate value
	 */
	@Override
	public int getXInt() {
		int xIntUpdate = (int) xInt;
		return xIntUpdate;
	}
	
	/*
	 * Returns the vertical coordinate of the upper-left corner of this object's bounding
	 * rectangle, rounded to the nearest interger
	 * @return yIntUpdate value
	 */
	@Override
	public int getYInt() {
		int yIntUpdate = 0;
		if (yInt - (int)yInt <= 0.5) {
			yIntUpdate = (int) yInt + 1;
		}
		else {
			yIntUpdate = (int) yInt;
		}
		return yIntUpdate;
	}
	/*
	 * Returns the width of this object's bounding rectangle
	 * @return width value
	 */
	@Override
	public int getWidth() {
		return width;
	}
	/*
	 * Returns the height of this object's bounding rectangle
	 * @return height value
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	/*
	 * Returns the bounding rectangle for this object as an instance of
	 * java.awt.Rectangle
	 */
	@Override
	public Rectangle getRect() {
		return new Rectangle (getXInt(), getYInt(), width, height);
	}
	/*
	 * Sets the position of this element
	 * @param double newX
	 * @param double newY
	 */
	@Override
	public void setPosition(double newX, double newY) {
		xInt = newX;
		yInt = newY;
	}
	/*
	 * Returns the x-coordinate's exact value as a double
	 * @return double xInt
	 */
	@Override
	public double getXReal() {
		return xInt;
	}
	/*
	 * Returns the y-coordinate's exact value as a double
	 * @return double yInt
	 */
	@Override
	public double getYReal() {
		// TODO Auto-generated method stub
		return yInt;
	}
	/*
	 * Updates this object's attributes for the next frame
	 */
	@Override
	public void update() {
		frameCount += 1;
	}
	
	/*
	 * Returns the number of times the update() has been invoked for this object
	 * @return int frameCount
	 */
	@Override
	public int getFrameCount() {
		return frameCount;
	}
	/*
	 * Returns true if this element has been marked for deletion
	 * @return true or false boolean
	 */
	@Override
	public boolean isMarked() {
		if (delete) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * Marks this element for deletion
	 */
	@Override
	public void markForDeletion() {
		delete = true;
	}
	/*
	 * Determines whether this element's bounding rectangle overlaps the given element's
	 * bounding rectangle
	 * @return boolean true or false if it intersects
	 */
	@Override
	public boolean collides(AbstractElement other) {
		return getRect().intersects(other.getRect());
	}
}
