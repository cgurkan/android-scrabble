package util;

import java.io.Serializable;

public class SendObject implements Serializable {

	private static final long serialVersionUID = 8158559495390915513L;
	private SendableAction action;
	private Object object = null;
	private String name = null;
	
	public SendObject(SendableAction a, Object o, String n){
		action = a;
		object = o;
		name = n;
	}
	
	public SendableAction getAction() {
		return action;
	}
	
	public Object getObject() {
		return object;
	}
	
	public String getName(){
		return name;
	}
}