package pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection;

public interface IReducer<I,O> {

	public O reduce(O ret, I element);
}
