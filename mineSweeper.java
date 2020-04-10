import java.util.Scanner;
import java.util.Random;
class MineSweeper{
	int rows,cols,mines,cellCount,cellCntExclBomb;
	int[][] boardValue;
	String[][] boardShown;
	boolean bombBlast;
	ArrayList<String> unTraversed=new ArrayList<String>();
	ArrayList<String> traversed=new ArrayList<String>();
	Scanner input=new Scanner(System.in);
	public mineSweeperWork(){
		System.out.println("Enter the no of rows : ");
		rows=input.nextInt();
		System.out.println("Enter the no of columns : ");
		cols=input.nextInt();
		System.out.println("Enter the no of mines for the given rows and columns : ");
		mines=input.nextInt();
		cellCount=rows*cols;
		cellCntExclBomb=cellCount-mines;
		boardValue=new int[rows][cols];
		boardShown=new String[rows][cols];
		
		for(int i=0;i<mines;i++)
        	{
		    Random randomBombs = new Random();
		     int r = randomBombs.nextInt(rows);
		    int c = randomBombs.nextInt(cols);
		    if(boardValue[r][c]!=-1)
		    {
			boardValue[r][c]=-1;
		    }
		    else
		    {
			if (r < rows && c < cols && r >= 1 && c >= 1 && boardValue[r-1][c-1]!=-1) {
			       boardValue[r-1][c-1]=-1;
			}
			else if (r < rows && c < cols-1 && r >= 1 && c >= 0 && boardValue[r-1][c+1]!=-1) {
				boardValue[r-1][c+1]=-1;
			 }
			 else if (r < rows-1 && c < cols && r >= 0 && c >= 1 && boardValue[r+1][c-1]!=-1 ) {
				boardValue[r+1][c-1]=-1;
			 }
			 else if (r < rows-1 && c < cols-1 && r >= 0 &&  c>= 0 && boardValue[r+1][c+1]!=-1 ) {
				boardValue[r+1][c+1]=-1;
			  }
			  else if (r < rows && c < cols && r >= 1 && c >= 0 &&  boardValue[r-1][c]!=-1) {
				boardValue[r-1][c]=-1;
			  }
			  else if(r < rows && c < cols && r >= 0 && c >= 1 && boardValue[r][c-1]!=-1) {
				boardValue[r][c-1]=-1;
			  }
			  else if (r < rows && c < cols-1 && r >= 0 && c >= 0 && boardValue[r][c+1]!=-1) {
				 boardValue[r][c+1]=-1;
			  }
			  else if (r < rows-1 && c < cols && r >= 0 && c >= 0 &&  boardValue[r+1][c]!=-1) {
				  boardValue[r+1][c]=-1;
		          }
            		}
        	}		
		for(int i=0;i<boardValue.length;i++){
			for(int j=0;j<boardValue[i].length;j++){
				if(boardValue[i][j]!=-1){
					boardValue[i][j]=0;
				}
			}
		}		
		for(int i=0;i<boardValue.length;i++){
			for(int j=0;j<boardValue[i].length;j++){
				if(boardValue[i][j]==-1){
					neighbour(i,j);
				}
				else{
					continue;
				}
			}
		}
		print(boardValue);
		for(int i=0;i<boardShown.length;i++){
			for(int j=0;j<boardShown[i].length;j++){
				boardShown[i][j]=".";
			}
		}		
		System.out.println();
		viewMineSweeper(boardShown,false);
	}
	public void neighbour(int i,int j){
	 	if (i < rows && j < cols && i >= 1 && j >= 1 && boardValue[i-1][j-1]!=-1) {
		   boardValue[i-1][j-1]=boardValue[i-1][j-1]+1;
		}
		if (i < rows && j < cols-1 && i >= 1 && j >= 0 && boardValue[i-1][j+1]!=-1) {
		    boardValue[i-1][j+1]=boardValue[i-1][j+1]+1;
		}
		if (i < rows-1 && j < cols && i >= 0 && j >= 1 && boardValue[i+1][j-1]!=-1 ) {
			boardValue[i+1][j-1]=boardValue[i+1][j-1]+1;
		}
		if (i < rows-1 && j < cols-1 && i >= 0 && j >= 0 && boardValue[i+1][j+1]!=-1 ) {
		    boardValue[i+1][j+1]=boardValue[i+1][j+1]+1;
		}
		if (i < rows && j < cols && i >= 1 && j >= 0 &&  boardValue[i-1][j]!=-1) {
		    boardValue[i-1][j]=boardValue[i-1][j]+1;
			}
			if(i < rows && j < cols && i >= 0 && j >= 1 && boardValue[i][j-1]!=-1) {
		    boardValue[i][j-1]=boardValue[i][j-1]+1;
		}
		if (i < rows && j < cols-1 && i >= 0 && j >= 0 && boardValue[i][j+1]!=-1) {
		    boardValue[i][j+1]=boardValue[i][j+1]+1;
		}
		if (i < rows-1 && j < cols && i >= 0 && j >= 0 &&  boardValue[i+1][j]!=-1) {
		    boardValue[i+1][j]=boardValue[i+1][j]+1;
		}
	}
	public void neighbourBlankCell(int x,int y){
	 	if(boardValue[x][y]!=0){
           		boardShown[x][y]=Integer.toString(boardValue[x][y]);
          	 }
         	  else{
           		boardShown[x][y]="$";
           		if(traversed.contains(x+","+y)==false && unTraversed.contains(x+","+y)==false){
           			unTraversed.add(x+","+y);
			}
           	}
	}
	public int blankAdjacentCell(int i,int j){
		traversed.add(i+","+j);			
		boardShown[i][j]="$";
	 	if (i < rows && j < cols && i >= 1 && j >= 1  ) {              
	 		neighbourBlankCell(i-1,j-1);
		}
		if (i < rows && j < cols-1 && i >= 1 && j >= 0 ) {
			neighbourBlankCell(i-1,j+1);
		}
		if (i < rows-1 && j < cols && i >= 0 && j >= 1  ) {
			neighbourBlankCell(i+1,j-1);
		}
		if (i < rows-1 && j < cols-1 && i >= 0 && j >= 0  ) {
			neighbourBlankCell(i+1,j+1);
		}
		if (i < rows && j < cols && i >= 1 && j >= 0) {
			neighbourBlankCell(i-1,j);
		}
		if(i < rows && j < cols && i >= 0 && j >= 1) {
			neighbourBlankCell(i,j-1);
		}
		if (i < rows && j < cols-1 && i >= 0 && j >= 0) {
			neighbourBlankCell(i,j+1);
		}
		if (i < rows-1 && j < cols && i >= 0 && j >= 0) {
			neighbourBlankCell(i+1,j);
		}
		    return unTraversed.size();
	}
	
