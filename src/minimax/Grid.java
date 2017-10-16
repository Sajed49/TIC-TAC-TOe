package minimax;

public class Grid {
	
	public int value[][] = new int [3][3];
	public Grid childGrids[];
	public int decision = -1;
	
	public Grid() {
		
		//initialize();
	
	}
	
	public void initialize() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) value[i][j] =0;
		}
	}
	
	public void copyValues(Grid temp) {
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) value[i][j] = temp.value[i][j];
		}
	}
	
	public void initializeChildGrids(int total) {
		
		childGrids = new Grid [total];
		for(int i=0; i<total;i++) childGrids[i] = new Grid();
	}
	
	public int countEmptySpots() {
		
		int counter = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				
				if(value[i][j] == 0) counter++;
			}
		}
		
		return counter;
	}
	
	
	public void putValue(int loc, int id) {
		
		int counter = 0;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				
				if(value[i][j] == 0 && loc == counter) {
					value[i][j] = id;
					return;
				}
				else if(value[i][j] == 0 ) counter++;
			}
		}
	}
	
	
	public int findIfWon() {
		
		int temp = vertical();
		if(temp == 1 || temp == 2) return temp;
		
		temp = horizontal();
		if(temp == 1 || temp == 2) return temp;
		
		temp = diagonal();
		if(temp == 1 || temp == 2) return temp;
		
		if( draw() == true ) return 0;
		
		return -1;
	}
	
	public boolean draw() {
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(value[i][j] == 0) return false;
			}
		}
		
		return true;
	}
	
	public int vertical() {
		
		for(int i=0; i<3; i++) {
			
			if(value[0][i] == value[1][i] &&
					value[1][i] == value[2][i] && value[1][i] != 0 ) return value[1][i];
		}
		
		return -1;
	}
	
	public int horizontal() {
		
		for(int i=0; i<3; i++) {
			
			if(value[i][0] == value[i][1] &&
					value[i][1] == value[i][2] && value[i][0] != 0 ) return value[i][0];
		}
		
		return -1;
	}
	
	public int diagonal() {
		
		if(value[0][0] == value[1][1] && value[1][1] == value[2][2] &&
				value[0][0] != 0) return value[0][0];
		if(value[0][2] == value[1][1] && value[1][1] == value[2][0] &&
				value[1][1] != 0) return value[1][1];
		
		return -1;
	}
	
	public void putIntoGrid(int i, int j) {
		
		value[i][j] = 2;
	}
	
	protected void printValues() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) System.out.print(value[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
}
