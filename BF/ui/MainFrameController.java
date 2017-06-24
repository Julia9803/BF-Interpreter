package ui;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import rmi.RemoteHelper;

public class MainFrameController {
	@FXML
	public AnchorPane root;
	@FXML 
	public ImageView bg;
	@FXML
	public MenuBar menubar;
	@FXML
	public MenuItem newone;
	@FXML
	public MenuItem save;
	@FXML
	public MenuItem exit;
	@FXML
	public MenuItem execute;
	@FXML
	public MenuItem execute1;
	@FXML
	public MenuItem blank1;
	@FXML
	public MenuItem blank2;
	@FXML
	public MenuItem blank3;
	@FXML
	public MenuItem blank4;
	@FXML
	public MenuItem BFdo;
	@FXML
	public MenuItem BFundo;
	@FXML
	public MenuItem Ookdo;
	@FXML
	public MenuItem Ookundo;
	@FXML
	public MenuItem file1;
	@FXML
	public MenuItem file2;
	@FXML
	public MenuItem file3;
	@FXML
	public ImageView login;
	@FXML
	public TextArea textarea;
	@FXML
	public TextArea textarea2;
	@FXML
	public TextArea dataText;
	@FXML
	public Label output;
	@FXML
	public Label tip;
	@FXML
	public Pane fileNamePane;
	@FXML
	public TextField fileName;
	
	public String newFileName;
	String username = "";
	int BFtxtHistoryPtr = 0;
	int OoktxtHistoryPtr = 0;
	
	ArrayList<String> BFtxtHistory = new ArrayList<String>();
	ArrayList<String> OoktxtHistory = new ArrayList<String>();
	ArrayList<String> BFVersion = new ArrayList<String>();
	ArrayList<String> OokVersion = new ArrayList<String>();
	ArrayList<String> paramVersion = new ArrayList<String>();
	
	
	public static String input = "";
	
	@FXML
	void initialize(){
		this.username = LoginController.username;
		tip.setText(username);//用户名
		textarea.setDisable(true);
		textarea2.setDisable(true);
		dataText.setDisable(true);
		
		BFtxtHistory = new ArrayList<String>();
		OoktxtHistory = new ArrayList<String>();
		BFVersion = new ArrayList<String>();
		OokVersion = new ArrayList<String>();
		paramVersion = new ArrayList<String>();
	}
	
