package fourx.domain;

/**
 * Coordinates, in X, Y and Z dimension.
 */
public class Coordinates {
    
    public final int x;
    public final int y;
    public final int z;
    
    public Coordinates(int x, int y, int z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }
    
    public String toString() {
	return "[" + x + "," + y + "," + z + "]";
    }

}
