package pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean;

public class OtherValue<T> implements IValue{

	protected T value;

	public OtherValue(T value){
		this.value = value;
	}
	
	@Override
	public T getValue() {
		return (T) value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
