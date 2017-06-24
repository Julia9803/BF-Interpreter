package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		String readfile = "";
		
		File f = new File(userId + "_" + fileName);
		try{
			if(f.exists() && f.isFile()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(f));
				BufferedReader br = new BufferedReader(read);
				String txt = "";
				while((txt = br.readLine()) != null){
					readfile += txt;
				}
				read.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return readfile;
	}

	@Override
	public String readFileList(String userId) {
		// TODO Auto-generated method stub
		
		return "OK";
	}
	
}
