package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import android.util.Log;

import util.SendObject;
import controller.ClientController;

public class ClientInput extends Thread implements Runnable{

	ClientController c = null;
	ObjectInputStream in = null;
	
	public ClientInput(ClientController c) {
		try {
			in = new ObjectInputStream(c.getSocket().getInputStream());
		} catch (StreamCorruptedException e) {
			Log.d("alpha", "Stream corrupted in input", e);
		} catch (IOException e) {
			Log.d("alpha", "IOE", e);
		}
	}
	
	public void run(){
		while(true){
			try {
				SendObject so = ((SendObject)(in.readUnshared()));
				c.redirect(so);
			}
			catch (IOException e) {
				Log.d("alpha", "IOE", e);
			} catch (ClassNotFoundException e) {
				Log.d("alpha", "Class not found", e);
			}
		}
	}
}
