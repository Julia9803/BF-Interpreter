package runner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteHelper;
import ui.LoginWin;
import ui.MainFrame;
import ui.MainFrame1;

public class ClientRunner extends Application{
	private RemoteHelper remoteHelper;
	
	public ClientRunner(){
		linkToServer();
		//initGUI();
	}
	
	private void linkToServer() {
		try {
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8887/DataRemoteObject"));
			System.out.println("linked");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
//	private void initGUI(){
//		MainFrame1 mainFrame = new MainFrame1();
//	}
	
	public void test(){
		try {
			System.out.println(remoteHelper.getUserService().login("admin", "123456a"));
			System.out.println(remoteHelper.getIOService().writeFile("2", "admin", "testFile"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		//ClientRunner cr = new ClientRunner();
	    launch();
		//cr.test();
	}

	@Override
	public void start(Stage stage) throws IOException{
		// TODO Auto-generated method stub
		new LoginWin();
	}
}
