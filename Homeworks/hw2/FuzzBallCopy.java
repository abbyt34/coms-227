package hw2;
/**
* Models a simplified baseball-like game called Fuzzball.
*
* @author Abby Taylor
*/
public class FuzzBallCopy {
/**
 * Number of strikes causing a player to be out.
 */
public static final int MAX_STRIKES = 2;
/**
 * Number of balls causing a player to walk.
 */
public static final int MAX_BALLS = 5;
/**
 * Number of outs before the teams switch.
 */
public static final int MAX_OUTS = 3;
// TODO: EVERTHING ELSE
// Note that this code will not compile until you have put in stubs for all
// the required methods.
 //Instance variables
private int inningCount;
public String inningPhase;
private boolean inningHalf;
private int ballCount;
private int calledStrikes;
private int currentOuts;
private boolean base1;
private boolean base2;
private boolean base3;
private boolean baseStatus;
private int team0Score;
private int team1Score;
//Helper methods
/*
 * This helper method will shift the runners according to the specifications given
 * @param boolean inning and int base
 */
public void shiftRunners(boolean inning, int base) {
	  if (base == 1) {
		  if (base3) {
			 if (inning) {
				 team0Score += 1;
			 }
			 else {
				 team1Score += 1;
			 }
			 base3 = false;
		  }
		  else if (base2) {
			  base3 = true;
			  base2 = false;
		  }
		  else if (base1) {
			  base2 = true;
			  base1 = false;
		  }
		  base1 = true;
	  }
	  else if (base == 2) {
		  if (base3) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base3 = false;
		  }
		  else if (base2) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base2 = false;
		  }
		  else if (base1) {
			  base3 = true;
		  }
		  base2 = true;
	  }
	  else if (base == 3) {
		  if (base3) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base3 = false;
		  }
		  else if (base2) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base2 = false;
		  }
		  else if (base1) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base1 = false;
		  }
		  base3 = true;
	  }
	  else if (base == 4) {
		  if (base3) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base3 = false;
		  }
		  else if (base2) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base2 = false;
		  }
		  else if (base1) {
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base1 = false;
		  }
		  if (inning) {
			  team0Score += 1;
		  }
		  else {
			  team1Score += 1;
		  }
	  }
}
 /*
 * This helper method resets the batters count
 */
public void resetCount() {
	  ballCount = 0;
	  calledStrikes = 0;
}
 /*
 * This helper method checks if there are enough outs to end the game
 */
public void checkOuts(boolean inning) {
	  if (currentOuts == MAX_OUTS) {
		  if (inning) {
			  inningHalf = false;
		  }
		  else {
			  inningHalf = true;
		  }
	  }
}
 /*
 * This helper method resets the current outs
 */
private void resetOuts() {
	  currentOuts = 0;
}
 /*
 * This helper method makes it so the T and B char can be switched according to the inning
 */
private void inningHelp() {
	  if (isTopOfInning()) {
		  inningPhase = "T";
	  }
	  else {
		  resetOuts();
		  inningPhase = "B";
	  }
}
 //Methods
 /*
 * Indicates a bad pitch, or a ball, which the batter did not swing at
 * Does nothing if the game has ended
 */
public void ball() {
	  ballCount += 1;
	  if (ballCount == MAX_BALLS-1) {
		  ballCount = 0;
		  base1 = true;
	  }
}
 /*
 * Indicates that the batter it out due to a caught fly ball
 * Does nothing if the game has ended
 */
public void caughtFly() {
	  currentOuts += 1;
}
 /*
 * Returns true if the game is over, false otherwise
 *
 * @return true or false boolean
 */
public boolean gameEnded() {
	  if (inningCount == 4) {
		  return true;
	  }
	  else {
		  return false;
	  }
}
 /*
 * Returns the count of balls for the current batter.
 *
 * @return int for ball count
 */
public int getBallCount() {
	  return ballCount;
}
 /*
 * Returns the number of called strikes for the current batter
 *
 * @return int for strikes
 */
public int getCalledStrikes() {
	  return calledStrikes;
}
 /*
 * Returns the number of outs for the team currently at bat
 *
 * @return int for outs
 */
public int getCurrentOuts() {
	  return currentOuts;
}
 /*
 * Returns the score for team 0
 *
 * @return int for score
 */
