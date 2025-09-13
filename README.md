# Java_Calculator
CSE3040 JAVA 언어 수업에서 과제로 작성했던 계산기 코드입니다.

## 1. 개발 환경

-note book : SAMSUNG; DESKTOP-K3TFASK
-java : eclipse

## 2. package name

-calculator

## 3. 입력 방식

- 계산기 연산을 위한 GUI 프로그램(history 기능은 없습니다.)
- 사칙연산; 연산의 우선순위를 고려한 계산, '=' 버튼 클릭 전까지의 식을 계산
- %, ln, log, n!, x^y, root 연산 : 숫자 입력 후 각 기능을 하는 버튼 클릭 후 '=' 버튼을 누르면 결과값이 textfield에 나타남
- 결과값이 뜬 후 새로운 값을 입력하면 textfield가 초기화되며 새로운 연산이 시작됨
- AC 버튼을 누르면 result 값, textfield가 초기화되며 처음 상태로 돌아감

## 4. method 설명
- public void Generator()  : GUI를 통한 계산기 구현 부분
- public void AC() : AC 버튼이 눌렸을 때 화면을 초기화시키기 위한 method
- public void Display(int i) : 숫자가 눌렸을 때 화면에 표시하기 위한 method
- public void IsFloat(String n) : 출력이 정수값임에도 .0으로 끝나는 경우 지워주기 위한 method
- btFact, btPer, btLn, btLog, btSqrt : 각 버튼이 눌렸을 때 연산 결과를 저장하고, 화면을 update하기 위한 method
- btPower : x의 n승 계산을 위해 statement와 화면을 update하기 위한 method
- btDot, btLpa, btRipa : 사칙 연산 계산 및 소수점 표기를 위해 textfield를 update하는 method
- btMinus : 뺄셈 연산 및 음수 표현을 위한 버튼 method
- btPlust, btMul, btDiv : 사칙연산 식을 update하기 위한 method
- public static boolean isOp(Stirng token) : 연산자인지 확인하기 위한 method
- private static int getOpPre(char c) : 연산자의 우선 순위를 부여하기 위한 method
- public static void convertToPostfix(String infix) : 입력받은 사칙연산 식을 postfix로 바꾸기 위한 method(음수처리도 이 부분에서 계산)
- public void calculatePostfix() : 후위 표기법으로 변환한 식을 계산하기 위한 method
-  private static double calculate(double operand1, double operand2, String operator) : operator에 따라 연산을 진행하는 method 
- public void btEqual() : '=' 버튼이 눌리면, statement에 따라 결과값을 textfield에 보이는 method 
