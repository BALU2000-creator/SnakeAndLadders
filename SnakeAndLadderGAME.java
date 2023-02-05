
import java.util.*;
class Jumper{
    Player Currentplayer;
    Dice dice;
    Jumps jumps; 
    int MaxNumBoundValues;
    int boardMaxValue;
    HashMap<Integer, ArrayList<Player>> SnakeNotToBite;
    Jumper(Dice dice, int MaxNumBoundValues, Jumps jumps, int boardMaxValue){
        this.dice = dice;
        this.SnakeNotToBite = new HashMap<>();
        this.jumps = jumps;
        this.MaxNumBoundValues = MaxNumBoundValues; 
        this.boardMaxValue = boardMaxValue;
    }

    void NextMove(){
        int currLocation = this.Currentplayer.getLocation();
        int numRolled = getDiceNumber();
        int getcurrLocation = RollOnIfSix(numRolled, currLocation);
        currLocation = AnySankeAndLadder(getcurrLocation);
        this.Currentplayer.setLocation(currLocation);
        if(getmap().containsKey(getcurrLocation)){
            if(getcurrLocation-currLocation > 0){
                System.out.println("Snake Bitten and reached to : "+currLocation);
            }else{
                System.out.println("Ladder, climed to : "+currLocation);
                NextMove();
            }
        }
    }

    int RollOnIfSix(int numRolled,int currLocation){
        int locationToChange = 0, numOfMaximums = 0;
        while((numRolled==this.dice.getMaxDice()) && numOfMaximums<this.MaxNumBoundValues){
            numOfMaximums++;
            locationToChange = locationToChange + numRolled;
            numRolled = getDiceNumber();
        }
        if(numOfMaximums<this.MaxNumBoundValues) locationToChange =  locationToChange+numRolled;
        else locationToChange =  numRolled;
        if(locationToChange+currLocation>this.boardMaxValue){
            return currLocation;
        }
        return currLocation+locationToChange;
    }
    int AnySankeAndLadder(int currLocation){
        int locationToChange = 0;
        if(getmap().containsKey(currLocation)){
            locationToChange = getmap().get(currLocation);  
            return locationToChange;
        }
        return currLocation;
    }
    int getDiceNumber(){
        UserEnterInput();
        int rolledNum = this.dice.rollDice();
        System.out.println(this.Currentplayer.name+" rolls the Number : "+rolledNum);
        return rolledNum;
    }
    Player getCurrentPlayer(){
        return this.Currentplayer;
    }
    HashMap<Integer, Integer> getmap(){
        return this.jumps.getMap();
    }
    void setMaxNumBoundValues(int MaxNumBoundValues){
        this.MaxNumBoundValues = MaxNumBoundValues;
    }
    void setCurrentPlayer(Player player){
        this.Currentplayer = player;
    }
    void UserEnterInput(){
        Scanner sc = new Scanner(System.in);
        String st = "Press Enter";
        do{
            System.out.println("Press Enter Button to roll dice");
            st= sc.nextLine();
        }while(!st.equals(""));
    }
}
class Game{
    SnakesAndLadders game;
    Jumper jumper;
    int ContinueUntillNumOfPlayers = 0;
    Game(SnakesAndLadders game){
        this.game = game;
        this.jumper = new Jumper(this.game.getDice(), 2, this.game.getBoard().getJumps(), this.game.getBoard().getMaxBoardSize());
    }
    Game(SnakesAndLadders game, int ContinueUntillNumOfPlayers){
        this.game = game;
        this.jumper = new Jumper(this.game.getDice(), 2, this.game.getBoard().getJumps(), this.game.getBoard().getMaxBoardSize());
        this.ContinueUntillNumOfPlayers = ContinueUntillNumOfPlayers;
    }
    void startGame(){
        Queue<Player> queue = this.game.getPlayers().getQueuePlayers();
        while(!(queue.size()==this.ContinueUntillNumOfPlayers)){
            Player player = queue.poll();
            System.out.println("\n\nPlayer "+player.getName()+" turn-->"+" Current location is "+player.getLocation());
            this.jumper.setCurrentPlayer(player);
            this.jumper.NextMove();
            System.out.println("player "+player.getName()+" location changed is "+player.getLocation());
            if(player.getLocation()<100) queue.add(player);
            else this.game.getPlayers().AddPlayersIntoLeaderBoard(player);
        }
        this.game.getPlayers().displayLeaderBoard();
    }
}
class SnakesAndLadders{
    Board board;
    Players players;
    Dice dice;
    SnakesAndLadders(Board board, Players players, Dice dice){
        this.board = board;
        this.players = players;
        this.dice = dice;
    }
    Dice getDice(){
        return this.dice;
    }
    Board getBoard(){
        return this.board;
    }
    Players getPlayers(){
        return this.players;
    }
}
class Jumps{
    HashMap<Integer, Integer> map = new HashMap<>();
    Jumps(HashMap<Integer, Integer> map, boolean random, int numOfSnakes, int numOfLadders){
        if(random==true){
            this.map = constructRandomMap(numOfSnakes, numOfLadders);
        }
        else this.map = map;
    }
    Jumps(){
        map.put(4, 25); 
        map.put(13, 46); 
        map.put(15, 69); 
        map.put(74, 92); 
        map.put(33, 49);
        map.put(42, 63); 
        map.put(62,81); 
        map.put(99,41); 
        map.put(76, 58); 
        map.put(66, 45); 
        map.put(89, 53); 
        map.put(54, 31);
        map.put(43,18); 
        map.put(40, 3); 
        map.put(27, 5);
    }
    HashMap<Integer, Integer> constructRandomMap(int numOfSnakes, int numOfLadders){
        // TO-DO
        
        return this.map;
    }
    HashMap<Integer, Integer> getMap(){
        return map;
    }
    void setmap(HashMap<Integer, Integer> map){
        cleanMapFromInfLoops(map);
        this.map = map;
        
    }
    void cleanMapFromInfLoops(HashMap<Integer, Integer> map){
        //To-D0
        return;
    }
}
class Dice{
    private int maxDice=6, minDice=1, numOfDices=1;
    Dice(int minDice, int maxDice, int numOfDices){
        setMinDice(minDice);
        setMaxDice(maxDice);
        setNumOfDices(numOfDices);
    }
    void ModifyMinMax(int minDice, int maxDice, int numOfDices){
        setMinDice(minDice+numOfDices);
        setMaxDice(maxDice*numOfDices);
    }
    int rollDice(){
        return (int)((Math.random() * (this.maxDice - this.minDice)) + this.minDice);
    }
    //Getters & Setters
    int getMaxDice(){
        return this.maxDice;
    }
    int getMinDice(){
        return this.minDice;
    }
    int getNumOfDices(){
        return this.numOfDices;
    }
    void setMaxDice(int MaxDice){
        this.maxDice=MaxDice;
    }
    void setMinDice(int MinDice){
        this.minDice=MinDice;
    }
    void setNumOfDices(int numOfDices){
        this.numOfDices = numOfDices;
        ModifyMinMax(getMinDice(), getMaxDice(), getNumOfDices());
    }
    
}
class Board{
    /**Board Conatins just nuimbers and Snakes,Ladder which called here together as jumps**/
    int max, min;
    Jumps jumps;
    Board(int min, int max, Jumps jumps){
        this.max = max;
        this.min = min;
        this.jumps = jumps;

    }
    void setMax(int max){
        this.max = max;
    }
    void setMin(int min){
        this.min = min;
    }
    void setJumps(Jumps jumps){
        this.jumps = jumps;
    }
    Jumps getJumps(){
        return this.jumps;
    }
    int getMaxBoardSize(){
        return this.max;
    }
    int getMinBoardSize(){
        return this.min;
    }
}
class Player{
    String id, name;
    int location=0;
    boolean turn = false;
    Player(String id, String name, int location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.turn = false;
    }
    boolean getTurn(){
        return this.turn;
    }
    int getLocation(){
        return this.location;
    }
    void setLocation(int location){
        this.location = location;
    }
    String getName(){
        return this.name;
    }
}
class Players{
    private HashMap<String, Player> map;
    private Queue<Player> queuePlayers;
    private Queue<Player> LeaderBoard;
    Players(HashMap<String, Player> map, Queue<Player> players){
        this.map = map;
        this.queuePlayers = players;
        this.LeaderBoard = new LinkedList<>();
    }

