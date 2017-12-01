
public abstract class Chesspiece
{
private GameManager.Team myTeam;
public GameManager.Team getTeam() {return myTeam;}

private String myCode;
public String code() {return myCode;}

private Vector2 myPos;
public Vector2 pos() {return myPos;}

public abstract Vector2[] MovablePos();
}
