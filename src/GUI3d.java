import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.TextArea;
import java.awt.Font;

public class GUI3d {

	private JFrame frame;
	private JTextField startLevel;
	private JTextField startRow;
	private JTextField startCol;
	private JTextField levelLength;
	private JTextField rowLength;
	private JTextField colLength;
	private JTextField trailAmount;
	private JTextField ReadOff;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI3d window = new GUI3d();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI3d() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 936, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TextArea outBox = new TextArea();
		outBox.setEditable(false);
		outBox.setBounds(503, 74, 380, 386);
		frame.getContentPane().add(outBox);
		
		JTextArea inBox = new JTextArea();
		inBox.setBounds(29, 74, 321, 386);
		frame.getContentPane().add(inBox);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outBox.setText("");
				ReadOff.setText("");
				String input = inBox.getText();
				final int totalLevels = Integer.parseInt(levelLength.getText());
		        final int totalRows = Integer.parseInt(rowLength.getText());
		        final int totalCols = Integer.parseInt(rowLength.getText());
		        final int sLevel = Integer.parseInt(startLevel.getText());
		        final int sRow = Integer.parseInt(startRow.getText());
		        final int sCol = Integer.parseInt(startCol.getText());
		        final int trail = Integer.parseInt(trailAmount.getText());
		        int placement = 0;
		        String[] d = input.split("\\s");
		        String[][] rawData = new String[totalLevels][totalRows];
		        for(int l = 0; l < totalLevels; l++) {
		        	for(int r = 0; r < totalRows; r++) {
		        		String nextString = d[placement];
		        		rawData[l][r] = nextString;
		        		placement++;
		        	}
		        }
		        Maze maze = new Maze(rawData);
		        maze.randomSolveTrial(sLevel, sRow, sCol, trail);
		        String res = maze.printStringAsSortedMaze();
		        String trailResult = maze.printTrailStats();
		        outBox.setText(res);
		        ReadOff.setText(trailResult);
			}
		});
		
		btnNewButton.setBounds(380, 471, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		startLevel = new JTextField();
		startLevel.setBounds(373, 268, 96, 20);
		frame.getContentPane().add(startLevel);
		startLevel.setColumns(10);
		
		startRow = new JTextField();
		startRow.setBounds(373, 313, 96, 20);
		frame.getContentPane().add(startRow);
		startRow.setColumns(10);
		
		startCol = new JTextField();
		startCol.setBounds(373, 365, 96, 20);
		frame.getContentPane().add(startCol);
		startCol.setColumns(10);
		
		levelLength = new JTextField();
		levelLength.setBounds(373, 121, 96, 20);
		frame.getContentPane().add(levelLength);
		levelLength.setColumns(10);
		
		rowLength = new JTextField();
		rowLength.setBounds(373, 165, 96, 20);
		frame.getContentPane().add(rowLength);
		rowLength.setColumns(10);
		
		colLength = new JTextField();
		colLength.setBounds(373, 212, 96, 20);
		frame.getContentPane().add(colLength);
		colLength.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Start Level");
		lblNewLabel.setBounds(373, 254, 96, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Start Row");
		lblNewLabel_1.setBounds(373, 299, 96, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Start Col");
		lblNewLabel_2.setBounds(373, 344, 48, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("# Levels");
		lblNewLabel_3.setBounds(373, 107, 48, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("# Rows");
		lblNewLabel_4.setBounds(373, 152, 48, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("#Cols");
		lblNewLabel_5.setBounds(373, 198, 48, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		trailAmount = new JTextField();
		trailAmount.setBounds(373, 413, 96, 20);
		frame.getContentPane().add(trailAmount);
		trailAmount.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Trials");
		lblNewLabel_6.setBounds(373, 396, 48, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		ReadOff = new JTextField();
		ReadOff.setEditable(false);
		ReadOff.setBounds(503, 474, 380, 20);
		frame.getContentPane().add(ReadOff);
		ReadOff.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("3D Random Maze Solver");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_7.setBounds(29, 11, 354, 38);
		frame.getContentPane().add(lblNewLabel_7);
		

		

	}
}
