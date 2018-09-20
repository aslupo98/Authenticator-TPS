package main.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultSaver<K, V> extends HashMap<K, V> implements IDataSaver<K, V>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8420709528332500105L;

	public DefaultSaver() {
		super();
	}

	@Override
	public void save(K identifier, V thing) {
		this.put(identifier, thing);
	}

	@Override
	public void delete(K identifier) {
		this.remove(identifier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V[] search(String query) {
		List<V> result = new ArrayList<V>();
		result.add(this.get(query));
		return (V[]) result.toArray();
	}

	@Override
	public V fetch(K identifier) {
		return this.get(identifier);
	}

}