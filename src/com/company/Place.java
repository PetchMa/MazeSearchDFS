/*=================================================================
Place Class
Peter Ma
Oct 27 2019
Java JDK 11 LTS
=================================================================
Problem Definition – Hold the location of the mouse via coordinate pairs
Input – The row and the columns for the position/point
Output – The needed attributes of the point
Process – simply gets and sets the values of the x and y coordinate
=================================================================
List of Identifiers - will be listed in each file.
=================================================================
    Let x be the row value for the position  <type int>
    let Y be the column value for the position <type int>
*/
package com.company;
public class Place {
    private int x;
    private int y;
    /**Place method
     * This is the constructor method that initializes the Place object
     *
     * List of local variables
     * none
     *
     * @param - none
     * @return  none
     */
    public Place(int x, int y) {
        this.x = x; // initialize X variable
        this.y = y; // initialize Y variable
    }// end of constructor
    /**setX method
     * This procedural method helps set the variable X
     *
     * List of local variables
     * none
     *
     * @param - X <type int>
     * @return  none
     */
    public void setX(int x) {
        this.x = x; // sets the private X var
    }// end of setX method
    /**setY method
     * This procedural method helps set the variable Y
     *
     * List of local variables
     * none
     *
     * @param - Y <type int>
     * @return  none
     */
    public void setY(int y) {
        this.y = y;
    }// end of setY method
    /**getX method
     * This functional method helps access the private variable X
     *
     * List of local variables
     * none
     *
     * @param - NONE
     * @return  X <type int>
     */
    public int getX() {
        return x; // returns the private variable X
    }//end of getX method
    /**getY method
     * This functional method helps access the private variable Y
     *
     * List of local variables
     * none
     *
     * @param - NONE
     * @return  Y <type int>
     */
    public int getY() {
        return y;// returns the Private variable Y
    }// end of getY method
}// end of class
