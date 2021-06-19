import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BasicLayout extends JFrame implements ActionListener{
	private String[][] expStrings = {
			{"DELETE", "(", ")", "/"},
			{"7", "8", "9", "*"},
			{"4", "5", "6", "-"},
			{"1", "2", "3", "+"},
			{"CE", "0", ".", "="}
	};
	private int WIDTH = 500;
	private int HEIGHT = 800;
	
	public JTextField inputText = new JTextField();
	public JTextField resultText = new JTextField();
	private JPanel expPanel = new JPanel();
	
	private ExpressionTree expTree = new ExpressionTree();
	private String inorderExp = "";
	
	public BasicLayout() {
		// TODO Auto-generated constructor stub
		super("BASIC-CALCULATOR");
		setSize(WIDTH, HEIGHT);
		setLayout(null);
		init();
	}
	  
	private void init() {		
		// -----------input & result panel------------
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBackground(Color.black);
		topPanel.setBounds(0, 0, 450, 100);
		
		inputText.setEditable(false);
		inputText.setBounds(0, 0, 450, 50);
		inputText.setBackground(Color.BLACK);
		inputText.setFont(new Font("Consolas", Font.PLAIN, 20));
		inputText.setForeground(new Color(0x00FF00));
		inputText.setCaretColor(Color.white);
		
		resultText.setEditable(false);
		resultText.setBounds(0, 50, 450, 50);
		resultText.setBackground(Color.BLACK);
		resultText.setFont(new Font("Consolas", Font.PLAIN, 20));
		resultText.setForeground(new Color(0x00FF00));
		resultText.setCaretColor(Color.white);
		resultText.setText("hello!");
		
		topPanel.add(inputText);
		topPanel.add(resultText);
		
		add(topPanel);
		
		// -------------  keys panel ----------------
		expPanel.setLayout(new GridLayout(5, 4));
		expPanel.setBounds(0, 100, 450, 500);
		add(expPanel);
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<4; j++) {
				JButton btn = new JButton(expStrings[i][j]);
				btn.addActionListener(this);
				expPanel.add( btn );
			}
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
//		System.out.println(str);
		if(str == null || str.equals("")) {
			return;
		}
		
		if(str.equals("=")) {
			
			double res = expTree.calculate(inorderExp);
			
			inorderExp = res+"";
			resultText.setText(res+"");
			
		}else if(str.equals("CE")){
			
			inorderExp = "";
			inputText.setText(inorderExp);
			
		}else if(str.equals("DELETE")) {
			
			if(inorderExp.length() >= 1) {
				inorderExp = inorderExp.substring(0, inorderExp.length()-1);
				inputText.setText(inorderExp);
			}
			
		}else {
			inorderExp += str;
			inputText.setText(inorderExp);
		}
		
	}

}
