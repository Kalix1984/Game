package application.entities;


public class Bar extends Entity{
	private OnBar name;
	private int endPointInX;
	
	public Bar(int width, int height, int posX, int posY){
		
		
		endPointInX = calculateEndPointInX(posX, width);
	}

	public int getEndPointInX() {
		return endPointInX;
	}

	private int calculateEndPointInX(int posX, int width) {
		
		return posX + width;
	}



}
