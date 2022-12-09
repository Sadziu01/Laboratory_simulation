package pwr.sadowski;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	JPanel panel;
	Labolatorium lab;

	protected JLabel distributor, assistant, foody, stamina;
	protected List<JLabel> assistantsList = new ArrayList<>(), foodList = new ArrayList<>(), staminaList = new ArrayList<>();
	protected List<Creature> creatureList = new ArrayList<>();
	protected List<Tank> foodyList = new ArrayList<>();
	protected List<Thread> threadList = new ArrayList<>();
	protected static JSlider sliderForCreature, sliderForAssistant;


	public MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 10, 455, 370);
		contentPane.add(panel);
		panel.setLayout(null);

		distributor = new JLabel("|___|");
		distributor.setBounds(69, 155, 56, 13);
		panel.add(distributor);


		int yPosition = 59;
		for(int i = 0; i < 9; i++){
			assistant = new JLabel("|____|");
			assistant.setBounds(150, yPosition, 56, 23);
			panel.add(assistant);
			assistantsList.add(assistant);

			foody = new JLabel("|____|");
			foody.setBounds(250, yPosition, 56, 23);
			panel.add(foody);
			foodList.add(foody);

			stamina = new JLabel("|___|");
			stamina.setBounds(350, yPosition, 56, 23);
			panel.add(stamina);
			staminaList.add(stamina);

			yPosition += 23;
		}

		JLabel distributrorLabel = new JLabel("distributror");
		distributrorLabel.setBounds(57, 36, 80, 13);
		panel.add(distributrorLabel);

		JLabel assistantLabel = new JLabel("assistant");
		assistantLabel.setBounds(147, 36, 64, 13);
		panel.add(assistantLabel);

		JLabel nourishmentLabel = new JLabel("nourishment");
		nourishmentLabel.setBounds(235, 36, 90, 13);
		panel.add(nourishmentLabel);

		JLabel staminaLabel = new JLabel("stamina");
		staminaLabel.setBounds(341, 36, 65, 13);
		panel.add(staminaLabel);



		JLabel assistantSpeedLabel = new JLabel("Assistant Speed");
		assistantSpeedLabel.setBounds(200, 275, 126, 14);
		panel.add(assistantSpeedLabel);

		sliderForAssistant = new JSlider();
		sliderForAssistant.setMaximum(3000);
		sliderForAssistant.setMinimum(200);
		sliderForAssistant.setBounds(147, 291, 200, 26);
		panel.add(sliderForAssistant);

		JLabel creatureSpeedLabel = new JLabel("Creature Speed");
		creatureSpeedLabel.setBounds(200, 317, 126, 14);
		panel.add(creatureSpeedLabel);

		sliderForCreature = new JSlider();
		sliderForCreature.setMaximum(3000);
		sliderForCreature.setMinimum(200);
		sliderForCreature.setBounds(147, 332, 200, 26);
		panel.add(sliderForCreature);



		JTextField inputThreadsNumber = new JTextField();
		inputThreadsNumber.setBounds(39, 290, 86, 20);
		panel.add(inputThreadsNumber);
		inputThreadsNumber.setColumns(10);

		JButton startButton = new JButton("START");
		startButton.setBounds(40, 325, 85, 21);
		panel.add(startButton);

		startButton.addActionListener(e -> {
			if(isNumeric(inputThreadsNumber.getText())){
				int temp = Integer.parseInt(inputThreadsNumber.getText());
				if(temp > 0 && temp < 7){
					createThreads1(temp);
					startButton.setVisible(false);
					inputThreadsNumber.setVisible(false);
					MyThread.setEnd(false);
				}
				else {
					inputThreadsNumber.setText("Wrong number");
				}
			}
			else if(!isNumeric(inputThreadsNumber.getText())){
				inputThreadsNumber.setText("Input number");
			}
		});



		setVisible(true);
	}

	public static boolean isNumeric(String num){
		if(num == null){
			return false;
		}
		try{
			int i = Integer.parseInt(num);
		}catch (NumberFormatException e){
			return false;
		}
		return true;
	}

	public void createThreads1(int input){
		lab = new Labolatorium();
		if(input > 6 || input < 1){
			System.err.println("Only numbers <1, 6> .");
		}
		else{
			for (int i = 0; i < input; i++) {
				Assistant assistant = new Assistant((char) (97+i), lab, i);
				assistantsList.get(i).setText("|" + assistant.getName() + "__" + assistant.getValue() + "|");
				System.out.println(assistant.getName());
				assistant.start();
				threadList.add(assistant);
			}
			for (int i = 0; i < 9; i++) {
				Creature creature = new Creature((char) (48 + i), lab);
				creatureList.add(creature);
				Tank tank = new Tank(10, i);
				foodyList.add(tank);
				foodList.get(i).setText(tank.getFood());
				staminaList.get(i).setText("|_" + creature.stamina + "_|");
				creature.start();
				threadList.add(creature);
			}
		}
	}
}