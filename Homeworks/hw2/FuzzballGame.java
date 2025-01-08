package hw2;
/**
* Models a simplified baseball-like game called Fuzzball.
*
* @author Abby Taylor
*/
public class FuzzballGame {
 /**
  * Number of strikes causing a player to be out.
  */
 public static final int MAX_STRIKES = 2;
 /**
  * Number of balls causing a player to walk.
  */
 public static final int MAX_BALLS = 4; //changed to four since it was at five and wasn't working correctly :)
 /**
  * Number of outs before the teams switch.
  */
 public static final int MAX_OUTS = 3;
 // TODO: EVERTHING ELSE
 // Note that this code will not compile until you have put in stubs for all
 // the required methods.
  //Instance variables
 /*
  * Number of balls
  */
 private int balls;
 /*
  * Number of strikes
  */
 private int strikes;
 /*
  * Number of outs
  */
 private int outs;
 /*
  * Indicates if it is the top of the inning
  */
 private boolean topOfInning;
 /*
  * Counts the innings
  */
 private int inningCount;
 /*
  * Indicates if first is occupied
  */
 private boolean base1;
 /*
  * Indicates if second is occupied
  */
 private boolean base2;
 /*
  * Indicates if third is occupied
  */
 private boolean base3;
 /*
  * Counts team 0 score
  */
 private int team0Score;
 /*
  * Counts team 1 score
  */
 private int team1Score;
 /*
  * Indicates if a runner is present on the base
  */
 private boolean baseStatus;
 /*
  * Indicates the max inning given to the constructor
  */
 private int maxInnings;
  //Helper methods
 
 /*
  * This helper method will shift the runners according to the specifications given
  * @param boolean inning and int base
  */
 private void shiftRunners(boolean inning, int base) {
	  resetCount(); //reset count before runners shift for new batter
	 
	  while (base > 0) {
		  if (runnerOnBase(3)) { //if a runner is on third, runner scores and third is vacated
			  if (inning) {
				  team0Score += 1;
			  }
			  else {
				  team1Score += 1;
			  }
			  base3 = false;
		  }
		  if (runnerOnBase(2)) { //if a runner on second, vacated and to third
			  base3 = true;
			  base2 = false;
		  }
		  if (runnerOnBase(1)) { //if a runner on first, vacated and to second
			  base2 = true;
			  base1 = false;
		  }
		  base -= 1;
	  }
 }
 
 /*
  * This helper method resets the batters count for balls and strikes
  */
 private void resetCount() {
	  balls = 0;
	  strikes = 0;
 }
 
 /*
  * This helper method checks the outs after a foul ball to see if the inning is over
  *
  * @param boolean inning to see if its top or bottom of the inning
  */
 private void checkOuts (boolean inning) {
	  if (outs == MAX_OUTS) {
		  switchInning();
	  }
 }
  /*
  * This helper method resets the current outs
  */
 private void resetOuts() {
	  outs = 0;
	  clearBases();
 }
 
 /*
  * This helper method checks for a walk
  */
 private void checkWalk() {
	  resetCount(); //resets count
	 
	  if (runnerOnBase(1)) { //if there is a runner on first
		  if (runnerOnBase(2)) { //if there is a runner on second
			  if (runnerOnBase(3)) { //if there is a runner on third
				  shiftRunners(topOfInning,1);
				  base1 = true;
			  }
			  else { //no runner on third
				  base3 = true;
			  }
		  }
		  else { //no runner on second
			  base2 = true;
		  }
	  }
	  else { //no runner on first
		  base1 = true;
	  }
 }
 
 /*
  * This helper method switches the inning so it compiles correctly
  */
 private void switchInning() {
	  if(topOfInning) {
		  topOfInning = false;
	  }
	  else {
		  inningCount += 1;
		  topOfInning = true;
	  }
 }
  /*
  * This helper methods clears the bases
  */
 private void clearBases() {
	  base1 = false;
	  base2= false;
	  base3 = false;
	
 }
 
