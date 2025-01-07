import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;

    public Board(int boardSize , int numberOfSnake , int numberOfLadder){
        initializeCells(boardSize);
        addSnakeLadder(cells , numberOfSnake , numberOfLadder);
    }

    private void initializeCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];

        for(int i = 0 ; i<boardSize ; i++){
            for (int j=0 ; j<boardSize ; j++){
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }
    private void addSnakeLadder(Cell[][] cells, int numberOfSnake, int numberOfLadder) {
        while(numberOfSnake>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1 , cells.length* cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1 , cells.length* cells.length - 1);

            if(snakeHead <= snakeTail){
                continue;
            }

            Jump snakeObj = new Jump();
            snakeObj.start = snakeHead;
            snakeObj.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = snakeObj;

            numberOfSnake--;

        }
        while(numberOfLadder>0){
            int ladderHead = ThreadLocalRandom.current().nextInt(1 , cells.length* cells.length + 1);
            int ladderTail = ThreadLocalRandom.current().nextInt(1 , cells.length* cells.length + 1);

            if(ladderTail <= ladderHead){
                continue;
            }

            Jump ladderObj = new Jump();
            ladderObj.start = ladderHead;
            ladderObj.end = ladderTail;

            Cell cell = getCell(ladderHead);
            cell.jump = ladderObj;
            numberOfLadder--;
        }
    }

    Cell getCell(int playerPosition) {
        int row = playerPosition / cells.length;
        int col = playerPosition/ cells.length;

        return cells[row][col];
    }

}
