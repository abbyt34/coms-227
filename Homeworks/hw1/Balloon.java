package hw1;
public class Balloon {
	
	/*
	 * This class takes in user input from a SimpleTests.java file provided to us for the assignment, and produces a
	 * calculation based on these inputs to find the altitude, velocity, and temperature of a hot air balloon. This
	 * program does not contain a main method, but the SimpleTests.java file contains the main method in which the
	 * testing is done.
	 * @author Abby Taylor
	 * @version 1.0
	 */
	
	// instance variables
	
	/*
	 * The following is the permanent instance variables used for calculations that will not change.
	 */
	private double HEAT_LOSS_FACTOR = 0.1;
	private double BALLOON_AIR_VOLUME = 61234;
	private double ACCELERATION_DUE_TO_GRAVITY = 9.81;
	private double GAS_CONSTANT = 287.05;
	private double STANDARD_PRESSURE = 1013.25;
	private double KELVINS = 273.15;
	
	/*
	 * The following are the variable settings for the methods and constructor in order to keep it organized and not having
	 * to instants the variables in the methods for a cleaner look.
	 */
	
	/*
	 * FUEL_REMAINING is used for the remaining fuel amount.
	 */
	private double FUEL_REMAINING;
	
	/*
	 * OUTSIDE_AIR_TEMP is used to keep track of the outside air temperature.
	 */
	private double OUTSIDE_AIR_TEMP;
	
	/*
	 * BALLOON_TEMP is used to keep the balloon temperature stored in celcius.
	 */
	private double BALLOON_TEMP;
	
	/*
	 * FUEL_BURN_RATE is used to keep the fuel burn rate stored in kelvins burned per second.
	 */
	private double FUEL_BURN_RATE;
	
	/*
	 * TETHER_LENGTH is used to store the current length of the tether connected to the balloon in meters.
	 */
	private double TETHER_LENGTH;
	
	/*
	 * BALLOON_MASS is used to store the current mass of the balloon.
	 */
	private double BALLOON_MASS;
	
	/*
	 * SIMULATION_TIME is used to store the current time of the simulation in seconds.
	 */
	private int SIMULATION_TIME;
	
	/*
	 * BALLOON_VELOCITY is used to store the balloons current velocity in m/s.
	 */
	private double BALLOON_VELOCITY;
	
	/*
	 * WIND_DIRECTION is used to store the balloons current degree of wind direction in degrees.
	 */
	private double WIND_DIRECTION;
	
	/*
	 * BALLOON_ALTITUDE is used to store the altitude for the balloon in meters.
	 */
	private double BALLOON_ALTITUDE;
	
	/*
	 * INITIAL_TEMP is used to store the initial temperature of the air in celcius.
	 */
	private double INITIAL_TEMP;
	
	/*
	 * INITIAL_DIRECTION is used to store the initial direction in degrees.
	 */
	private double INITIAL_DIRECTION;
	
	
	// methods
	
	/*
	 * Gets the remaining fuel that can be used to heat the air in the balloon
	 * Accessor
	 * @return FUEL_REMAINING ; an double value representing the amount of fuel remaining
	 */
	public double getFuelRemaining() {
		return FUEL_REMAINING;
	}
	
	/*
	 * Sets the remaining fuel that can be used to heat the air in the balloon with a range of the most amount of fuel to zero
	 * Mutator
	 * @param portion of fuel given
	 */
	public void setFuelRemaning(double fuel) {
		FUEL_REMAINING = Math.max(fuel, 0);
	}
	
	/*
	 * Gets the mass of the balloon
	 * Accessor
	 * @return BALLOON_MASS ; a double value representing the mass of the balloon
	 */
	public double getBalloonMass() {
		return BALLOON_MASS;
	}
	
	/*
	 * Sets the mass of the balloon
	 * Mutator
	 * @param portion of fuel given
	 */
	public void setBalloonMass(double fuel) {
		BALLOON_MASS = fuel;
	}
	
