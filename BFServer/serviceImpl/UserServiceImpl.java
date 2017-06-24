package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;

import service.UserService;

public class UserServiceImpl implements UserService{
	ArrayList<String> u_p_list = new ArrayList<String>();
	String online = "";

	@Override
	public boolean login(String username, String password) throws RemoteException {
        String readfile = "";
		
		File f = new File("username_password");
		try{
			if(f.exists() && f.isFile()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(f));
				BufferedReader br = new BufferedReader(read);
				String txt = "";
				//用户名密码文件中无数据情况
				if((txt = br.readLine()) == null){
					br.close();
					return false;
				}
					
				
				while((txt = br.readLine()) != null){
					readfile += txt;
				}
				read.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		String[] u_p_list = readfile.split("$");
		
		for(int i = 0;i<u_p_list.length;i++){
			if(username.equals(u_p_list[i].substring(0, username.length())) && password.equals(u_p_list[i].substring(username.length()))){
				online = username;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return false;
	}
	
	@Override
	public boolean register(String username, String password) throws RemoteException {
		System.out.println(username + " " + password);
		String readfile = "";
		File f = new File("username_password");
		//先读后写 判断用户名是否被注册过
		try{
			if(f.exists() && f.isFile()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(f));
				BufferedReader br = new BufferedReader(read);
				String txt = "";
				//一种独特的情况，username_password文件为空，需要重新保存
				if((txt = br.readLine()) == null){
					br.close();
					try {
						FileWriter fw = new FileWriter(f, true);
						fw.write("$" + username + "_" + password);
						fw.flush();
						fw.close();
						return true;
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				}
				
				while((txt = br.readLine()) != null){
					readfile += txt;
				}
				read.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
//		String[] u_p_list = readfile.split("$");
//		for(int i = 0;i<u_p_list.length;i++){
//			if(username.equals(u_p_list[i].substring(0, username.length()))){
//				//已被注册过
//				return false;
//			}
//		}
			
		try {
			FileWriter fw = new FileWriter(f, true);
			fw.write("$" + username + "_" + password);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}