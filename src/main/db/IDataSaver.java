package main.db;

public interface IDataSaver <K, V> {
	public void save(K identifier, V thing);
	public void delete(K identifier);
	public V[] search(String query);
	public V fetch(K identifier);
}