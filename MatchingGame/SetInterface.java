package MatchingGame;

public interface SetInterface<E>
{
	public boolean add(E o);
	public void clear();
	public boolean contains(E o);
	public boolean isEmpty();
	public boolean remove(E o);
	public int size();
	public Object[] toArray();
}