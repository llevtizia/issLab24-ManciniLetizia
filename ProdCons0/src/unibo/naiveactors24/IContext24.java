package unibo.naiveactors24;
import java.util.Vector;

public interface IContext24 {
	public String getName();
	public Vector<String> getLocalActorNames( );
	public ActorBasic24 getActor(String actorName); // restituisce un riferimento all'attore
	public void addActor( ActorBasic24 a );
	public void removeActor(ActorBasic24 a);
	public  void showActorNames( );

}
