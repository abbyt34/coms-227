package hw4;

import java.util.ArrayList;

import api.AbstractElement;

/**
 * A PlatformElement is an element with two distinctive behaviors. First, it can
 * be set up to move horizontally within a fixed set of boundaries. On reaching
 * a boundary, the x-component of its velocity is reversed. Second, it maintains
 * a list of <em>associated</em> elements whose basic motion all occurs relative
 * to the PlatformElement.
 * 
 * @author Abby Taylor
 * @version 1
 */
public class PlatformElement extends MovingElement{
	/*
	 * Instance variable for the minimum of the function
	 */
	private double min;
	/*
	 * Instance variable for the maximum of the function
	 */
	private double max;
	/*
	 * Instance variable for the attached base
	 */
	private AttachedElement attachedBase;
	/*
	 * Instance variable for the follower base
	 */
	private FollowerElement followerBase;
	/*
	 * Instance variable for the arraylist of the elevator as a whole elements
	 */
	private ArrayList<AbstractElement> elevatorElements;
	/*
	 * Constructs a new PlatformElement. Initially the left and right boundaries are
	 * <code>Double.NEGATIVE_INFINITY</code> and
	 * <code>Double.POSITIVE_INFINITY</code>, respectively.
	 * 
	 * @param x      x-coordinate of initial position of upper left corner
	 * @param y      y-coordinate of initial position of upper left corner
	 * @param width  object's width
	 * @param height object's height
	 */
	public PlatformElement(double x, double y, int width, int height) {
		super(x, y, width, height);
		elevatorElements = new ArrayList<>();
		setBounds(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
	/*
	 * Adds an associated element to this LiftElement, and sets this object to
	 * be the AttachedElement's base
	 * @param AttachedElement attached
	 */
	public void addAssociated(AttachedElement attached) {
		attachedBase.setBase(this);
		elevatorElements.add(attached);
	}
	
	/*
	 * Adds an associated element to this LiftElement, and sets this object to be the 
	 * FollowerElement's base
	 * @param FollowerElement follower
	 */
	public void addAssociated(FollowerElement follower) {
		followerBase.setBase(this);
		elevatorElements.add(follower);	
	}
	
	/*
	 * Deletes all associated elements that have been marked
	 */
	public void deleteMarkedAssociated() {
		elevatorElements.removeIf(AbstractElement :: isMarked);
	}
	
	/*
	 * Returns a list of all this Elevator's associated elements
	 * @return an arraylist called elevatorElements
	 */
	public ArrayList<AbstractElement> getAssociated(){
		return elevatorElements;
	}
	
	/*
	 * Return's the rigth boudnary for the platform's movement
	 * @return max double value
	 */
	public double getMax() {
		return max;
	}
	
	/*
	 * Return's the left boudnaru for this Platform's movmement
	 * @return min double value
	 */
	public double getMin() {
		return min;
	}

	/*
	 * Sets the upper and lower boundaries for this lift's movement
	 * @param double value min
	 * @param double value max
	 */
	public void setBounds(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	/*
	 * Update's this object's state for a new frame, and additionall calls update
	 * on all it's associated elements
	 */
	@Override
	public void update() {
		super.update();
		for (AbstractElement a : elevatorElements) {
			a.update();
		}
		if (getXReal() + getWidth() >= max || getXReal() <= min) {
			setVelocity(getDeltaX() * -1, getDeltaY());
			if (getXReal() + getWidth() >= max) {
				setPosition(max - getWidth(), getYReal());
			}
			else if (getXReal() <= min) {
				setPosition(min, getYReal());
			}
		}
	}
}