    Queue<Player> getQueuePlayers(){
        return this.queuePlayers;
    }
    void removeWinPlayer(){
        Player player = this.queuePlayers.poll();
        this.LeaderBoard.add(player);
    }
    void AddPlayersIntoLeaderBoard(Player player){
        this.LeaderBoard.add(player);
    }
    void AddPlayersInMiddleOfGame(Queue<Player> players){
        /**Function upadte map, queuePlayers -TODO**/
        return;
    }
    void displayLeaderBoard(){
        int i =0;
        if(this.LeaderBoard.isEmpty()){
            System.out.println("Currently there are no players in LeaderBoard");
        }
        for(Player player : this.LeaderBoard){
            System.out.println("Player Name = "+player.name+" in win position = "+i);
            i++;
        }
    }
    void ListDownAllPlayerIdsAndNames(){
        for(Player player : this.queuePlayers){
            System.out.println("Player Name = "+player.name+", Player id = "+player.id);
        }
    }
}
class SnakeAndLadderGAME {
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.driverTheGame();
    }
}
class Driver{
    String getAlphaNumericString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
    void driverTheGame(){
                Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the SnakeAndLadders Game\nDo you want to play? -> Yes or No");
        String st = sc.nextLine();
        while(!st.equals("Yes")){
            System.out.println("Please enter Yes or No :");
            st = sc.nextLine();
            if(st.equals("No")){
                System.out.println("Ok thank you, Have a nice day.");
                return;
            }
        }
        System.out.println("Enter the Number of Players in team :");
        int numOfPlayers = Integer.parseInt(sc.nextLine());
        
        String[] arr = new String[numOfPlayers];
        Queue<Player> players = new LinkedList<>(); 
        HashMap<String, Player> mpaOfIdAndPlayer = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        
        System.out.print(arr.length);
        for(int i=0; i<numOfPlayers; i++){
            System.out.print("Enter Player name : ");
            String playerName = sc.nextLine();
            System.out.print("Enter "+playerName+" prioity number : ");
            int index = Integer.parseInt(sc.nextLine());
            arr[index-1]= playerName;
        }
        for(int i=0; i<numOfPlayers; i++){
            String id = getAlphaNumericString(8);
            while(set.contains(id)){
                id = getAlphaNumericString(8);
            }
            Player player = new Player(id, arr[i], 0);
            players.add(player);
            mpaOfIdAndPlayer.put(id, player);
        }
        Players AllPlayers = new Players(mpaOfIdAndPlayer, players);
        Jumps jumps =  new Jumps();
        Board board = new Board(0, 100, jumps);
        Dice dice = new Dice(1,6,1);
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders(board, AllPlayers, dice);
        Game game = new Game(snakesAndLadders);
        game.startGame();
    }
}
