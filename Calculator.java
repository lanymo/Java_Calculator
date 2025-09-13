package assign.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;

public class Calculator extends Frame {
	
	/*계산 결과 및 연산을 위한 변수 선언부*/
	double num, res; //입력 받은 숫자와 결과값을 저장하기 위한 변수
	boolean isClear; //초기화 시켜야 하는지 판단하는 변수
	double pnum1, pnum2; //power에서 두 개의 변수를 담기 위해 선언
	boolean isFloat; //float인지 확인하기 위한 변수
	int dotloc; //소수점 위치를 저장하기 위한 변수
	boolean isPa; //괄호가 사용되었는지 확인하기 위한 변수
	
	/*계산기 모양 구현을 위한 버튼 및 패널 선언*/
	public static StringBuilder postfix = new StringBuilder();
	//숫자 버튼
	private Button bt7,bt8,bt9,bt4,bt5,bt6,bt1,bt2,bt3,bt0;
	//Operator Button
	private Button btfact, btper, btAC, btln, btlog, btsqrt, 
	   btpower,btplus,btminus,btmult,btdiv,bteq,
	   btdot,btlpa,btripa;
	
	//Panel
	private Panel pn;
	
	//text filed
	private JTextField tf;
	
	/*현재 상태를 체크하기 위한 변수*/
	final int NUMBER = 0; //숫자가 입력된 상태
	final int OPERATOR = 1; //operator가 입력된 상태
	final int EQUAL = 2; //등호가 입력된 상태
	final int FLOAT = 3; //float은 따로 처리하기 위해 상태 분리(예정)
	final int POWER = 4; //power처리를 위한 상태
	final int CAL = 5; //사칙 연산 case
	final int ERROR = 6; //0으로 나눈 경우 처리
	int statement; //현재 상태를 나타내기 위한 변수
	
	/*method 구성*/
	//constructor
	public Calculator(String str) {
		setTitle(str);
		setLocation(300,300);
		setLayout(new BorderLayout(10,10));
		Generator();
		Butt();
	}
	
	//계산기 모양을 구현하기 위한 method
	public void Generator() {
		//Generate textField
		 tf = new JTextField("0", 10);
		 tf.setHorizontalAlignment(JTextField.RIGHT);
		 
		//Generate Button_num
		 bt0 = new Button("0");
		 bt1 = new Button("1");
		 bt2 = new Button("2");
		 bt3 = new Button("3");
		 bt4 = new Button("4");
		 bt5 = new Button("5");
		 bt6 = new Button("6");
		 bt7 = new Button("7");
		 bt8 = new Button("8");
		 bt9 = new Button("9");
		 //Generate Operator Button
		 btfact = new Button("x!");
		 btper = new Button("%");
		 btAC = new Button("AC");
		 btln = new Button("ln");
		 btlog = new Button("log");
		 btsqrt = new Button("√ ");
		 btpower = new Button("x^y");
		 btplus = new Button("+");
		 btminus = new Button("-");
		 btmult = new Button("*");
		 btdiv = new Button("/");
		 bteq = new Button("=");
		 btdot = new Button(".");
		 btlpa = new Button("(");
		 btripa = new Button(")");
		 //Operation Button 색상 변경
		 btfact.setBackground(Color.GRAY.darker().brighter().brighter());
		 btper.setBackground(Color.GRAY.darker().brighter().brighter());
		 btAC.setBackground(Color.GRAY.darker().brighter().brighter());
		 btln.setBackground(Color.GRAY.darker().brighter().brighter());
		 btlog.setBackground(Color.GRAY.darker().brighter().brighter());
		 btsqrt.setBackground(Color.GRAY.darker().brighter().brighter());
		 btpower.setBackground(Color.GRAY.darker().brighter().brighter());
		 btplus.setBackground(Color.GRAY.darker().brighter().brighter());
		 btminus.setBackground(Color.GRAY.darker().brighter().brighter());
		 btmult.setBackground(Color.GRAY.darker().brighter().brighter());
		 btdiv.setBackground(Color.GRAY.darker().brighter().brighter());
		 bteq.setBackground(Color.BLUE.brighter().brighter().brighter());
		 bteq.setForeground(Color.WHITE);
		 btlpa.setBackground(Color.GRAY.darker().brighter().brighter());
		 btripa.setBackground(Color.GRAY.darker().brighter().brighter());
		 
		 //Grid Panel 생성, 방향에 따라 생성
		 pn = new Panel(new GridLayout(5,5,3,3));
		
		 //pn1에 버튼 추가
		 pn.add(btfact);pn.add(btlpa);pn.add(btripa);pn.add(btper);pn.add(btAC);
		 pn.add(btln);pn.add(bt7);pn.add(bt8);pn.add(bt9);pn.add(btdiv);
		 pn.add(btlog);pn.add(bt4);pn.add(bt5);pn.add(bt6);pn.add(btmult);
		 pn.add(btsqrt);pn.add(bt1);pn.add(bt2);pn.add(bt3);pn.add(btminus);
		 pn.add(btpower);pn.add(bt0);pn.add(btdot);pn.add(bteq);pn.add(btplus);
		 
		 //Button 영역과 textField 영역 연결
		 add(BorderLayout.NORTH, tf);
		 add(BorderLayout.CENTER,pn);
		 
		 setSize(300, 233);
		 setBackground(Color.WHITE);
		 WindowDestroyer listener = new WindowDestroyer();
		 addWindowListener(listener);
		 setVisible(true);
	}
	
