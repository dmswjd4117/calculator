import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;


public class Main {
	static protected ExpressionTree expTree = new ExpressionTree();
	
	public static void main(String[] args) {
		BasicLayout frame = new BasicLayout();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    frame.setVisible(true);

		ExpressionTree e = new ExpressionTree();
		System.out.println(e.calculate("(-20+0)-7*8+2"));
	}
}


//( 0   -   5   ×   8   ÷   2 )   -   7   ×   8   +   2   ÷   9   -   ( 6   ×   2 )