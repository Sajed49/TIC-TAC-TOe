package minimax;

class Computer {
	
	protected int identity;
	private Grid maxNode = new Grid();
	
	public Computer(int identity) {
		this.identity = identity;
	}
	
	protected Grid giveTurn( Grid mainGrid) {
		
		Grid tempRoot = new Grid();
		tempRoot.copyValues(mainGrid);
		
		//int result = findMax(tempRoot, 0);
		//System.out.println(result);
		
		//maxNode.printValues();
		findMax(tempRoot, 0);
		mainGrid.copyValues(maxNode);
		return mainGrid;
	}
	
	
	
	protected int findMax( Grid currentGrid, int level) {
		
		int currentState = currentGrid.findIfWon();
		currentGrid.decision = currentState;
		
		if( currentState == identity) return 10;
		else if( currentState == 0) return 0;
		else if( currentState != -1) return -10;
		
		
		int possibleMoves = currentGrid.countEmptySpots();
		currentGrid.initializeChildGrids(possibleMoves);
		
		int maximumValue = -99999;
		//System.out.println(possibleMoves);
		//currentGrid.printValues();
		for(int i=0; i<possibleMoves; i++) {
			
			Grid tempGrid = new Grid();
			tempGrid.copyValues(currentGrid);
			tempGrid.putValue(i, identity );
			currentGrid.childGrids[i].copyValues(tempGrid);
			
			int tempValue = findMin(tempGrid, level+1);
			
			if(tempValue >= maximumValue) {
				maximumValue = tempValue;
				if(level == 0) {
					maxNode.copyValues(tempGrid); 
				}
			}
			//maximumValue = Math.max(maximumValue, findMin(tempGrid, level++));
		}
		
		return maximumValue;
		
	}
	
	protected int findMin(Grid currentGrid, int level) {
		//currentGrid.printValues();
		int currentState = currentGrid.findIfWon();
		currentGrid.decision = currentState;
		
		if( currentState == identity) return 10;
		else if( currentState == 0) return 0;
		else if( currentState != -1) return -10;
		
		
		int possibleMoves = currentGrid.countEmptySpots();
		currentGrid.initializeChildGrids(possibleMoves);
		//currentGrid.printValues();
		
		int minimumValue = 999999;
		
		for(int i=0; i<possibleMoves; i++) {
			
			Grid tempGrid = new Grid();
			tempGrid.copyValues(currentGrid);
			tempGrid.putValue(i, 2 );
			currentGrid.childGrids[i].copyValues(tempGrid);
			//tempGrid.printValues();
			minimumValue = Math.min(minimumValue, findMax(tempGrid, level+1));
		}
		
		return minimumValue;
	}
	
	
}
