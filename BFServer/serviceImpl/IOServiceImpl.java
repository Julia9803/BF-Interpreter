package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
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
		String ret = "";
	        File dir = new File("/Users/julia98/Downloads/BFInterpreter/BFServer");
	        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
	        if (files != null) {
	            for (int i = 0; i < files.length; i++) {
	                String fileName = files[i].getName();
	                if (files[i].isFile()) { // 判断是文件还是文件夹
	                   if (fileName.startsWith(userId) && fileName.endsWith(".BF")) { // 判断文件名是否以userId开头
	                    System.out.println("---" + files[i].getName());
	                    
	                    ret+= files[i].getName().substring(files[i].getName().indexOf('_')+1,files[i].getName().indexOf('.')) + " ";
	                   }
	                } else 
	                    continue;
	            }

	        }
	        return ret;
	}
	
}
