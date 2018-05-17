package tsetToashBox;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioPanel extends JPanel{
	private ButtonGroup buttonGroup;
	private JButton jButton;
	public RadioPanel(String title,String[] radioCotentArray) {
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS ));
		buttonGroup=new ButtonGroup();
		
		for(int i=0;radioCotentArray!=null && i<radioCotentArray.length;i++) {
			JRadioButton radioButton=new JRadioButton(radioCotentArray[i]);
			radioButton.setActionCommand(radioCotentArray[i]);
			this.add(radioButton);
			buttonGroup.add(radioButton);
			radioButton.setSelected(i==0);
		}
	}
	
	public void setButton(String name) {
		jButton=new JButton(name);
		this.add(jButton);
	}
	
	public JButton getButton() {
		return jButton;
	}
	
	public String getRadioButtonSelection() {
		return buttonGroup.getSelection().getActionCommand();
	}
}
