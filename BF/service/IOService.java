//服务器IOService的Stub，内容相同
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;
	
//	public class FileManager implements IOService{
//		public static String onScreen = "";
//
//		@Override
//		public boolean writeFile(String file, String userId, String fileName) throws RemoteException {
//			// TODO Auto-generated method stub
//			try {
//				BufferedWriter bw = new BufferedWriter(new FileWriter("service/file"));
//				bw.write("fileName,userId_file");
//				bw.flush();
//				bw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return false;
//		}
//
//		@Override
//		public String readFile(String userId, String fileName) throws RemoteException {
//			// TODO Auto-generated method stub
//			String readFile = "";
//			
//			try {
//				BufferedReader br = new BufferedReader(new FileReader("service/file"));
//				while((readFile = br.readLine()) != null){
//					if(readFile.substring(0, readFile.indexOf("_")).equals(fileName+","+userId)){
//						onScreen = readFile.substring(readFile.indexOf('_') + 1);
//					}
//				}
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		@Override
//		public String readFileList(String userId) throws RemoteException {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	}
}
