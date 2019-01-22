
public class Queen {
    private int col;
    private int row;

    //Construct queen without arguments
    public Queen() {
        col = 0;
        row = 0;
    }

    //Construct queen with arguments
    public Queen(int a, int b) {
        col = a;
        row = b;
    }

    //Change column to new value
    public void setCol(int a) {
        col = a;
    }

    //Change row to new value
    public void setRow(int b) {
        row = b;
    }

    //Return column number
    public int getCol() {
        return col;
    }

    //Return row number
    public int getRow() {
        return row;
    }

    //Check for conflicts with columns
    public boolean ColConflict(Queen a) {
        //System.out.println("Col: "+a.getCol()+" Row: "+a.getRow());
        if (a.getCol() == col) {
            //System.out.println("Vertical Test " + col + " " + a.getCol());
            return true;
        }
        return false;
    }

    //Check for conflicts with rows and diagonals
    public boolean QConflict(Queen a, int n) {
        int tRow = 0, tCol = 0;
        int nCol = a.getCol();
        int nRow = a.getRow();
        if (row == nRow) {
            //System.out.println("True Cross");
            return true;
        }
        //mark off top left
        tCol = col - 1;
        tRow = row - 1;
        while (tRow >= 0 && tCol >= 0) {
            if (nRow == tRow && nCol == tCol) {
                //System.out.println("True Top Left");
                return true;
            }
            tCol--;
            tRow--;
        }

        //mark off bottom left
        tCol = col - 1;
        tRow = row + 1;
        while (tRow < n && tCol >= 0) {
            if (nRow == tRow && nCol == tCol) {
                //System.out.println("True Bottom Left");
                return true;
            }
            tCol--;
            tRow++;
        }

        //mark off top right
        tCol = col + 1;
        tRow = row - 1;
        while (tRow >= 0 && tCol < n) {
            if (nRow == tRow && nCol == tCol) {
                //System.out.println("True Top Right");
                return true;
            }
            tCol++;
            tRow--;
        }

        //mark off bottom right
        tCol = col + 1;
        tRow = row + 1;
        while (tRow < n && tCol < n) {
            if (nRow == tRow && nCol == tCol) {
                //System.out.println("True Bottom Right");
                return true;
            }
            tCol++;
            tRow++;
        }
        //System.out.println("No Conflict");
        return false;
    }
}