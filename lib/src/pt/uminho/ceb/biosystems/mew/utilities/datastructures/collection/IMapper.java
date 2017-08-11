package pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection;

public interface IMapper<I,O> {

	public O map(I in);
	
}
