import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.*;

public class NQueens {
    public static void main(String[] args) throws IOException {

        int dataC = 0, k = 0;
        int lineNumber = 0;

        int[] dataR = new int[200];

        //int[] dataR = {5,3,5,4,1,1,15,11,13,7,4,4,9,9,9,8,5,4,10,6,1,6,6,6,13,2,11,11,10,7,4,1,1};

        // check number of command line arguments is at least 2
        if (args.length < 2) {
            System.out.println("Usage: java –jar FileTokens.jar <input file> <output file>");
            System.exit(1);
        }

        // open files
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        // read lines from in, extract and print tokens from each line
        while (in.hasNextLine()) {
            lineNumber++;

            // trim leading and trailing spaces, then add one trailing space so
            // split works on blank lines
            String line = in.nextLine().trim() + " ";

            // split line around white space
            String[] token = line.split("\\s+");

            // print out tokens
            int n = token.length;
            //System.out.println("N: "+n);
            for (int i = 0; i < n; i++) {
                dataR[i + k] = Integer.parseInt(token[i]);
            }
            k += 3;
        }
        //dataR[i] has all of the input ints

        while (dataR[dataC] != 0) {
            //create new queen array
            Queen[] List = new Queen[dataR[dataC]];

            //fill first queen in n = 0
            List[0] = new Queen((dataR[dataC + 1] - 1), (dataR[dataC + 2] - 1));

            //recursive queens are called, if return false, print no solution
            if (Queens(1, List, (dataR[dataC]) - 1) == false) {
                //System.out.println("No solution");
                out.println("No solution");

            } else {
                QSort(List);
                for (int i = 0; i < List.length; i++) {
                    out.print((List[i].getCol() + 1) + " " + (List[i].getRow() + 1) + " ");
                }
                out.println();
            }
            dataC += 3;
        }
        // close files
        in.close();   // <----PUT THIS BACK IN
        out.close();   //<----PUT THIS BACK IN
    }

    //Queens recursive function
    public static boolean Queens(int n, Queen[] List, int totLen) {

        //set row and column to 0
        int newCol = 0, newRow = 0;
        //System.out.println("LEVEL N: " + n);
        //create new queen at 0,0
        List[n] = new Queen(newCol, newRow);

        //n == 0 base case
        if (n == totLen) {
            while (true) {
                //check columns
                for (int i = 0; i < n; i++) {
                    if (List[n].ColConflict(List[i])) {
                        newCol++;
                        List[n].setCol(newCol);
                        i = -1;
                    }
                }
                //System.out.println("Col Check");

                //check rows/diagonals
                for (int i = 0; i < n; i++) {

                    if (List[n].QConflict(List[i], (totLen + 1))) {
                        if (newRow < totLen) {
                            newRow++;
                            List[n].setRow(newRow);
                            i = -1;
                        } else {
                            return false;
                        }
                    }
                }

                return true;
            }
            //return true;
        }

        //recursive case, infinite loop
        while (true) {

            //check columns, check queens from 0 to the current queen
            for (int i = 0; i < n; i++) {

                //if Queen n is in same column as Queen i, move 1 column to the right
                //reset counter to check from Queen 0
                if (List[n].ColConflict(List[i])) {
                    newCol++;
                    List[n].setCol(newCol);
                    i = -1;
                }
            }
            //System.out.println("Col Check");

            //check rows/diagonals
            //check Queens from 0 to the current queen
            for (int i = 0; i < n; i++) {


                //if Queen n conflicts with Queen i
                if (List[n].QConflict(List[i], (totLen + 1))) {

                    //if the row is still less than the board size, move 1 row down
                    //reset checker to 0
                    if (newRow < totLen) {
                        newRow++;

                        List[n].setRow(newRow);
                        i = -1;

                        //if newRow is more than the total size of the board, then return false
                    } else {
                        return false;
                    }
                }
            }
            //if reached this point, you have passed the row check
            //System.out.println("Row Check");


            if (Queens(n + 1, List, totLen)) { //RECURSION

                //if the next Queen returns true, then return true
                return true;
            } else {
                if (newRow >= totLen) {
                    return false;
                }
                newRow++;
                List[n].setRow(newRow);

            }
        }
    }

    //Sort Queens based on column
    public static Queen[] QSort(Queen[] List) {
        Queen temp = new Queen(0, 0);
        for (int i = 0; i < List.length; i++) {
            for (int j = 0; j < List.length; j++) {
                if (List[i].getCol() < List[j].getCol()) {
                    temp.setCol(List[i].getCol());
                    temp.setRow(List[i].getRow());
                    List[i].setCol(List[j].getCol());
                    List[i].setRow(List[j].getRow());
                    List[j].setCol(temp.getCol());
                    List[j].setRow(temp.getRow());
                }
            }
        }
        return List;
    }
}