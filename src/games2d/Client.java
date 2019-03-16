package games2d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client extends Thread{
	
	private  Socket socket;
	private  String nom;
	public PrintWriter output=null;
	public BufferedReader input = null;
	private Serveur serv;
	public Client(Socket socket,Serveur serv) {
		super();
		this.serv=serv;
		this.socket = socket;
		
		try {
			this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.output=new PrintWriter(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.nom =input.readLine() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("client "+this.nom+" a connecte");
		
		
		}

	public void run(){
		while(true)
		{	System.out.println(this.nom+" att msg ..");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				String 	dstname = this.input.readLine();
				String 	msg = this.input.readLine();
				
				serv.sendMessage(this.nom, dstname, msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}

	public String getNom() {
		return nom;
	}

	
}