 //Methods
  /*
  * Indicates a bad pitch, or a ball, which the batter did not swing at
  * Does nothing if the game has ended
  */
 public void ball() {
	  if (!gameEnded()) {
		  if (balls == MAX_BALLS) {
			  checkWalk();
			  balls = 0;
		  }
		  else {
			  balls += 1;
		  } 
	  }
 }
  /*
  * Indicates that the batter it out due to a caught fly ball
  * Does nothing if the game has ended
  */
 public void caughtFly() {
	  if (!gameEnded()) {
		  outs += 1;
		  resetCount();
		  if(outs == MAX_OUTS) {
			  switchInning();
			  resetOuts();
		  }
	  }
 }
  /*
  * Returns true if the game is over, false otherwise
  *
  * @return true or false boolean
  */
 public boolean gameEnded() {
	  if (inningCount == maxInnings) {
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
	  return balls;
 }
  /*
  * Returns the number of called strikes for the current batter
  *
  * @return int for strikes
  */
 public int getCalledStrikes() {
	  return strikes;
 }
  /*
  * Returns the number of outs for the team currently at bat
  *
  * @return int for outs
  */
 public int getCurrentOuts() {
	  return outs;
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
		  resetCount(); //reset count
		  /*
		   * Foul ball, automatic out
		   */
		  if (distance < 15) {
			  outs += 1;
			  if (outs == MAX_OUTS) {
				  switchInning();
				  resetOuts();
			  }
			  checkOuts(topOfInning);
		  }
		  /*
		   * Single hit, runner on third scores if present, everyone advances one
		   */
		  if (distance >= 15 && distance < 150) {
			  shiftRunners(topOfInning,1);
			  base1 = true;
		  }
		  /*
		   * Double hit, runner on second and third score, everyone advances two
		   */
		  if (distance >= 150 && distance < 200) {
			  shiftRunners(topOfInning,2);
			  base2 = true;
		  }
		  /*
		   * Triple hit, runner on first, second, and third score, everyone advances three
		   */
		  if (distance >= 200 && distance < 250) {
			  shiftRunners(topOfInning,3);
			  base3 = true;
		  }
		  /*
		   * Homerun hit, everone on base scores
		   */
		  if (distance >= 250) {
			  shiftRunners(topOfInning,3); //clear the bases
			  base3 = true; //puts the homerun hitter on third
			  shiftRunners(topOfInning,1); //scores the homerun hitter
		  }
	  }
 }
  /*
  * Returns true is it's the first half of the inning (team 0 is at bat)
  *
  * @return boolean topOfInning indicating true or false
  */
 public boolean isTopOfInning() {
	  return topOfInning;
 }
  /*
  * Returns true is there is a runner on the indicated base, false is otherwise
  *
  * @return boolean true or false
  * @param int which indicating which base to check
  */
 public boolean runnerOnBase(int which) {
	  if (which == 1) {
		  if (base1) {
			  baseStatus = true;
		  }
		  else {
			  baseStatus = false;
		  }
	  }
	  else if (which == 2) {
		  if (base2) {
			  baseStatus = true;
		  }
		  else {
			  baseStatus = false;
		  }
	  }
	  else if (which == 3) {
		  if (base3) {
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
	  if (!gameEnded()) {
		  if (swung || strikes == 1) {
			  outs += 1;
			  if (outs == 3) {
				  switchInning();
				  resetOuts();
			  }
			  resetCount();
		  }
		  else {
			  strikes += 1;
		  }
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
 
 //Constructor
  /*
  * Constructs a game with the given number of inning and starts at the top of inning 1
  *
  * @param int givenNumInnings
  */
 public FuzzballGame(int givenNumInnings) {
	  maxInnings = givenNumInnings;
	  inningCount = 1;
	  topOfInning = true;
	  balls = 0;
	  strikes = 0;
	  outs = 0;
	  team0Score = 0;
	  team1Score = 0; 
 }
}