	/*
	 * Gets the outside air temperature
	 * Accessor
	 * @return OUTSIDE_AIR_TEMP ; a double value representing the outside air temperature in celcius
	 */
	public double getOutsideAirTemp() {
		return OUTSIDE_AIR_TEMP;
	}
	
	/*
	 * Sets the outside air temperature in celcius
	 * Mutator
	 * @param temperature value given
	 */
	public void setOutsideAirTemp(double temp) {
		OUTSIDE_AIR_TEMP = temp;
	}
	
	/*
	 * Gets the fuel burn rate in Kelvins per second
	 * Accessor
	 * @return FUEL_BURN_RATE ; a double value representing the fuel burn rate in Kelvins per second
	 */
	public double getFuelBurnRate() {
		return FUEL_BURN_RATE;
	}
	
	/*
	 * Sets the fuel burn rate in Kelvins per second
	 * Mutator
	 * @param the burn rate given in the constructor named rate
	 */
	public void setFuelBurnRate(double rate) {
		FUEL_BURN_RATE = rate;
	}
	
	/*
	 * Gets the balloon temperature in celcius
	 * Accessor
	 * @return BALLOON_TEMP ; a double variable representing the temperature inside the balloon in celcius
	 */
	public double getBalloonTemp() {
		return BALLOON_TEMP;
	}
	
	/*
	 * Sets the balloon temperature in celcius
	 * Mutator
	 * @param temp: the temperature given in the constructor and main method
	 */
	public void setBalloonTemp(double temp) {
		BALLOON_TEMP = temp;
	}
	
	/*
	 * Gets the balloon velocity in m/s^2
	 * Accessor
	 * @return BALLOON_VELOCITY ; a double variable representing the balloons velocity in meters per second^2
	 */
	public double getVelocity() {
		return BALLOON_VELOCITY;
	}
	
	/*
	 * Gets the balloons altitude in meters
	 * Accessor
	 * @return BALLOON_ALTITUDE ; a double variable representing the balloons altitude in meters
	 */
	public double getAltitude() {
		return BALLOON_ALTITUDE;
	}
	
	/*
	 * Gets the length of the tether in meters
	 * Accessor
	 * @return TETHER_LENGTH ; a double variable presenting the balloons full tether length
	 */
	public double getTetherLength() {
		return TETHER_LENGTH;
	}
	
	/*
	 * Gets the length of the remaining tether as the ballon climbs
	 * Accessor
	 * @return TETHER_LENGTH - BALLOON_ALTITUDE ; this takes the different between the two variables to find the remaining length in meters
	 */
	public double getTetherRemaining() {
		return TETHER_LENGTH - BALLOON_ALTITUDE;
	}
	
	/*
	 * Sets the lemgth of the tether
	 * Mutator
	 * @param length is found in the constructor's main method as a userinput
	 */
	public void setTetherLength(double length) {
		TETHER_LENGTH = length;
	}
	
	/*
	 * Gets the direction of the wind in degrees, between 0 (inclusive) and 360 (exclusive)
	 * Accessor
	 * @return WIND_DIRECTION ;  a double varible that represents the degree in which the wind is blowing the balloon
	 */
	public double getWindDirection() {
		return WIND_DIRECTION;
	}
	
	/*
	 * Updates the wind direction by adding the given value to the current wind direction
	 * Mutator
	 * @param deg = the degrees in which are given to add onto the existing value
	 */
	public void changeWindDirection (double deg) {
		WIND_DIRECTION = (WIND_DIRECTION + deg + 360) % 360;
	}
	
	/*
	 * Gets the full number of minutes from seconds
	 * Accessor
	 * @return minutes = the number of minutes returned to the system for the simulation
	 */
	public long getMinutes() {
		int minutes = SIMULATION_TIME / 60;
		return minutes;
	}
	
	/*
	 * Gets the number of seconds from a number of minutes inputted
	 * Accessor
	 * @return seconds = the number of seconds found out of the minute input
	 */
	public long getSeconds() {
		int seconds = SIMULATION_TIME % 60;
		return seconds;
	}
	
