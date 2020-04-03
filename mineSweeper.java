import java.util.*;
public class mineSweeper{
	int rows=5;
	int cols=5;
	int[][] boardValue=new int[rows][cols];
	String[][] boardShown=new String[rows][cols];
	boolean bombBlast;
	
	HashMap<Integer,Integer> unTraversed=new HashMap<Integer,Integer>();
	public mineSweeper(){
		boardValue[0][3]=-1;
		
		boardValue[3][1]=-1;
		
		for(int i=0;i<boardValue.length;i++){
			for(int j=0;j<boardValue.length;j++){
				if(boardValue[i][j]!=-1){
					boardValue[i][j]=0;
				}
			}
		}
		
		for(int i=0;i<boardValue.length;i++){
			for(int j=0;j<boardValue.length;j++){
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
			for(int j=0;j<boardShown.length;j++){
				boardShown[i][j]=".";
			}
		}
		viewMineSweeper(boardShown);
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
	public int blankAdjacentCell(int i,int j){
			boardShown[i][j]="$";

		 	if (i < rows && j < cols && i >= 1 && j >= 1  ) {
               if(boardValue[i-1][j-1]!=0){
               		boardShown[i-1][j-1]=Integer.toString(boardValue[i-1][j-1]);
               }
               else{
               		boardShown[i-1][j-1]="$";
               		unTraversed.put(i-1,j-1);
               }

            }
          	if (i < rows && j < cols-1 && i >= 1 && j >= 0 ) {
               
                if(boardValue[i-1][j+1]!=0){
               		boardShown[i-1][j+1]=Integer.toString(boardValue[i-1][j+1]);
               }
               else{
               	boardShown[i-1][j+1]="$";
               		unTraversed.put(i-1,j+1);
               }
            }
            if (i < rows-1 && j < cols && i >= 0 && j >= 1  ) {
               	
               	if(boardValue[i+1][j-1]!=0){
               		boardShown[i+1][j-1]=Integer.toString(boardValue[i+1][j-1]);
               }
               else{
               	boardShown[i+1][j-1]="$";
               		unTraversed.put(i+1,j-1);
               }
            }
            if (i < rows-1 && j < cols-1 && i >= 0 && j >= 0  ) {
               
                if( boardValue[i+1][j+1]!=0){
               		boardShown[i+1][j+1]= Integer.toString(boardValue[i+1][j+1]);
               }
               else{
               	boardShown[i+1][j+1]="$";
               		unTraversed.put(i+1,j+1);
               }
            }
            if (i < rows && j < cols && i >= 1 && j >= 0) {
              
                if(boardValue[i-1][j]!=0){
               		boardShown[i-1][j]=Integer.toString(boardValue[i-1][j]);
               }
               else{
               	boardShown[i-1][j]="$";
               		unTraversed.put(i-1,j);
               }
       		 }
			if(i < rows && j < cols && i >= 0 && j >= 1) {
	            
	            if( boardValue[i][j-1]!=0){
               		boardShown[i][j-1]= Integer.toString(boardValue[i][j-1]);
               }
               else{
               	boardShown[i][j-1]="$";
               		unTraversed.put(i,j-1);
               }
	        }
	        if (i < rows && j < cols-1 && i >= 0 && j >= 0) {
	           
	            if(boardValue[i][j+1]!=0){
               		boardShown[i][j+1]=Integer.toString(boardValue[i][j+1]);
               }
               else{
               	boardShown[i][j+1]="$";
               		unTraversed.put(i,j+1);
               }
	        }

	        if (i < rows-1 && j < cols && i >= 0 && j >= 0) {
	           
	            if(boardValue[i+1][j]!=0){
               		boardShown[i+1][j]=Integer.toString(boardValue[i+1][j]);
               }
               else{
               	boardShown[i+1][j]="$";
               		unTraversed.put(i+1,j);
               }
	        }

	        System.out.println("unTraversed Size : "+unTraversed.size());

	        return unTraversed.size();
	}
	
	public void print(int[][] array){
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length;j++){
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void viewMineSweeper(String[][] array){
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array.length;j++){
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void checkCondition(int x,int y,String f){
		if(boardValue[x][y]!=-1 && boardValue[x][y]!=0 && f.equals("n") && boardShown[x][y]!="F"){
			boardShown[x][y]=Integer.toString(boardValue[x][y]);
			viewMineSweeper(boardShown);
			bombBlast=false;
			
		}
		else if(boardValue[x][y]==0 && f.equals("n") && boardShown[x][y]!="F"){
			int size=blankAdjacentCell(x,y);
			if(size>0){
				System.out.println(unTraversed);
				Iterator iter = unTraversed.entrySet().iterator();
		        while (iter.hasNext()) {
		            Map.Entry entry = (Map.Entry) iter.next();
		            int key=(int)entry.getKey();
		            int value=(int)entry.getValue();
		            size=blankAdjacentCell(key,value);

		        }
		        viewMineSweeper(boardShown);
		    }
			else{
				viewMineSweeper(boardShown);
				bombBlast=false;
			}
			
			
		}
		else if(boardShown[x][y]=="F" && f.equals("n")){
			   boardShown[x][y]=".";
				viewMineSweeper(boardShown);
				bombBlast=false;
		}
		else if(boardShown[x][y]=="." &&f.equals("y")){
			
				boardShown[x][y]="F";
				viewMineSweeper(boardShown);
				bombBlast=false;
			
		}
		else if(boardValue[x][y]==-1 && f.equals("n")){
			for(int i=0;i<boardValue.length;i++){
				for(int j=0;j<boardValue.length;j++){
					if(boardValue[i][j]==-1){
						boardShown[i][j]="*";
						
						
					}
				}
			}
			viewMineSweeper(boardShown);
			System.out.println("Game Over .....");
			bombBlast=true;
		}
		
	}
	public void playGame(){
		do{
			Scanner input=new Scanner(System.in);
			System.out.println("Enter the row");
			int x=input.nextInt();
			System.out.println("Enter the column");
			int y=input.nextInt();
			System.out.println("Do you want make this position as flag ? say(y/n)");
			String f=input.next();
			checkCondition(x,y,f);
		}while(bombBlast==false);
	}
}
class mineSweeperGame{
	public static void main(String arg[]){
		mineSweeper mine=new mineSweeper();
		mine.playGame();
	}
}