	public void print(int[][] array){
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				System.out.print(array[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public void viewMineSweeper(String[][] array,boolean bombBlast){
		 for(int i=0;i<cols;i++){
                 	System.out.print("\t"+i);
            	}
            System.out.println("\n\n");
		for(int i=0;i<array.length;i++){
			System.out.print(i+"\t");
			for(int j=0;j<array[i].length;j++){
				System.out.print(array[i][j]+"\t");
			}
			System.out.println("\n\n");
		}
		winningCondition(array,bombBlast);
	}
	public void winningCondition(String[][] finalarr,boolean bombBlast){
		int openCell=0;
		for(int i=0;i<finalarr.length;i++){
			for(int j=0;j<finalarr[i].length;j++){
				if(boardShown[i][j]!="."){
					openCell+=1;
				}
			}
		}
		if(openCell==cellCntExclBomb && bombBlast==false){
			System.out.println("You wins !  ");
			System.exit(0);
		}
	}
	public void checkCondition(int x,int y,String f){
		bombBlast=false;
		if(boardValue[x][y]!=-1 && boardValue[x][y]!=0 && f.equals("n") && boardShown[x][y]!="F" && boardShown[x][y]=="."){
			boardShown[x][y]=Integer.toString(boardValue[x][y]);
					
		}
		else if(boardValue[x][y]!=-1  && (f.equals("n") || f.equals("y")) && boardShown[x][y]!="F" && boardShown[x][y]!="."){
			System.out.println("Already the given position is opened ! ...");
		}
		else if(boardValue[x][y]==0 && f.equals("n") && boardShown[x][y]!="F"){
			int size=blankAdjacentCell(x,y);
			while(size>0){
				for(int i=0;i<size;i++){
					String ar=unTraversed.get(i);
		           	String[] arra=ar.split(",");
		            unTraversed.remove(i);
		            size=blankAdjacentCell(Integer.parseInt(arra[0]),Integer.parseInt(arra[1]));
				}
		    }			
			
		}
		else if(boardShown[x][y]=="F" && f.equals("y")){
			boardShown[x][y]=".";
		}
		else if(boardShown[x][y]=="." &&f.equals("y")){			
			boardShown[x][y]="F";
							
		}
		else if(boardShown[x][y]=="F" &&f.equals("n")){			
			System.out.println("Already the given position has been flagged .");
		}
		else if(boardValue[x][y]==-1 && f.equals("n")){
			for(int i=0;i<boardValue.length;i++){
				for(int j=0;j<boardValue[i].length;j++){
					if(boardValue[i][j]==-1){
						boardShown[i][j]="B";
					}
				}
			}
			bombBlast=true;
			System.out.println("Game Over .....");
			
		}
		viewMineSweeper(boardShown,bombBlast);	
	}
	public void playGame(){		    
		do{
			System.out.println("Enter the row between 0 and "+rows);
			int x=input.nextInt();
			System.out.println("Enter the column between 0 and "+cols);
			int y=input.nextInt();
			System.out.println("Do you want make this position as flag ? say(y/n)");
			String f=input.next();
			if(x>=0 && x<rows && y>=0 && y<cols){
				checkCondition(x,y,f);
			}
			else{
				System.out.println("The given postion doesn't fall between the range");
			}
		}while(bombBlast==false);
	}
}
class MineSweeperDemo{
	public static void main(String arg[]){
		MineSweeper mine=new MineSweeper();
		mine.playGame();
	}
}
