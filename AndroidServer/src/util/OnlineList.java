package util;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class OnlineList {

	private static OnlineList list = null;
	private List<NamedConnection> connections;
	
	private OnlineList(){
		connections = new ArrayList<NamedConnection>();
	}
	
	public static OnlineList getInstance(){
		if(list == null){
			list = new OnlineList();
		}
		return list;
	}
	
	public void add(NamedConnection nc){
		connections.add(nc);
	}
	
	public void remove(String name, Socket s){
		ListIterator<NamedConnection> it = connections.listIterator();
		while(it.hasNext()){
			NamedConnection nc = it.next();
			if(nc.getSocket().equals(s) && nc.getName().equals(name)){
				it.remove();
				break;
			}
		}
	}
	
	public void setName(String name, Socket s){
		for(NamedConnection nc : connections){
			if(nc.getSocket().equals(s)){
				nc.setName(name);
				break;
			}
		}
	}
}