package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfaces.Player;

public class SummaryPanel extends JPanel 
{
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private HashMap<String, Integer> userIndexMap;// id, row

	public SummaryPanel() 
	{
		userIndexMap = new HashMap<>();
		setLayout(new BorderLayout());
		String headers[] = { "id", "Name", "Points", "Bet", "Result", "Win/Loss" };
		defaultTableModel = new DefaultTableModel(headers, 0);
		table = new JTable(defaultTableModel);
		table.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		setPreferredSize(new Dimension(0,70));
	}

	public void addPlayer(Player player) 
	{
		if (userIndexMap.containsKey(player.getPlayerId()) == false) 
		{
			String[] row = new String[6];
			row[0] = player.getPlayerId();
			row[1] = player.getPlayerName();
			row[2] = String.valueOf(player.getPoints());
			row[3] = String.valueOf(player.getBet());
			row[4] = String.valueOf(player.getResult());
			row[5] = "Draw";
			
			defaultTableModel.addRow(row);
			userIndexMap.put(row[0], defaultTableModel.getRowCount() - 1);
		}
	}

	public void removeRowById(String id) 
	{
		if (userIndexMap.containsKey(id) == false) 
			return;
		
		int row = userIndexMap.get(id);
		
		for (String key : userIndexMap.keySet()) 
		{
			int value = userIndexMap.get(key);
			if (row < value && value > 0)
				userIndexMap.put(key, value - 1);
		}
		
		defaultTableModel.removeRow(row);
		userIndexMap.remove(id);
	}

	public void updateTableValue(String id, int colIndex, String value) 
	{
		int row = userIndexMap.get(id);
		table.getModel().setValueAt(value, row, colIndex);
	}
}