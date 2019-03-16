package games2d;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;



public class Serveur extends ServerSocket{
	private Lock lock;
	private ArrayList<Client> listClients; 
	public Serveur(int port) throws IOException {
		super(port);
		this.listClients=new ArrayList<Client>();
		// TODO Auto-generated constructor stub
	}
	public void demarer()
	{int i=0;
		while(true)
		{	
			
			try {
				System.out.println("attente d'une connexion ...");
				listClients.add(new Client(accept(),this));
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listClients.get(i).start();
			i++;
		}
	}
	public void sendMessage(String srcname,String dstname,String msg)
	{
		System.out.println(dstname+" "+msg);
		
		for(int i=0;i<this.listClients.size();i++)
		{
			System.out.println(this.listClients.get(i).getNom()+" "+dstname);
			if((this.listClients.get(i).getNom()).equals(dstname)) {
				
				this.listClients.get(i).output.println(srcname+" : "+msg);
				this.listClients.get(i).output.flush();
				break;
				}
		}
	}
	
}
