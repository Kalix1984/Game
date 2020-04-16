package application.entities;


import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Entity
{
    private Image image;
 
    private int positionX;
    private int positionY;    
    private double velocityX;
    private double velocityY;
    private int width;
    private int height;

    public Entity()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }
    
    public Entity(int width, int height, int posX, int posY)
    {
        this.width = width;
        this.height = height;
        this.positionX = posX;
        this.positionY = posY;
    }
    
	public void setImage(Image image)
    {
        this.image = image;
        width = (int) image.getWidth();
        height = (int) image.getHeight();
    }
    
    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(int x, int y)
    {
        positionX = x;
        positionY = y;
    }

    public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }
    
    public void update()
    {
    	positionX += velocityX;
//    	positionY += velocityY;
    }

    public void renderWithImage(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }
    
    public void renderWithRect(GraphicsContext gc, Color color)
    {
    	gc.setFill(color);
    	gc.setImageSmoothing(true);
        gc.fillRect(positionX,positionY,width,height);

    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Entity s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}

