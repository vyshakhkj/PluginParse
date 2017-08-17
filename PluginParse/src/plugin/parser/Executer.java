package plugin.parser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Executer extends JFrame implements ActionListener {
	
	JRadioButton add;
	JRadioButton sub;
	JRadioButton mul;
	JRadioButton div;
	JTextField lhsField;
	JTextField rhsField;
	JTextField resultField;
	JLabel lhsLabel;
	JLabel rhsLabel;
	JLabel resultLabel;
	JButton executeButton;
	ButtonGroup radioButtonGroup;
	public Executer() {
		add = new JRadioButton("Add");
		add.setActionCommand("ADD");
		add.setBounds(50, 50, 50, 25);

		sub = new JRadioButton("Sub");
		sub.setActionCommand("SUB");
		sub.setBounds(120, 50, 50, 25);

		mul = new JRadioButton("Mul");
		mul.setActionCommand("MUL");
		mul.setBounds(190, 50, 50, 25);

		div = new JRadioButton("Div");
		div.setActionCommand("DIV");
		div.setBounds(260, 50, 50, 25);

		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(sub);
		radioButtonGroup.add(mul);
		radioButtonGroup.add(div);
		radioButtonGroup.add(add);

		lhsLabel = new JLabel("LHS : ");
		lhsLabel.setBounds(50, 80, 70, 20);
		lhsField = new JTextField();
		lhsField.setBounds(125, 80, 150, 20);

		rhsLabel = new JLabel("RHS : ");
		rhsLabel.setBounds(50, 110, 70, 20);
		rhsField = new JTextField();
		rhsField.setBounds(125, 110, 150, 20);

		resultLabel = new JLabel("RESULT : ");
		resultLabel.setBounds(50, 140, 70, 20);
		resultField = new JTextField();
		resultField.setBounds(125, 140, 150, 20);

		executeButton = new JButton("Execute");
		executeButton.setBounds(125, 170, 150, 20);
		executeButton.addActionListener(this);

		add(add);
		add(sub);
		add(mul);
		add(div);
		add(lhsLabel);
		add(lhsField);
		add(rhsLabel);
		add(rhsField);
		add(resultLabel);
		add(resultField);
		add(executeButton);
		setLayout(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Executer();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String lhs = lhsField.getText();
			String rhs = rhsField.getText();
			String operator = radioButtonGroup.getSelection().getActionCommand();
			OperationContainer operationContainer = new OperationContainer();
			operationContainer.lhs = Double.valueOf(lhs);
			operationContainer.rhs = Double.valueOf(rhs);
			operationContainer.operator = operator;
			ExecutionContext executionContext = new ExecutionContext();
			executionContext.put("input", operationContainer);
			PluginManager pluginManager = new PluginManager("plugins.xml");
			String result = pluginManager.execute(executionContext);
			resultField.setText(result);
		} catch (Exception error) {

		}
	}
}
