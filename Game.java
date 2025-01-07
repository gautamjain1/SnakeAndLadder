import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;
    Deque<Player> playersLists = new LinkedList<>();
    Player winner;


    public Game(){
        initilizeGame();
    }

    private void initilizeGame() {
        board = new Board(10 , 5, 7);
        dice = new Dice(1);
        winner = null;
        addPlayer();

    }

    private void addPlayer() {
        Player p1 = new Player("Player1 " , 0);
        Player p2 = new Player("Player2 " , 0);

        playersLists.add(p1);
        playersLists.add(p2);
    }

    public void startGame(){
        while(winner == null){
            Player playerTurn = findPlayerTurn();
            System.out.println("Player turn is: " + playerTurn.name + " current position is: " + playerTurn.currentPosition);

            int diceNumber = dice.rollDice();

            int playerNewPosition = playerTurn.currentPosition + diceNumber;

            playerNewPosition = jumpCheck(playerNewPosition);
            playerTurn.currentPosition = playerNewPosition;

            System.out.println("Player turn is: " + playerTurn.name + " new Position is: " + playerNewPosition);

            if(playerNewPosition >= board.cells.length*board.cells.length - 1){
                winner = playerTurn;
            }
        }
        System.out.println("Winner is: " + winner.name);
    }

    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition >= board.cells.length * board.cells.length - 1){
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null &&  cell.jump.start == playerNewPosition){
            String jumpBy = (cell.jump.start < cell.jump.end) ? "Ladder" : "Snake";
            System.out.println("Jump done by " + jumpBy);
            return cell.jump.end;
        }

        return playerNewPosition;

    }

    private Player findPlayerTurn() {
        Player playerTurns = playersLists.removeFirst();
        playersLists.addLast(playerTurns);
        return playerTurns;

    }
}
