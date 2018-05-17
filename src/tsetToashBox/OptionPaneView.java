package tsetToashBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class OptionPaneView {
	JFrame jFrame;
	JPanel jPanel, topPanel, buttomPanel;
	RadioPanel promptIconPanel, promptMessagePanel, inputTextBoxPanel, optionConfirmResponseBoxPanel,
			optionConfirmTypeBoxPanel, responseButtonBoxPanel;

	private OptionPaneView() {
		init();
		initLayout();
	}

	public static OptionPaneView getOptionPaneViewObject() {
		return new OptionPaneView();
	}

	private void init() {
		topPanel = new JPanel();
		buttomPanel = new JPanel();

		promptIconPanel = new RadioPanel("option_prompt_icon",
				new String[] { "error", "information", "warning", "question", "plain" });

		promptMessagePanel = new RadioPanel("option_prompt_content",
				new String[] { "string", "image", "component", "object", "object[]" });

		inputTextBoxPanel = new RadioPanel("input_text", new String[] {});
		inputTextBoxPanel.setButton("input_text");

		optionConfirmResponseBoxPanel = new RadioPanel("option_confirm_box_response",
				new String[] { "default", "yes_or_no", "yes_no_cancel", "ok_cancel" });
		optionConfirmResponseBoxPanel.setButton("option_confirm_box_response");

		optionConfirmTypeBoxPanel = new RadioPanel("option_confirm_type",
				new String[] { "single_text_box", "drop_down_option_box" });
		optionConfirmTypeBoxPanel.setButton("option_confirm_type");

		responseButtonBoxPanel = new RadioPanel("response_button",
				new String[] { "string_button", "image_button", "object_button" });
		responseButtonBoxPanel.setButton("response_button");
	}

	private void initLayout() {
		jFrame = new JFrame("test option pane type");
		jPanel = new JPanel();

		jPanel.setLayout(new GridLayout(2, 1));
		topPanel.setLayout(new GridLayout(1, 2));
		topPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "top",
				TitledBorder.CENTER, TitledBorder.TOP));

		buttomPanel.setLayout(new GridLayout(1, 4));
		buttomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "buttom",
				TitledBorder.CENTER, TitledBorder.TOP));

		topPanel.add(promptIconPanel);
		topPanel.add(promptMessagePanel);

		buttomPanel.add(inputTextBoxPanel);
		buttomPanel.add(optionConfirmResponseBoxPanel);
		buttomPanel.add(optionConfirmTypeBoxPanel);
		buttomPanel.add(responseButtonBoxPanel);

		inputTextBoxPanel.getButton().addActionListener(new ShowAction());
		optionConfirmResponseBoxPanel.getButton().addActionListener(new ShowAction());
		optionConfirmTypeBoxPanel.getButton().addActionListener(new ShowAction());
		responseButtonBoxPanel.getButton().addActionListener(new ShowAction());

		jPanel.add(topPanel);
		jPanel.add(buttomPanel);

		jFrame.add(jPanel);

		jFrame.setVisible(true);
		jFrame.pack();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public int getOptionPromptIcon() {
		switch (promptIconPanel.getRadioButtonSelection()) {
		case "error":
			return JOptionPane.ERROR_MESSAGE;
		case "information":
			return JOptionPane.INFORMATION_MESSAGE;
		case "warning":
			return JOptionPane.WARNING_MESSAGE;
		case "question":
			return JOptionPane.QUESTION_MESSAGE;
		case "plain":
			return JOptionPane.PLAIN_MESSAGE;
		default:
			return JOptionPane.PLAIN_MESSAGE;
		}
	}

	public Object getPromptMessage() {
		switch (promptMessagePanel.getRadioButtonSelection()) {
		case "string":
			return "string content";
		case "image":
			return new ImageIcon("ico/1.PNG");
		case "component":
			return new JButton("component message");
		case "object":
			return new Date();
		case "object[]":
			return new Object[] { "string content", new ImageIcon("ico/1.PNG"), new JButton("component message"),
					new Date() };
		default:
			return new Date();
		}
	}

	public void getInputText() {

	}

	public int getOptionConfirmResponse() {
		switch (optionConfirmResponseBoxPanel.getRadioButtonSelection()) {
		case "default":
			return JOptionPane.DEFAULT_OPTION;
		case "yes_or_no":
			return JOptionPane.YES_NO_OPTION;
		case "yes_no_cancel":
			return JOptionPane.YES_NO_CANCEL_OPTION;
		case "ok_cancel":
			return JOptionPane.OK_CANCEL_OPTION;
		default:
			return JOptionPane.OK_CANCEL_OPTION;
		}
	}

	public Object[] getResponseButton() {
		switch (responseButtonBoxPanel.getRadioButtonSelection()) {
		case "string_button":
			return new String[] { "a", "b", "c" };
		case "image_button":
			return new Icon[] { new ImageIcon("ico/1.PNG"), new ImageIcon("ico/2.PNG"), new ImageIcon("ico/3.PNG") };
		case "object_button":
			return new Object[] { new Date(), new Date(), new Date() };
		default:
			return new Object[] { new Date(), new Date(), new Date() };
		}
	}

	private class ShowAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "input_text":
				JOptionPane.showMessageDialog(jFrame, getPromptMessage(), "input_text", getOptionPromptIcon());
				break;
			case "option_confirm_box_response":
				JOptionPane.showConfirmDialog(jFrame, getPromptMessage(), "option_confirm_box_response",
						getOptionConfirmResponse(), getOptionPromptIcon());
				break;
			case "option_confirm_type":
				if (optionConfirmTypeBoxPanel.getRadioButtonSelection().equals("single_text_box")) {
					JOptionPane.showInputDialog(jFrame, getPromptMessage(), "option_confirm_type",
							getOptionPromptIcon());
				} else {
					JOptionPane.showInputDialog(jFrame, getPromptMessage(), "option_confirm_type",
							getOptionPromptIcon(), null, new String[] { "aa", "bb" }, "bb");
				}
				break;
			case "response_button":
				JOptionPane.showOptionDialog(jFrame, getPromptMessage(), "response_button", getOptionConfirmResponse(),
						getOptionPromptIcon(), null, getResponseButton(), "a");
				break;
			default:
				break;
			}
		}

	}
}