	//연산을 수행하기 위한 버튼 조작 method
	public void Butt() { 
		//ButtonHandeler constructor 
		ButtonHandeler Hdr = new ButtonHandeler();
		
		
		bt0.addActionListener(Hdr);
		bt1.addActionListener(Hdr);
		bt2.addActionListener(Hdr);
		bt3.addActionListener(Hdr);
		bt4.addActionListener(Hdr);
		bt5.addActionListener(Hdr);
		bt6.addActionListener(Hdr);
		bt7.addActionListener(Hdr);
		bt8.addActionListener(Hdr);
		bt9.addActionListener(Hdr);
		btfact.addActionListener(Hdr);
		btper.addActionListener(Hdr);
		btAC.addActionListener(Hdr);
		btln.addActionListener(Hdr);
		btlog.addActionListener(Hdr);
		btsqrt.addActionListener(Hdr);
		btpower.addActionListener(Hdr);
		btplus.addActionListener(Hdr);
		btminus.addActionListener(Hdr);
		btmult.addActionListener(Hdr);
		btdiv.addActionListener(Hdr);
		bteq.addActionListener(Hdr);
		btdot.addActionListener(Hdr);
		btlpa.addActionListener(Hdr);
		btripa.addActionListener(Hdr);
	}	
	
	//각 버튼이 눌렸을 때 행동을 저장하는 method
	public class ButtonHandeler implements ActionListener{
		 
	 	//AC버튼이 눌렸을 때 작동될 함수
		public void AC() {
			tf.setText("0");
			res = 0;
			num = 0;
			statement = NUMBER;
			isClear = true;
		}
		
		//입력받은 숫자를 text field에 보이기 위한 함수
		public void Display(int i) {
			
			if(isClear) {
				res = 0;
				num =0;
				tf.setText("");
			}
			String prestr = tf.getText();
			if(prestr.indexOf("0") == 0 && !isFloat){
				prestr = prestr.substring(1);
			}
			if((!prestr.equals("0") || i>0 )){
				// result 레이블 + 입력받은 숫자
				tf.setText(prestr+i);
			}
			
			
			if(statement == POWER || statement == CAL) {isClear = false;}
			else{
				statement = NUMBER;
				isClear = false;
			}
		}
		
		public String IsFloat(String n) { //출력이 .0으로 끝나는 경우 Int형으로 변환
			if(n.length()-2 == n.indexOf(".")) {
				if(n.charAt(n.length()-1) == '0') {
					n = n.substring(0,n.length()-2);
				}
			}
			return n;
		}
		
		//x! 버튼이 눌렸을 때 작동될 함수
		public void btFact() {
			String preStr = tf.getText();
			num = Double.parseDouble(tf.getText());
			tf.setText(preStr + '!');
			double val = num;
			num--;
			while(num > 0)
			{
				val = val * num;
				num--;
			}
			res = val;
			statement = OPERATOR; // 숫자를 입력받는 상태로 statement ON
		}
		
		//% 버튼이 눌렸을 때 작동될 함수
		public void btPer() {
			String preStr = tf.getText();
			num = Double.parseDouble(tf.getText());
			tf.setText(preStr + '%');
			num /= 100;
			res = num;
			statement = OPERATOR; // 숫자를 입력받는 상태로 statement ON
		}
		
		//ln 버튼이 눌렸을 때 작동될 함수
		public void btLn() {
			String preStr = tf.getText();
			num = Double.parseDouble(tf.getText());
			tf.setText( Character.toString('l') + Character.toString('n') + preStr);
			res = Math.log(num);
			statement = OPERATOR;
		}
		
		//log 버튼이 눌렸을 때 작동될 함수
		public void btLog() {
			String preStr = tf.getText();
			num = Double.parseDouble(tf.getText());
			tf.setText( Character.toString('l') + Character.toString('o') + Character.toString('g')+preStr);
			res = Math.log10(num);
			statement = OPERATOR;
		}
		
