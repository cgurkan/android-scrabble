package util;

import java.util.LinkedList;
import java.util.List;

public class ListenableList<E> extends LinkedList<E> {
	
	private static final long serialVersionUID = 4829748232963920754L;
	private List<ListListener> listeners = new LinkedList<ListListener>();
	
	public boolean add(E item){
		boolean value = super.add(item);

		for(ListListener ll : listeners){
			ll.listChanged();
		}
		return value;
	}
	
	public void addListListener(ListListener ll){
		listeners.add(ll);
	}
	
	public void removeListListener(ListListener ll){
		listeners.remove(ll);
	}
}
