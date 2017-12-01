
public class Vector2
{
    private int x;
    private int y;
    public int X() { return x; }
    public int Y() { return y; }


    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Vector2 point = (Vector2)obj;

        return (x ==point.x && y == point.y);
    }

    public Vector2 add(Vector2 vec)
    {
        return new Vector2(this.x + vec.x, this.y + vec.y);
    }

    public Vector2 sub(Vector2 vec)
    {
        return this.add((vec.mul(-1)));
    }

    public Vector2 mul(int num)
    {
        return new Vector2(x * num, y * num);
    }
    public String ToString()
    {
        return "(" +Integer.toString(x) + "," + Integer.toString(y) + ")";
    }

    public int magnitude()
    {
    	return (int)Math.sqrt(x *x + y *y); 
    }
}
