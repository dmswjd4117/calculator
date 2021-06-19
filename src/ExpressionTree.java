

import java.util.*;

public class ExpressionTree {
	
	public double calculate(String inorder) {
	   
		ArrayList<String> chunkedInorder = this.chunk(inorder);	
		ArrayList<String> postorder = this.makePostOrder(chunkedInorder);		
		
		// System.out.println("=====");
		// for(int i=0; i<postorder.size(); i++) {
		// 	System.out.print(postorder.get(i)+" , ");
		// }
		// System.out.println("\n=====");

		Stack<Double>stack = new Stack<Double>();
		int length = postorder.size();
		for(int i=0; i<length; i++) {
			String token = postorder.get(i);
			if(isDouble(token)) {
				stack.add(Double.parseDouble(token));
			}else {
				char op = postorder.get(i).charAt(0);
				double two = stack.pop();
				double one = stack.pop();
				
				switch(op) {
				case '+':
					stack.add(one+two);
					break;
				case (char) '-':
					stack.add(one-two);
					break;
				case '*':
					stack.add(one*two);
					break;
				case '/':
					stack.add(one/two);
					break;
				}
			}
		}
		
		if(stack.empty()) {
			return 0.0;
		}
		
		return stack.pop();
	}
	
	public ArrayList chunk(String inorder) {
		String temp = "";
		ArrayList ans = new ArrayList();
		int length = inorder.length();
		
		for(int i=0; i<length; i++) {
			System.out.println("! "+ inorder.charAt(i));
			char token = inorder.charAt(i);
			if(Character.isDigit(token) || token == '.') {
				temp += token;
			}else if(token == '+' || token == '-'){
				if(i == 0) {
					temp += token;
				
				}else if(Character.isDigit(inorder.charAt(i-1)) || inorder.charAt(i-1) == ')') {
					if(temp != "") {
						ans.add(temp);
						temp = "";
					}
					ans.add(token+"");
				}else{
					temp += token;
				}
			}else {
				if(temp != "") {
					ans.add(temp);
					temp = "";
				}
				ans.add(token+"");
			}
		}

		
		if(temp != "") ans.add(temp);

		// System.out.println("=====");
		// for(int i=0; i<ans.size(); i++) {
		// 	System.out.print(ans.get(i)+" , ");
		// }
		// System.out.println("\n=====");

		return ans;
	}
	
	public ArrayList<String> makePostOrder(ArrayList inorder) {
		int length = inorder.size();
		
		Stack<String> stack = new Stack<String>();
		ArrayList<String> ans = new ArrayList<String>();
	
		for(int i=0 ; i<length; i++) {
			String ch = (String) inorder.get(i);
	        switch (ch) {
            case "+":
            case "-":
            case "*":
            case "/":
            	int p = getPriority(ch);
                while (!stack.isEmpty() && getPriority((String) stack.peek()) >= p) {
                	ans.add(stack.pop());
                }
                stack.push(ch+"");
                break;
            case "(":
                stack.push(ch);
                break;
            case ")":
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                	ans.add(stack.pop());
                }
                stack.pop();
                break;
            default: 
            	ans.add(ch);
	        }
        }
		
		while(!stack.empty()) {
			String top = (String) stack.pop();
			ans.add(top);
		}
		
		return ans;
	}
	
	public int getPriority(String ch) {
		switch(ch) {
		case "*":
		case "/":
			return 10;
		case "+":
		case "-":
			return 5;
		case "(":
			return 3;
		default:
			return 0;
		}
	}
	
	  public static boolean isDouble(String s) {
		    try {
		        Double.parseDouble(s);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
	}

}