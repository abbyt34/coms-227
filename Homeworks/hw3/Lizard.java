package hw3;

import static api.Direction.*;

import java.util.ArrayList;

import api.BodySegment;
import api.Cell;
import api.Direction;

/**
 * Represents a Lizard as a collection of body segments.
 */
public class Lizard {
	/**
	 * Constructs a Lizard object.
	 */
	
	private ArrayList<BodySegment> lizardSegments;
	private static BodySegment head;
	private static BodySegment tail;
	
	
	public Lizard() {
	}

	/**
	 * Sets the segments of the lizard. Segments should be ordered from tail to
	 * head.
	 * 
	 * @param segments list of segments ordered from tail to head
	 */
	public void setSegments(ArrayList<BodySegment> segments) {
		lizardSegments = segments;
		// if it is an actual lizard
		if (lizardSegments.size() > 0) {
			//finding the tail segment
			tail = lizardSegments.get(0);
			//finding the head segment
			head = lizardSegments.get(segments.size() - 1);
		}
	}

	/**
	 * Gets the segments of the lizard. Segments are ordered from tail to head.
	 * 
	 * @return a list of segments ordered from tail to head
	 */
	public ArrayList<BodySegment> getSegments() {
		return lizardSegments;
	}

	/**
	 * Gets the head segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the head segment
	 */
	public BodySegment getHeadSegment() {
		if (lizardSegments.size() > 0) {
			// if the lizard is true, then return the intialized head
			return head;
		}
		else {
			return null;
		}
	}

	/**
	 * Gets the tail segment of the lizard. Returns null if the segments have not
	 * been initialized or there are no segments.
	 * 
	 * @return the tail segment
	 */
	public BodySegment getTailSegment() {
		if (lizardSegments.size() > 0) {
			// if the lizard is true, then return the intialized tail
			return tail;
		}
		else {
			return null;
		}
	}

	/**
	 * Gets the segment that is located at a given cell or null if there is no
	 * segment at that cell.
	 * 
	 * @param cell to look for lizard
	 * @return the segment that is on the cell or null if there is none
	 */
	public BodySegment getSegmentAt(Cell cell) {
		for (BodySegment segment : lizardSegments) {
			//if the segment is at the given cell
			if (segment.getCell().equals(cell))
			{
				return segment;
			}
		}
		return null;
	}

	/**
	 * Get the segment that is in front of (closer to the head segment than) the
	 * given segment. Returns null if there is no segment ahead.
	 * 
	 * @param segment the starting segment
	 * @return the segment in front of the given segment or null
	 */
	public BodySegment getSegmentAhead(BodySegment segment) {
		int segmentIndex = lizardSegments.indexOf(segment);
		//following the parameters
		if (segmentIndex == -1 || segmentIndex == lizardSegments.indexOf(head)) {
			return null;
		}
		// returning the segment ahead
		return lizardSegments.get(segmentIndex + 1);
	}

	/**
	 * Get the segment that is behind (closer to the tail segment than) the given
	 * segment. Returns null if there is not segment behind.
	 * 
	 * @param segment the starting segment
	 * @return the segment behind of the given segment or null
	 */
	public BodySegment getSegmentBehind(BodySegment segment) {
		int segmentIndex = lizardSegments.indexOf(segment);
		//following the parameters
		if (segmentIndex == -1 || segmentIndex == lizardSegments.indexOf(tail)) {
			return null;
		}
		//returning the segment behind
		return lizardSegments.get(segmentIndex - 1);
	}

