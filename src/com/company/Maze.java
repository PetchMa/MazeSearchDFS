/*=================================================================
Maze Class
Peter Ma
Oct 27 2019
Java JDK 11 LTS
=================================================================
Problem Definition – Holds the properties of the maze within a program.
Input – File for the maze
Output – Outputs the needed information from the maze without directly accessing the maze.
Process – Holds the properties of the maze within the class
=================================================================
List of Identifiers - will be listed in each file.
=================================================================
*/
package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Maze {
    private char[][] map = new char[8][12];
    private boolean[][] visited = new boolean[8][12];
    private int [][] route = new int[8][12];
    private int [][] ExitRoute = new int[8][12];
    Place Start = new Place(0,0);
    Place Exit = new Place(0,0);
    Place Cheese = new Place(0,0);
    /**Maze method
     * This constructor will initialize class
     * attributes when object is instantiated
     *
     * List of local variables
     * none
     *
     * @param - none
     * @return  - none
     */
    public Maze()throws IOException {
        map = readMAZE(map);
        for(int t=0;t<8;t++){
            for(int k=0; k<12;k++){
                this.visited[t][k]=false;
                if(map[t][k]=='B'){
                    this.route[t][k]=-1;
                    this.ExitRoute[t][k]=-1;
                }
                else {
                    this.route[t][k] = 0;
                    this.ExitRoute[t][k]=0;
                }
            }
        }
        this.Start = findTarget(map, 'R');
        this.Cheese = findTarget(map, 'C');
        this.Exit = findTarget(map, 'X');
    }// end of maze method
    /**readMAZE method
     * This functional method will reRead the map from the file location
     * at the same directory level as class.
     *
     * List of local variables
     * read - object from scanner class used to read <type scanner>
     * line - this is a variable that holds the value of each line read <type String>
     * row - the row thats read <type int>
     * count - the column thats read <type int>
     * @param - none
     * @throws - IOException
     * @return  - map <type char[][]>
     */
    public static char[][] readMAZE(char[][] map) throws IOException {
        Scanner read = new Scanner(new File("Maze1.txt"));
        String line;
        int row=0;
        while(read.hasNextLine()) {
            line = read.nextLine();
            int count=0;
            for(int i =0;i<line.length();i++){
                if(line.charAt(i)!=' '){
                    map[row][count]=line.charAt(i);
                    count++;
                }
            }
            row++;
        }
        return map;
    }// end of readMaze method
    /**resetMap method
     * This procedural method will reRead the map from the file location
     * at the same directory level as class.
     *
     * List of local variables
     * read - object from scanner class used to read <type scanner>
     * line - this is a variable that holds the value of each line read <type String>
     * row - the row thats read <type int>
     * count - the column thats read <type int>
     * @param - none
     * @throws - IOException
     * @return  - none
     */
    public void resetMap() throws IOException {
        Scanner read = new Scanner(new File("Maze1.txt"));
        String line;
        int row=0;
        while(read.hasNextLine()) {
            line = read.nextLine();
            int count=0;
            for(int i =0;i<line.length();i++){
                if(line.charAt(i)!=' '){
                    map[row][count]=line.charAt(i);
                    count++;
                }
            }
            row++;
        }
    }// end of resetMap method
    public Place getCheese(){
        return Cheese;
    }//end of getCheese method
    /**getHeight method
     * This functional method will get the value for height of the maze
     *
     * List of local variables
     * none
     *
     * @return  - <type int>
     */
    public int getHeight() {
        return map.length;
    }// end of getHeight method
    /**getWidth method
     * This functional method will get the value for width of the maze
     *
     * List of local variables
     * none
     *
     * @return  - <type int>
     */
    public int getWidth() {
        return map[0].length;
    }// end of getWidth method
    /**setStart method
     * This procedural method helps set the private variable Start
     *
     * List of local variables
     * none
     *
     * @param - none
     * @return  Start <type Place>
     */
    public Place getStart(){
        return Start;
    }// end of getStart method
    /**setExit method
     * This procedural method helps set the private variable Exit
     *
     * List of local variables
     * none
     *
     * @param - none
     * @return  Exit <type Place>
     */
    public Place getExit(){
        return Exit;
    }// end of getExit method
    /**isWall method
     * This functional method helps check if the position is a wall.
     * Used to help verify the direction of the mouse.
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @return  - <type Boolean>
     */
    public boolean isWall(int x, int y){
        if (map[x][y] == 'B') {
            return true;
        }
        return false;
    }// end of isWall method
    /**setVisited method
     * This procedural method helps set the value of a private
     * array visited[][].
     *
     * List of local variables
     * none
     *
     * @param - flag <type boolean>
     * @param - x <type int>
     * @param - y <type int>
     * @return  none
     */
    public void setVisited(int x, int y, boolean flag){
        visited[x][y]=flag;
    }// end of setVisited method
    /**isExplored method
     * This functional method helps check if the position has already
     * been explored used to help tracing through the maze recursively
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @return  - <type Boolean>
     */
    public boolean isExplored(int x, int y){
        return visited[x][y];
    }// end of isExplored method
    /**isExit method
     * This functional method helps check if the position has reached the exit.
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @return  - <type Boolean>
     */
    public boolean isExit(int x, int y){
        if(x==Exit.getX() && y==Exit.getY()){
            return true;
        }return false;
    }// end of the isExit method
    /**isCheese method
     * This functional method helps check if the position has reached the cheese.
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @return  - <type Boolean>
     */
    public boolean isCheese(int x, int y){
        if(x==Cheese.getX() && y==Cheese.getY()){
            return true;
        }return false;
    }// end of the isCheese Method
    /**isValidLocation method
     * This functional method checks if the position is within the maze
     *
     * List of local variables
     * none
     *
     * @param - row <type int>
     * @param - col <type int>
     * @return  - <type Boolean>
     */
    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }// end of the isValidLocation method
    /**findTarget method
     * This functional method finds where the target position by looping through entire maze and
     * searching for the target value.
     *
     * List of local variables
     * point - the place where the tuple position is stored. <type Place>
     *
     * @param - map <type char[][]>
     * @param - target <type char>
     * @return  - point <type Place>
     */
    public Place findTarget(char[][] map, char target){
        Place point = new Place(0,0);
        for(int i=0;i<map.length;i++){
            for(int k=0;k<map[0].length;k++){
                if(map[i][k]==target){
                    point.setX(i);
                    point.setY(k);
                    return point;
                }
            }
        }
        return point;
    }// end of findTarget Method
    /**printMap method
     * This procedural method will print out the entire map
     *
     * List of local variables
     * none
     *
     * @param - none
     * @return  - none
     */
    public void printMap(){
        for(int i=0;i<getHeight(); i++){
            for(int k=0;k<getWidth();k++){
                System.out.print(map[i][k] + " ");
            }
            System.out.println();
        }
    }// end of printMap method
    /**resetExplore method
     * This procedural method will fill the visited[][] with all false
     * values which will reset the array
     *
     * List of local variables
     * none
     *
     * @param - none
     * @return  - none
     */
    public void resetExplore(){
        for(int t=0;t<8;t++){
            for(int k=0; k<12;k++){
                this.visited[t][k]=false;
            }
        }
    }// end of resetExplore method
    /**setStart method
     * This procedural method will set the value for private variable
     * start.
     *
     * List of local variables
     * none
     *
     * @param - start <type Place>
     * @return  - none
     */
    public void setStart(Place start){
        this.Start = start;
    }// end of the setStart method
    /**setRoute method
     * This procedural method will set the value for private variable route[][]
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @param - val <type int>
     * @return  - none
     */
    public void setRoute(int x , int y, int val){
        this.route[x][y]=val;
    }// end of the setRoute method
    /**getRoute method
     * This functional method will get the value for private variable route[][]
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @return  - <type int>
     */
    public int getRoute(int x, int y){
        return this.route[x][y];
    }// end of the getRoute method
    /**setExitRoute method
     * This procedural method will set the value for private variable ExitRoute[][]
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @param - val <type int>
     * @return  - none
     */
    public void setExitRoute(int x , int y, int val){
        this.ExitRoute[x][y]=val;
    }// end of the setExitRoute method
    /**getExitRoute method
     * This functional method will get the value for private variable route[][]
     *
     * List of local variables
     * none
     *
     * @param - x <type int>
     * @param - y <type int>
     * @return  - <type int>
     */
    public int getExitRoute(int x, int y){
        return this.ExitRoute[x][y];
    }// end of the getExitRoute method
    /**getMap method
     * This functional method will get the private char array route[][]
     *
     * List of local variables
     * none
     * @return  - <type char[][]>
     */
    public char [][] getMap(){
        return this.map;
    }//end of getMap method
}