		//√ 버튼이 눌렸을 때 작동될 함수
		public void btSqrt() {
			String preStr = tf.getText();
			num = Double.parseDouble(tf.getText());
			tf.setText( Character.toString('√')+preStr);
			res = Math.sqrt(num);
			statement = OPERATOR;
		}
		
		//x^y 버튼이 눌렸을 때 작동될 함수
		public void btPower() {
			String preStr = tf.getText();
			pnum1 = Double.parseDouble(preStr);
			tf.setText(preStr + '^');
			statement = POWER;
		}
		
		//.이 눌렸을 때 작동될 함수
		public void btDot() {
			isFloat = true;
			isClear = false;
			String preStr = tf.getText();
			int len = preStr.length();
			tf.setText(preStr + '.');
			dotloc = len + 1;
		}
		
		//( 버튼이 눌렸을 때 작동될 함수
		public void btLpa() {
			String preStr = tf.getText();
			isClear = false;
			isPa = true;
			tf.setText(preStr+'(');
			statement = CAL;
		}
		// ) 버튼이 눌렸을 때 작동될 함수 
		public void btRipa() {
			String preStr = tf.getText();
			tf.setText(preStr+')');
			statement = CAL;
		}
		
		//'+' 버튼이 눌렸을 때 작동되는 함수
		public void btPlus() {
			String preStr = tf.getText();
			tf.setText(preStr+'+');
			statement = CAL;
		}
		
		//'-' 버튼이 눌렸을 때 작동되는 함수
		public void btMinus() {
			String preStr = tf.getText();
			tf.setText(preStr+'-');
			if(statement == POWER) {
				statement = POWER;
			}
			else {
				statement = CAL;
			}
		}
		
		//'*' 버튼이 눌렸을 때 작동되는 함수
		public void btMul() {
			String preStr = tf.getText();
			tf.setText(preStr+'*');
			statement = CAL;
		}
		
		//'/' 버튼이 눌렸을 때 작동되는 함수
		public void btDiv() {
			String preStr = tf.getText();
			tf.setText(preStr+'/');
			statement = CAL;
		}
		
		
		//연산자인지 확인하기 위한 method
		public static boolean isOp (String token) {
		     // implement function
		     if (token.equals("/") ||  token.equals("-")|| token.equals("+") || token.equals("*")) 
		     {
		         return true;
		     }
		        return false;
		    }
		
		//연산자에 우선순위를 부여하는 method
		private static int getOpPre(char c) {
			switch (c) {
			case'*': //곱하기와 나누기 연산에 가장 높은 우선순위 부여
			case'/':
				return 3;
			case'+': //더하기와 나누기에 두 번째 우선순위 부여
			case'-':
				return 2;
			case'(': //괄호에 가장 낮은 우선순위 부여(내부계산만 가능하면 됨)
				return 1;
			default:
				return -1;
			}
		}
		
		//후위 표기법으로 변환하는 함수
		public static void convertToPostfix(String infix) {
			Stack<Character> stack = new Stack<>();
		
			int len = infix.length();
			int idx = 0;
			
			char Arr[] = infix.toCharArray();
			
			
		    while (idx < len) {
		         char token = Arr[idx];
		         char prevToken = (idx > 0) ? Arr[idx - 1] : ' ';
		          
		         //음수 처리를 위한 case
		         if((Arr[idx] == '-') && idx ==0 || (Arr[idx] == '-' && isOp(String.valueOf(prevToken)))) {
		        	 idx++;
		        	 token = Arr[idx];
		        	 if(Character.isDigit(token)) {
		            	StringBuilder number = new StringBuilder();
			            while (idx < len && (Character.isDigit(Arr[idx]))) {
			                number.append(Arr[idx]);
			                idx++;
			            }
			            postfix.append('-').append(number).append(' ');
		        	 }
		         } //음수가 아닌 경우 처리
		         else {   
		            if (token == '(') {
		                stack.push(token);
		                idx++;
		            } else if (token == ')') {
		                while (!stack.isEmpty() && stack.peek() != '(') {
		                    postfix.append(stack.pop()).append(' ');
		                }
		                stack.pop(); // '(' 제거
		                idx++;
		            } else if (isOp(String.valueOf(token))) {
		                while (!stack.isEmpty() && getOpPre(token) <= getOpPre(stack.peek())) {
		                    postfix.append(stack.pop()).append(' ');
		                }
		                stack.push(token);
		                idx++;
		            } else if (Character.isDigit(token)) {
		                StringBuilder number = new StringBuilder();
		                while (idx < len && ((Character.isDigit(Arr[idx]))|| Arr[idx] == '.')) {
		                    number.append(Arr[idx]);
		                    idx++;
		                }
		                postfix.append(number).append(' ');
		            } else {
		                idx++;
		            }
		        }
		    }
			

		    while (!stack.isEmpty()) {
		        postfix.append(stack.pop()).append(' ');
		    }
		    
		    postfix.deleteCharAt(postfix.length()-1);
		    
		}
		