	/**
	 * Gets the direction from the perspective of the given segment point to the
	 * segment ahead (in front of) of it. Returns null if there is no segment ahead
	 * of the given segment.
	 * 
	 * @param segment the starting segment
	 * @return the direction to the segment ahead of the given segment or null
	 */
	public Direction getDirectionToSegmentAhead(BodySegment segment) {
		//finding the segment ahead that we are looking for their direction
		BodySegment ahead = getSegmentAhead(segment);
		
		if (lizardSegments.size() > 1) {
			//if the segment ahead is present
			if (ahead != null) {
				Cell aheadCell = ahead.getCell();
				Cell segmentCell = segment.getCell();
				
				int aheadRow = aheadCell.getRow();
				int aheadCol = aheadCell.getCol();
				int segmentRow = segmentCell.getRow();
				int segmentCol = segmentCell.getCol();
				
				int rowDifference = aheadRow - segmentRow;
				int colDifference = aheadCol - segmentCol;
				
				if (rowDifference == 1 && colDifference == 0) {
					return DOWN;
				}
				if (rowDifference == -1 && colDifference == 0) {
					return UP;
				}
				if (rowDifference == 0 && colDifference == 1) {
					return RIGHT;
				}
				if (rowDifference == 0 && colDifference == -1) {
					return LEFT;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the direction from the perspective of the given segment point to the
	 * segment behind it. Returns null if there is no segment behind of the given
	 * segment.
	 * 
	 * @param segment the starting segment
	 * @return the direction to the segment behind of the given segment or null
	 */
	public Direction getDirectionToSegmentBehind(BodySegment segment) {
		//finding the segment of what direction the behind is
		BodySegment behind = getSegmentBehind(segment);
		
		if (lizardSegments.size() > 1) {
			//if the behind segment is present
			if (behind != null) {
				Cell behindCell = behind.getCell();
				Cell segmentCell = segment.getCell();
				
				int behindRow = behindCell.getRow();
				int behindCol = behindCell.getCol();
				int segmentRow = segmentCell.getRow();
				int segmentCol = segmentCell.getCol();
				
				int rowDifference = behindRow - segmentRow;
				int colDifference = behindCol - segmentCol;
				
				if (rowDifference == 1 && colDifference == 0) {
					return DOWN;
				}
				if (rowDifference == -1 && colDifference == 0) {
					return UP;
				}
				if (rowDifference == 0 && colDifference == 1) {
					return RIGHT;
				}
				if (rowDifference == 0 && colDifference == -1) {
					return LEFT ;
				}
				
			}
		}
		return null;
	}

	/**
	 * Gets the direction in which the head segment is pointing. This is the
	 * direction formed by going from the segment behind the head segment to the
	 * head segment. A lizard that does not have more than one segment has no
	 * defined head direction and returns null.
	 * 
	 * @return the direction in which the head segment is pointing or null
	 */
	public Direction getHeadDirection() {
		//getting the segment behind the head to find the direction of the head
		BodySegment behindHead = getSegmentBehind(head);
		
		if (lizardSegments.size() > 1) {
			//if the segment behind the head is there
			if ( behindHead != null) {
				Cell behindHeadCell = behindHead.getCell();
				Cell headCell = head.getCell();
				
				int behindRow = behindHeadCell.getRow();
				int behindCol = behindHeadCell.getCol();
				int headRow = headCell.getRow();
				int headCol = headCell.getCol();
				
				int rowDifference = behindRow - headRow;
				int colDifference = behindCol - headCol;
				
				if (rowDifference == 1 && colDifference == 0) {
					return DOWN;
				}
				if (rowDifference == -1 && colDifference == 0) {
					return UP;
				}
				if (rowDifference == 0 && colDifference == 1) {
					return LEFT;
				}
				if (rowDifference == 0 && colDifference == -1) {
					return RIGHT;
				}
			}
		} return null;
	}

	/**
	 * Gets the direction in which the tail segment is pointing. This is the
	 * direction formed by going from the segment ahead of the tail segment to the
	 * tail segment. A lizard that does not have more than one segment has no
	 * defined tail direction and returns null.
	 * 
	 * @return the direction in which the tail segment is pointing or null
	 */
	public Direction getTailDirection() {
		//getting the segment ahead of the tail to check for direction
		BodySegment aheadTail = getSegmentAhead(tail);
		
		if(lizardSegments.size() > 1) {
			//if the segment ahead of the tail is present
			if (aheadTail != null) {
				Cell aheadTailCell = aheadTail.getCell();
				Cell tailCell = tail.getCell();
				
				int behindRow = aheadTailCell.getRow();
				int behindCol = aheadTailCell.getCol();
				int tailRow = tailCell.getRow();
				int tailCol = tailCell.getCol();
				
				int rowDifference = behindRow - tailRow;
				int colDifference = behindCol - tailCol;
				
				if (rowDifference == 1 && colDifference == 0) {
					return DOWN;
				}
				if (rowDifference == -1 && colDifference == 0) {
					return UP;
				}
				if (rowDifference == 0 && colDifference == 1) {
					return LEFT;
				}
				if (rowDifference == 0 && colDifference == -1) {
					return RIGHT;
				}
			}
		} return null;
	}

	@Override
	public String toString() {
		String result = "";
		for (BodySegment seg : getSegments()) {
			result += seg + " ";
		}
		return result;
	}
}
