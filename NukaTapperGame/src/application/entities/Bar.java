package application.entities;


public class Bar extends Entity{
	private int endPointInX;
	
	public Bar(int width, int height, int posX, int posY){
		super(width, height, posX, posY);
		
		endPointInX = getEndPointInX(posX, width);
	}

	public int getEndPointInX() {
		return endPointInX;
	}

	private int getEndPointInX(int posX, int width) {
		
		return posX + width;
	}

}