public int getTeam0Score() {
	  return team0Score;
}
 /*
 * Returns the score for team 1
 *
 * @return int for score
 */
public int getTeam1Score() {
	  return team1Score;
}
 /*
 * Indicates that the batter hit the ball
 *
 * @param int for distance the ball is hit
 */
public void hit(int distance) {
	  if (!gameEnded()) {
		  resetCount();
		  if (distance < 15) {
			  currentOuts += 1;
			  checkOuts(inningHalf);
		  }
		  if (distance >= 15 && distance < 150) {
			  // hit is a single
			  // all runners advance 1
			  // person on third scores; increase score by 1
			  shiftRunners(inningHalf,1);
		  }
		  if (distance >= 150 && distance < 200) {
			  // hit is a double
			  // all runners advance 2
			  // person on third and second score; increase score by 1 or 2
			  shiftRunners(inningHalf,2);
		  }
		  if (distance >= 200 && distance < 250) {
			  // hit is a triple
			  // all runners advance 3
			  // person on third and second and first score; increase score by 1 or 2 or 3
			  shiftRunners(inningHalf,3);
		  }
		  if (distance >= 250) {
			  // hit is a homerun
			  // batter and all runners score
			  shiftRunners(inningHalf,4);
		  }
	  }
}
 /*
 * Returns true is it's the first half of the inning (team 0 is at bat)
 *
 * @return boolean true or false
 */
public boolean isTopOfInning() {
	  if (inningHalf) {
		  return true;
	  }
	  else {
		  return false;
	  }
}
 /*
 * Returns true is there is a runner on the indicated base, false is otherwise
 *
 * @return boolean true or false
 * @param int which indicating which base to check
 */
public boolean runnerOnBase(int which) {
	  if (which == 1) {
		  if (base1 == true) {
			  baseStatus = true;
		  }
		  else {
			  baseStatus = false;
		  }
	  }
	  else if (which == 2) {
		  if (base2 == true) {
			  baseStatus = true;
		  }
		  else {
			  baseStatus = false;
		  }
	  }
	  else if (which == 3) {
		  if (base3 == true) {
			  baseStatus = true;
		  }
		  else {
			  baseStatus = false;
		  }
	  }
	  return baseStatus;
}
 /*
 * Indicates a strike for the current batter
 *
 * @param boolean swung to indicate if the batter swung
 */
public void strike(boolean swung) {
	  if (swung == false) {
		  if (calledStrikes == 1) {
			  currentOuts += 1;
			  calledStrikes = 0;
			  ballCount = 0;
		  }
		  else {
			  calledStrikes += 1;
		  }
	  }
	  else {
		  currentOuts += 1;
	  }
}
 /*
 * Returns the current inning
 *
 * @return int for inning
 */
public int whichInning() {
	  return inningCount;
}
 // The methods below are provided for you and you should not modify them.
// The compile errors will go away after you have written stubs for the
// rest of the API methods.
/**
 * Returns a three-character string representing the players on base, in the
 * order first, second, and third, where 'X' indicates a player is present and
 * 'o' indicates no player. For example, the string "oXX" means that there are
 * players on second and third but not on first.
 *
 * @return three-character string showing players on base
 */
public String getBases()
{
  return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o")
      + (runnerOnBase(3) ? "X" : "o");
}
/**
 * Returns a one-line string representation of the current game state. The
 * format is:
 * <pre>
 *      ooo Inning:1 [T] Score:0-0 Balls:0 Strikes:0 Outs:0
 * </pre>
 * The first three characters represent the players on base as returned by the
 * <code>getBases()</code> method. The 'T' after the inning number indicates
 * it's the top of the inning, and a 'B' would indicate the bottom. The score always
 * shows team 0 first.
 *
 * @return a single line string representation of the state of the game
 */
public String toString()
{
  String bases = getBases();
  String topOrBottom = (isTopOfInning() ? "T" : "B");
  String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
  return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(),
      getTeam1Score(), getBallCount(), getCalledStrikes(), getCurrentOuts());
}
 /*
 * Constructs a game with the given number of inning and starts at the top if inning 1
 *
 * @param int givenNumInnings
 */
public FuzzBallCopy(int givenNumInnings) {
	  inningCount = 1;
	  currentOuts = 0;
	  team0Score = 0;
	  team1Score = 0;
	  inningHelp();
	
}
}