	/*
	 * This method is used to update each variable to their certain values and make calculations in the system to find
	 * the values the test cases need.
	 * Mutator
	 */
	public void update() {
		//moves the simualtion time by one second each update
		SIMULATION_TIME += 1;
		//fuelToConsume calculates how much fuel is being consumed each interval for easy calculations to find the last of when the fuel runs out
	    double fuelToConsume = Math.min(FUEL_BURN_RATE, FUEL_REMAINING);
	    //calculates how much fuel remains after oen second interval
	    FUEL_REMAINING -= fuelToConsume;
	    //calcualtes how much the balloon temperatures change eahc one second interval according to all the factors found
		double balloonTempChange = fuelToConsume + (OUTSIDE_AIR_TEMP - BALLOON_TEMP) * HEAT_LOSS_FACTOR;
		//calculates the total temperature in the balloon
		BALLOON_TEMP = BALLOON_TEMP + balloonTempChange;
		//calculates the desity of the air surrounding the balloon
		double densityOfAir = STANDARD_PRESSURE / (GAS_CONSTANT * (OUTSIDE_AIR_TEMP + KELVINS));
		//calculates the denisty of the air in the ballooon
		double densityOfBalloon = STANDARD_PRESSURE / (GAS_CONSTANT * (BALLOON_TEMP + KELVINS));
		//calculates the force of lift on the balloon
		double forceOfLift = BALLOON_AIR_VOLUME * (densityOfAir - densityOfBalloon) * ACCELERATION_DUE_TO_GRAVITY;
		//calculates the force of gravity working against the balloon
		double forceOfGravity = BALLOON_MASS * ACCELERATION_DUE_TO_GRAVITY;
		//calculates the net force being extered by the balloon
		double netForce = forceOfLift - forceOfGravity;
		//calculates the new acceleration the ballon is acheieving
		double netAcceleration = netForce / BALLOON_MASS;
		//calculates the balloons velocity
		BALLOON_VELOCITY += netAcceleration;
		//calculates the balloons altitude as the balloon rises
		BALLOON_ALTITUDE += BALLOON_VELOCITY;
		//sets the altitude to make sure it doesn't go under 0 or above the tether length
		BALLOON_ALTITUDE = Math.min(Math.max(BALLOON_ALTITUDE, 0), TETHER_LENGTH);
	}
	
	/*
	 * Resets the simulation to its initial state before running at all
	 */
	public void reset() {
		OUTSIDE_AIR_TEMP = INITIAL_TEMP;
		WIND_DIRECTION = INITIAL_DIRECTION;
		BALLOON_TEMP = OUTSIDE_AIR_TEMP;
		SIMULATION_TIME = 0;
		BALLOON_ALTITUDE = 0;
		FUEL_REMAINING = 0;
		FUEL_BURN_RATE = 0;
		BALLOON_MASS = 0;
		BALLOON_VELOCITY = 0;
		TETHER_LENGTH = 0;		
	}
	
	// constructor
	
	/*
	 * Balloon is the constructor for the entire simulation. it gives the intial values as well as stores some temporary values
	 * for calculations along the road and different situations
	 * @param airTemp is the intital air temperature given and windDiretion is the intial wind direction given to the simulation
	 */
	public Balloon (double airTemp, double windDirection) {
		INITIAL_TEMP = airTemp;
		OUTSIDE_AIR_TEMP = airTemp;
		BALLOON_TEMP = OUTSIDE_AIR_TEMP;
		SIMULATION_TIME = 0;
		BALLOON_ALTITUDE = 0;
		FUEL_REMAINING = 0;
		FUEL_BURN_RATE = 0;
		BALLOON_MASS = 0;
		BALLOON_VELOCITY = 0;
		TETHER_LENGTH = 0;
		INITIAL_DIRECTION = windDirection;
		WIND_DIRECTION = windDirection;
	}
}
