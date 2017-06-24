//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Stack;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {
	
	public static final String one = "Ook. Ook?";
	public static final String two = "Ook? Ook.";
	public static final String three = "Ook. Ook.";
	public static final String four = "Ook! Ook!";
	public static final String five = "Ook! Ook.";
	public static final String six = "Ook. Ook!";
	public static final String seven = "Ook! Ook?";
	public static final String eight = "Ook? Ook!";
	
	String code;
	String param;
	int codePtr;
	int inputPtr;
	int memPtr;
	byte[] mem;
	private Stack<Integer> stack;
	String output;
	boolean wrongInput;
	
	//initialze BF
	private void init(String code, String param){
		this.code = code;
		this.param = param;
		codePtr = 0;
		inputPtr = 0;
		memPtr = 0;
		output = "";
		stack = new Stack<Integer>();
		mem = new byte[20];
		Arrays.fill(mem, (byte)0);
		wrongInput = false;
	}
	
	//initialze Ook!
		private void init1(String code, String param){
			this.code = code;
			this.param = param;
			codePtr = 0;
			inputPtr = 0;
			memPtr = 0;
			output = "";
			stack = new Stack<Integer>();
			mem = new byte[20];
			Arrays.fill(mem, (byte)0);
			
				String code1 = "";
				String[] list = code.split(" ");
				for(int i = 0;i<list.length-1;i=i+2){
					switch(list[i]+" "+list[i+1]){
					case one:
						code1 += ">";
						break;
					case two:
						code1 += "<";
						break;
					case three:
						code1 += "+";
						break;
					case four:
						code1 += "-";
						break;
					case five:
						code1 += ".";
						break;
					case six:
						code1 += ",";
						break;
					case seven:
						code1 += "[";
						break;
					case eight:
						code1 += ']';
						break;	
					}
				}
				this.code = code1;
				System.out.println("由Ook!翻译过来的BF" + this.code);
		}

		private void start(){
			// TODO Auto-generated method stub
	    	char a = code.charAt(codePtr);
	    	System.out.println("every char of code:" + a);
	    	
	    	if(a != '>' && a!= '<' && a != '+' && a != '-' && a != ',' && a != '.' && a != '[' && a != ']'){
	    		wrongInput = true;
	    		System.out.println("wrongInputAlert!!!");
	    	}
	    	
			switch (a){
			    case '>':
			    	memPtr++;
			    	break;
			    	
			    case '<':
			    	if(memPtr != 0){
			    		memPtr--;
			    	}
			    	break;
			    
			    case '+':
			    	mem[memPtr]++;
			    	break;
			    	
			    case '-':
			    	mem[memPtr]--;
			    	break;
			    	
			    case '.'://输出
			    	output += Character.toString((char)mem[memPtr]);
			    	System.out.println(output);
			    	break;
			    	
			    case ','://输入
			    	mem[memPtr] = (byte) param.charAt(inputPtr);
			    	inputPtr++;
			    	break;
			      
			    case '['://while(){
			    	if(mem[memPtr] != 0){
			    		stack.push(codePtr);//压栈
			    	}else{
			    		while(code.charAt(codePtr) != ']'){
			    			codePtr++;
			    		}
			    	}
			    	break;
			    	
			    case ']':
			    	if(mem[memPtr] != 0){
			    		codePtr = stack.peek();
			    	}else{
			    		stack.pop();
			    	}
			    	break;
			}
			codePtr++;
		}
		
		/**
		 * BF 解释
		 * 请实现该方法
		 * @throws Exception 
		 */
		@Override
		public String execute(String code, String param) throws RemoteException {
			// TODO Auto-generated method stub

				init(code,param);
				while(codePtr < this.code.length()){
					if(wrongInput){
						output = "ERROR!!!";
						break;
					}
				start();
				}
				System.out.println("output" + output);
				return output;
			}

		/**
		 * Ook!解释器
		 */
		@Override
		public String execute1(String code, String param) throws RemoteException, Exception {
			// TODO Auto-generated method stub
			init1(code,param);
			while(codePtr < this.code.length()){
				if(wrongInput){
					output = "ERROR!!!";
					break;
				}
			start();
			}
			System.out.println("output" + output);
			return output;
		}
}