		//후위 표기법으로 변환된 식을 계산하는 함수
		public void calculatePostfix(){
			Stack<Double> operandStack = new Stack<>();
			String[] tokens = postfix.toString().split("\\s+");
			
			for (String token : tokens) {
				if(Character.isDigit(token.charAt(0)) || (token.length() > 1 && Character.isDigit(token.charAt(1)))) {
					operandStack.push(Double.parseDouble(token));
				}
				else if (isOp(String.valueOf(token))) {
					double operand2 = operandStack.pop();
					double operand1 = operandStack.pop();
					if(token.equals("/") && operand2 == 0) {
						statement = ERROR;
						return;
					}
					double result = calculate(operand1,operand2,token);
					operandStack.push(result);
				}
			}
			res = operandStack.pop();
		}
		
		//각 operator에 따라 연산을 진행하는 method
		 private static double calculate(double operand1, double operand2, String operator) {
		        switch (operator) {
		            case "+":
		                return operand1 + operand2;
		            case "-":
		                return operand1 - operand2;
		            case "*":
		                return operand1 * operand2;
		            case "/":
		                return operand1 / operand2;
		            default:
		                throw new IllegalArgumentException("Invalid operator: " + operator);
		        }
		    }
	
		// = 버튼이 눌렸을 때 작동될 함수
		public void btEqual() {
			if(statement == OPERATOR) { //n!,ln,log,root,% 연산 수행 후 작동
				String strres = String.valueOf(res);
				strres = IsFloat(strres);
				tf.setText(String.valueOf(strres));
				isClear = true;
				statement = NUMBER;
			}
			else if (statement == CAL) { //사칙연산 후 작동
				convertToPostfix(tf.getText());
				calculatePostfix();
				if(statement == ERROR) { // divide by 0 => NAN값이 뜨도록 설정
					tf.setText("NAN");
					isClear = true;
					statement = NUMBER;
					postfix.setLength(0);
				}
				else {
					String strres = String.valueOf(res);
					strres = IsFloat(strres);
					tf.setText(String.valueOf(strres));
					isClear = true;
					statement = NUMBER;	
					postfix.setLength(0);
				}
			
			}
			else if(statement == POWER) { //제곱 연산 수행 시 작동
				String Str = tf.getText();
				int idx = Str.indexOf('^');
				String n = Str.substring(idx+1);
				pnum2 = Double.parseDouble(n);
				res = Math.pow(pnum1, pnum2);
				String strres = String.valueOf(res);
				strres = IsFloat(strres);
				tf.setText(String.valueOf(strres));
				isClear = true;
				statement = NUMBER;
			}
			else {
				String result = tf.getText();
				tf.setText(result);
				isClear = true;
				statement = NUMBER;
			}
		}

		//각 버튼 별로 Action performance 지정
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == bt1) {
				Display(1);
			}
			else if(e.getSource() == bt2) {
				Display(2);
			}
			else if(e.getSource() == bt3) {
				Display(3);
			}
			else if(e.getSource() == bt4) {
				Display(4);
			}
			else if(e.getSource() == bt5) {
				Display(5);
			}
			else if(e.getSource() == bt6) {
				Display(6);
			}
			else if(e.getSource() == bt7) {
				Display(7);
			}
			else if(e.getSource() == bt8) {
				Display(8);
			}
			else if(e.getSource() == bt9) {
				Display(9);
			}
			else if(e.getSource() == bt0) {
				Display(0);
			}
			else if(e.getSource() == btAC) {
				AC();
			}
			else if(e.getSource() == btfact) {
				btFact();
			}
			else if(e.getSource() == btper) {
				btPer();
			}
			else if(e.getSource() == btln) {
				btLn();
			}
			else if(e.getSource() == btlog) {
				btLog();
			}
			else if(e.getSource() == btsqrt) {
				btSqrt();
			}
			else if(e.getSource() == btpower) {
				btPower();
			}
			else if(e.getSource() == btplus) {
				btPlus();
			}
			else if(e.getSource() == btminus) {
				btMinus();
			}
			else if(e.getSource() == btmult) {
				btMul();
			}
			else if(e.getSource() == btdiv) {
				btDiv();
			}
			else if(e.getSource() == bteq) {
				btEqual();
			}
			else if(e.getSource() == btdot) {
				btDot();
			}
			else if(e.getSource() == btlpa) {
				btLpa();
			}
			else if(e.getSource() == btripa) {
				btRipa();
			}
			
			
		}
		
		
	}

	public static void main(String[] args) {
		Calculator calc = new Calculator("Calculator!!!");
	}
}