	public void onLoginClicked(){
		Platform.runLater(()->{
			try {
				new LoginWin();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getScene().getWindow().hide();
	}
	
	public void onExitClicked(){
		root.getScene().getWindow().hide();
	}
	
	public void onSaveClicked(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		boolean writeSucc1 = false;
		boolean writeSucc2 = false;
		String codeBF = textarea.getText();
		String codeOok = textarea2.getText();

		try {
			writeSucc1 = RemoteHelper.getInstance().getIOService().writeFile(codeBF, username, newFileName+".BF");
			writeSucc2 = RemoteHelper.getInstance().getIOService().writeFile(codeOok, username, newFileName+".Ook");
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		if(writeSucc1 && writeSucc2){
			System.out.println("正确创建文件存档");
		}else{
			System.out.println("根本没有正确创建文件存档");
		}
		//设置历史版本
		if(blank1.getText().equals("Blank1")){
			blank1.setText(df.format(new Date()));
			BFVersion.add(codeBF);
			OokVersion.add(codeOok);
			paramVersion.add(dataText.getText());
		}else
			if(blank2.getText().equals("Blank2")){
				blank2.setText(df.format(new Date()));
				BFVersion.add(codeBF);
				OokVersion.add(codeOok);
				paramVersion.add(dataText.getText());
			}else
				if(blank3.getText().equals("Blank3")){
					blank3.setText(df.format(new Date()));
					BFVersion.add(codeBF);
					OokVersion.add(codeOok);
					paramVersion.add(dataText.getText());
				}else
					if(blank4.getText().equals("Blank4")){
						blank4.setText(df.format(new Date()));
						BFVersion.add(codeBF);
						OokVersion.add(codeOok);
						paramVersion.add(dataText.getText());
					}else{//溢出的历史版本默认放第一个位置 这里有bug
						blank1.setText(df.format(new Date()));
						BFVersion.add(codeBF);
						OokVersion.add(codeOok);
						paramVersion.add(dataText.getText());
					}
		System.out.println(BFVersion);
		System.out.println(OokVersion);
		System.out.println(paramVersion);
	}
	/**
	 * BF 解释
	 * @throws Exception
	 */
	public void onExecuteClicked() throws Exception{
		String result = "";
		String code = textarea.getText();
		String param = dataText.getText();
		
		try {
			result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
		} catch (RemoteException e1) {
			output.setText("ERROR!!!");
			e1.printStackTrace();
		}
		
		if(result != ""){
			output.setText(result);
		}else{
			output.setText("ERROR!!!");
		}
		
	}
	/**
	 * Ook! 解释
	 * @throws Exception
	 */
	public void onExecute1Clicked() throws Exception{
		System.out.println("Execute1 clicked!");
		String result = "";
		String code = textarea2.getText();
		String param = dataText.getText();
		
		try {
			result = RemoteHelper.getInstance().getExecuteService().execute1(code, param);
		} catch (RemoteException e1) {
			output.setText("ERROR!!!");
			e1.printStackTrace();
		}

		if(result != ""){
			output.setText(result);
		}else{
			output.setText("ERROR!!!");
		}
	}
	@FXML
	public void onBlank1Clicked(){
		if(!blank1.getText().equals("Blank1")){
			textarea.setText(BFVersion.get(0));
			textarea2.setText(OokVersion.get(0));
			dataText.setText(paramVersion.get(0));
		}
	}
	@FXML
	public void onBlank2Clicked(){
		if(!blank2.getText().equals("Blank2")){
			textarea.setText(BFVersion.get(1));
			textarea2.setText(OokVersion.get(1));
			dataText.setText(paramVersion.get(1));
		}
	}
	@FXML
	public void onBlank3Clicked(){
		if(!blank3.getText().equals("Blank3")){
			textarea.setText(BFVersion.get(2));
			textarea2.setText(OokVersion.get(2));
			dataText.setText(paramVersion.get(2));
		}
	}
	@FXML
	public void onBlank4Clicked(){
		if(!blank4.getText().equals("Blank4")){
			textarea.setText(BFVersion.get(3));
			textarea2.setText(OokVersion.get(3));
			dataText.setText(paramVersion.get(3));
		}
	}

	public void onFile1Clicked(){
		String readFileBF = null;
		String readFileOok = null;
		if(!file1.getText().equals("file1")){
			try {
			readFileBF = RemoteHelper.getInstance().getIOService().readFile(this.username, file1.getText()+".BF");
			readFileOok = RemoteHelper.getInstance().getIOService().readFile(this.username, file1.getText()+".Ook");
			textarea.setText(readFileBF);
			textarea2.setText(readFileOok);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
    public void onFile2Clicked(){
    	String readFileBF = null;
		String readFileOok = null;
    	if(!file2.getText().equals("file2")){
			try {
			readFileBF = RemoteHelper.getInstance().getIOService().readFile(this.username, file2.getText()+".BF");
			readFileOok = RemoteHelper.getInstance().getIOService().readFile(this.username, file2.getText()+".Ook");
			textarea.setText(readFileBF);
			textarea2.setText(readFileOok);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
    public void onFile3Clicked(){
    	String readFileBF = null;
		String readFileOok = null;
    	if(!file3.getText().equals("file3")){
			try {
			readFileBF = RemoteHelper.getInstance().getIOService().readFile(this.username, file3.getText()+".BF");
			readFileOok = RemoteHelper.getInstance().getIOService().readFile(this.username, file3.getText()+".Ook");
			textarea.setText(readFileBF);
			textarea2.setText(readFileOok);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void onNewClicked(){
		fileName.clear();
		fileNamePane.setVisible(true);
	}
	
	public void onFileNamePaneClicked(){
		if(!fileName.getText().equals(null)){
			this.newFileName = fileName.getText();
			tip.setText(username + "-" + newFileName);
			fileNamePane.setVisible(false);
			textarea.setText("");
			textarea2.setText("");
			dataText.setText("");
			textarea.setDisable(false);
			textarea2.setDisable(false);
			dataText.setDisable(false);
			
			//设置文件存档
			if(file1.getText().equals("file1")){
				file1.setText(newFileName);
			}else
				if(file2.getText().equals("file2")){
					file2.setText(newFileName);
				}else
				if(file3.getText().equals("file3")){
					file3.setText(newFileName);
				}else{
					file1.setText(newFileName);
				}
		}
	}
	
	public void onBFdoClicked(){
		if((BFtxtHistoryPtr == 0) || (BFtxtHistoryPtr == BFtxtHistory.size()-1)){
			//根本还没撤销，重做什么啊
		}else{
			BFtxtHistoryPtr++;
			textarea.setText(BFtxtHistory.get(BFtxtHistoryPtr));
		}
	}
	
	public void onBFundoClicked(){
		if(BFtxtHistoryPtr == 0 && !textarea.getText().equals(null)){
			BFtxtHistoryPtr = BFtxtHistory.size()-1;
			BFtxtHistoryPtr--;
			textarea.setText(BFtxtHistory.get(BFtxtHistoryPtr));
		}else{
			BFtxtHistoryPtr--;
			textarea.setText(BFtxtHistory.get(BFtxtHistoryPtr));
		}
	}
	
	public void onOokDoClicked(){
		if((OoktxtHistoryPtr == 0) || (OoktxtHistoryPtr == OoktxtHistory.size()-1)){
			//根本还没撤销，重做什么啊
		}else{
			OoktxtHistoryPtr++;
			textarea2.setText(OoktxtHistory.get(OoktxtHistoryPtr));
		}
	}
	
	public void onOokUndoClicked(){
		if(OoktxtHistoryPtr == 0 && !textarea.getText().equals(null)){
			OoktxtHistoryPtr = OoktxtHistory.size()-1;
			OoktxtHistoryPtr--;
			textarea2.setText(OoktxtHistory.get(OoktxtHistoryPtr));
		}else{
			OoktxtHistoryPtr--;
			textarea2.setText(OoktxtHistory.get(OoktxtHistoryPtr));
		}
	}
	
	public void onBFtxtChanged(){
		BFtxtHistory.add(textarea.getText());
	}
	
	public void onOoktxtChanged(){
		OoktxtHistory.add(textarea2.getText());
	}
}