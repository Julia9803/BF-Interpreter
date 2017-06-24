package ui;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import rmi.RemoteHelper;

public class LoginController {
	@FXML
	public AnchorPane root;
	@FXML
	public TextField name;
	@FXML
	public PasswordField password;
	@FXML
	public Button login;
	@FXML
	public Button logout;
	@FXML
	public Label tip;
	
	public static String username;
	public String password1;
	//登入
	public void onLoginClicked(){
		boolean onConnect = false;
		this.username = name.getText();
		password1 = password.getText();
		try{
			onConnect = RemoteHelper.getInstance().getUserService().login(username, password1);
		}catch(RemoteException e){
			e.printStackTrace();
		}
		if(onConnect){
			Platform.runLater(()->{
				try {
					new MainFrame();
					root.getScene().getWindow().hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}else{
			tip.setText("您输入的用户名或密码错误。");
		}
	}
	//注册
	public void onRegisterClicked(){
		this.username = name.getText();
		password1 = password.getText();
		System.out.println("传参前: " + username + " " + password1);
		boolean register = false;
		try {
			register = RemoteHelper.getInstance().getUserService().register(username, password1);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		if(register){
			Platform.runLater(()->{
				try {
					new MainFrame();
					root.getScene().getWindow().hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}else{
			tip.setText("注册失败！");
		}
	}

}
