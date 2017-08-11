package pt.uminho.ceb.biosystems.mew.utilities.datastructures.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class CollectionUtils {
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Takes a collection and returns all elements assembled in
	 * a {@link String} joined by the defined separator.
	 * <br>
	 * Example: Create a {@link String} using a {@link List<Integer>}
	 * separated by "\n":
	 * <br>
	 * <code>
	 * List<Integer> list = new ArrayList<Integer>();
	 * <br>
	 * list.add(1);
	 * <br>
	 * list.add(2);
	 * <br>
	 * list.add(3);
	 * <br>
	 * String output = CollectionUtils.join(list, "\n");
	 * <br>
	 * </code>
	 * 
	 * @param collection a {@link Collection} of objects to join.
	 * @param separator the {@link String} separator used to join the collection. 
	 * @return {@link String} joined string.
	 */
	public static String join(Collection<?> collection, String separator) {
		String output = "";
		if(collection!=null){
			Iterator<?> iterator = collection.iterator();
			while(iterator.hasNext()) {
				Object o = iterator.next();
				output += o;
				if(iterator.hasNext()) output += separator;
			}
		}
		return output;
	}
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Takes an array of Objects and returns all elements assembled in
	 * a {@link String} joined by the defined separator.
	 * <br>
	 * Example: Create a {@link String} using an {@link Integer[]}
	 * separated by "\n":
	 * <br>
	 * <code>
	 * Integer[] array = {1, 2, 3};
	 * <br>
	 * String output = CollectionUtils.join(array, "\n");
	 * <br>
	 * </code>
	 * 
	 * @param collection a {@link Collection} of objects to join.
	 * @param separator the {@link String} separator used to join the collection. 
	 * @return {@link String} joined string.
	 */
	public static String join(Object[] collection, String separator) {
		String output = "";
		if(collection !=null){
			for (int i = 0; i < collection.length-1; i++) {
				Object o = collection[i];
				output += o;
				output += separator;
			}
			if(collection.length > 0)
				output += collection[collection.length-1];
		}
		return output;
	}
	
	/*
	 * author Joao Cardoso
	 */
	public static <T> Set<T> getIntersectionValues(Collection<T> colection1, Collection<T> colection2){
		
		Set<T> ret = new HashSet<T>();
		if(colection1 == null || colection2 == null) return ret;
		
		for(T value : colection1){
			if(colection2.contains(value))
				ret.add(value);
		}
		return ret;
	}
	
	public static <T> Set<T> getSortedIntersectionValues(Collection<T> colection1, Collection<T> colection2){
		Set<T> set = new TreeSet<>();
		set.addAll(getIntersectionValues(colection1, colection2));
		return set;
	}
	
	
	/*
	 * author Joao Cardoso
	 */
	public static <T> Set<T> getSetDiferenceValues(Collection<T> colection1, Collection<T> colection2){
		
		Set<T> ret = new HashSet<T>();
		
		for(T value : colection1){
			if(!colection2.contains(value))
				ret.add(value);
		}
		
		return ret;
	}
	
	/*
	 * author Joao Cardoso
	 */
	/** 
	 * Splits an given {@link String} using a defined delimiter.
	 * <br>
	 * Example:
	 * <br>
	 * <code>
	 * String input = "1, 2, 3, 4, 5, foo, bar";
	 * <br>
	 * Collection<String> output = CollectionUtils.parseSeparatedString(input, ", ");
	 * </code>
	 * <br>
	 * @param input the {@link String} to split.
	 * @param delim the {@link String} regular expression used to split the input.
	 * @return {@link Collection<String>} with the elements separated.
	 */
	public static List<String> parseSeparatedString(String input, String delim){
		List<String> output = new ArrayList<String>();
		
		String[] data = input.split(delim, -1);
		
		for(String d : data){
			output.add(d.trim());
		}
		
		return output;
	}
	
	public static Set<String> parseSeparetedStringSet(String inputString, String delim){
		Set<String> ret = new TreeSet<String>();
		
		String[] data = inputString.split(delim);
		
		for(String d : data){
			ret.add(d.trim());
		}
		
		return ret;
	}
	
	
	/*
	 * FIXME: There is a MapUtils! Move me into there, because Map is not a collection!!
	 */
	public static <K, V> void addToMap(K key, V value, Map<K, Set<V>> map) {

		if(key != null && value!=null)
			if (map.containsKey(key))
				map.get(key).add(value);
			else {
				Set<V> values = new TreeSet<V>();
				values.add(value);
				map.put(key, values);
			}
	}
	
	
	
	public static <T> Set<T> getReunionValues(Collection<T> collectionA, Collection<T> collectionB) {
		Set<T> newSet = new HashSet<T>(collectionA);
		newSet.addAll(collectionB);
		return newSet;
	}
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Takes an array of booleans and returns all elements assembled in
	 * a {@link String} joined by the defined separator.
	 * <br>
	 * Example: Create a {@link String} using an {@link boolean[]}
	 * separated by ", ":
	 * <br>
	 * <code>
	 * boolean[] array = {true, true, false};
	 * <br>
	 * String output = CollectionUtils.join(array, ", ");
	 * <br>
	 * </code>
	 * 
	 * @param collection a {@link boolean[]} of objects to join.
	 * @param separator the {@link String} separator used to join the collection. 
	 * @return {@link String} joined string.
	 */
	public static String join(boolean[] collection, String separator) {
		String output = "";
		for (int i = 0; i < collection.length-1; i++) {
			String o = Boolean.toString(collection[i]);
			output += o + separator;
		}
		if(collection.length > 0)
			output += Boolean.toString(collection[collection.length-1]);
		return output;
	}
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Takes an array of chars and returns all elements assembled in
	 * a {@link String} joined by the defined separator.
	 * <br>
	 * Example: Create a {@link String} using an {@link char[]}
	 * separated by ", ":
	 * <br>
	 * <code>
	 * boolean[] array = {true, true, false};
	 * <br>
	 * String output = CollectionUtils.join(array, ", ");
	 * <br>
	 * </code>
	 * 
	 * @param collection a {@link boolean[]} of objects to join.
	 * @param separator the {@link String} separator used to join the collection. 
	 * @return {@link String} joined string.
	 */
	public static String join(char[] collection, String separator) {
		String output = "";
		for (int i = 0; i < collection.length-1; i++) {
			String o = Character.toString(collection[i]);
			output += o + separator;
		}
		if(collection.length > 0)
			output += Character.toString(collection[collection.length-1]);
		return output;
	}
	
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Takes an array of doubles and returns all elements assembled in
	 * a {@link String} joined by the defined separator.
	 * <br>
	 * Example: Create a {@link String} using an {@link double[]}
	 * separated by ", ":
	 * <br>
	 * <code>
	 * double[] array = {1.0, 2.0, 3.0};
	 * <br>
	 * String output = CollectionUtils.join(array, ", ");
	 * <br>
	 * </code>
	 * 
	 * @param collection a {@link double[]} of objects to join.
	 * @param separator the {@link String} separator used to join the collection. 
	 * @return {@link String} joined string.
	 */
	public static String join(double[] collection, String separator) {
		String output = "";
		for(int i = 0; i < collection.length-1; i++) {
			String o = Double.toString(collection[i]);
			output += o + separator;
		}
		if(collection.length > 0)
			output += Double.toString(collection[collection.length-1]);
		return output;
		
	}
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Takes an array of primitive integer and returns all elements assembled in
	 * a {@link String} joined by the defined separator.
	 * <br>
	 * Example: Create a {@link String} using an {@link int[]}
	 * separated by ", ":
	 * <br>
	 * <code>
	 * int[] array = {1, 2, 3};
	 * <br>
	 * String output = CollectionUtils.join(array, ", ");
	 * <br>
	 * </code>
	 * 
	 * @param collection a {@link int[]} of objects to join.
	 * @param separator the {@link String} separator used to join the collection. 
	 * @return {@link String} joined string.
	 */
	public static String join(int[] collection, String separator) {
		String output = "";
		for(int i = 0; i < collection.length-1; i++) {
			String o = Integer.toString(collection[i]);
			output += o + separator;
		}
		if(collection.length > 0)
			output += Integer.toString(collection[collection.length-1]);
		return output;
	}

	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Generic reduce function for high-level programming. It takes a IReducer<I,O>
	 * where I is the input class and O the output. It takes the first element of the same class
	 * of the output for the first reduce action.
	 * <br>
	 * Example, sum a list of Integers:
	 * <br>
	 * <code>
	 * List<Integer> list = new ArrayList<Integer>();
	 * <br>
	 * list.add(1);
	 * <br>
	 * list.add(2);
	 * <br>
	 * list.add(3);
	 * <br>
	 * Integer sum = CollectionUtils.reduce(0, list, new IReducer<Integer, Integer>() {
	 * <br>
	 * 	 public Integer reduce(Integer mem, Integer input) {
	 * <br>
	 * 	 	return mem + input;
	 * <br>
	 * 	 }
	 * <br>
	 * });
	 * <br>
	 * </code>
	 * 
	 * @param first the base Object for output.
	 * @param collection a collection to reduce
	 * @param reducer an implementation of {@link IReducer}.
	 * @return The output value of the reducer.
	 * @throws Exception 
	 */
	public static <I,O> O reduce(O first, Collection<I> collection, IReducer<I,O> reducer) throws Exception {
		O mem = first;
		for(I element : collection) {
			mem = reducer.reduce(mem, element);
		}
		return mem;
	}
	
	/*
	 * author Joao Cardoso
	 */
	/**
	 * Generic reduce function for high-level programming. It takes a IReducer<E,E>
	 * where I is the input class and O the output. The first element of the collection is
	 * used as the first element. The reducer must return the same type as the collection elements.
	 * <br>
	 * Example, sum a list of Integers:
	 * <br>
	 * <code>
	 * List<Integer> list = new ArrayList<Integer>();
	 * <br>
	 * list.add(1);
	 * <br>
	 * list.add(2);
	 * <br>
	 * list.add(3);
	 * <br>
	 * Integer sum = CollectionUtils.reduce(list, new IReducer<Integer, Integer>() {
	 * <br>
	 * 	 public Integer reduce(Integer mem, Integer input) {
	 * <br>
	 * 	 	return mem + input;
	 * <br>
	 * 	 }
	 * <br>
	 * });
	 * <br>
	 * </code>
	 * 
	 * @param collection a collection to reduce
	 * @param reducer an implementation of IReducer.
	 * @return The output value of the reducer.
	 * @throws Exception 
	 */
	public static <E> E reduce(Collection<E> collection, IReducer<E, E> reducer) {
		Queue<E> queue = new LinkedList<E>(collection);
		E mem = queue.poll();
		while(!queue.isEmpty()) {
			E element = queue.poll();
			mem = reducer.reduce(mem, element);
		}
		return mem;
	}
	
	public static <I,O> Collection<O> map(Collection<I> collection, IMapper<I, O> mapper) {
		Collection<O> output = new ArrayList<O>();
		for(I in : collection) {
			output.add(mapper.map(in));
		}
		return output;
	}

	public static <I,O> Collection<O> map(I[] array, IMapper<I, O> mapper) {
		Collection<O> output = new ArrayList<O>();
		for(I in : array) {
			output.add(mapper.map(in));
		}
		return output;
	}
	
	public static <T, C> Collection<T> sortBy(Collection<T> collection, final Map<T, C> valuesMap) {
		List<T> ordered = new ArrayList<T>(collection);
		Comparator<T> compartor = new Comparator<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(T a, T b) {
				C aValue = valuesMap.get(a);
				C bValue = valuesMap.get(b);
				return ((Comparable<C>) aValue).compareTo(bValue);
			}
		};
		
		Collections.sort(ordered, compartor);
		
		return ordered;
	}
	
	public static <T> Collection<T> select(Collection<T> collection, ISelector<T> selector) {
		Collection<T> filtered = new ArrayList<T>();
		for(T element : collection) {
			if(selector.approve(element))
				filtered.add(element);
		}
		return filtered;
		
	}
	
	
	public static <T> ArrayList<T> convertPrimitiveArrayToList(T[] data){
		
		ArrayList<T> ret = new ArrayList<T>();
		
		for(int i =0; i < data.length ; i++){
			ret.add(data[i]);
		}
		
		return ret;
	}

	public static Set<String> split(String string, String separator) {
		Set<String> set = new HashSet<String>();
		String[] split = string.split(separator);
		for(String v : split)
			set.add(v.trim());
		
		return set;
		
	}

	public static <T> Set<T> aggregate(Collection<? extends Collection<T>> values) {
		Set<T> ret = new HashSet<T>();
		aggregate(values, ret);
		
		return ret;
	}
	
	public static <T> void aggregate(Collection<? extends Collection<T>> values, Collection<T> toAdd) {
		for(Collection<T> v : values)
			if(v!=null) toAdd.addAll(v);
	}

	public static <T extends Object> T getArrayValue(T[] data, int i) {
		T value = null;
		if(i < data.length)
			value=data[i];		
		return value;
	}


}
