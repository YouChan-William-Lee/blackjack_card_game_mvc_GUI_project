package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import controller.AddButtonListener;
import controller.BetButtonListener;
import controller.ComboBoxListener;
import controller.DealButtonListener;
import controller.MenuBarListener;
import controller.RemoveButtonListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class MainFrame extends JFrame 
{
	private MainPanel mainPanel;
	private JComboBox combo;
	
	private GameEngine gameEngine;
	private Player selectedPlayer;
	
	private JButton betBtn;
	private JButton addBtn;
	private JButton removeBtn;
	private JButton dealBtn;
	
	private JMenuItem exitItem;
	private JMenuItem helpItem;
	
	private JPanel statusBarPanel;
	
	private int dealCnt;
	
	public int getDealCnt() 
	{
		return dealCnt;
	}

	public void setDealCnt(int dealCnt) 
	{
		this.dealCnt = dealCnt;
	}

	public JButton getRemoveBtn() 
	{
		return removeBtn;
	}

	public void setRemoveBtn(JButton removeBtn) 
	{
		this.removeBtn = removeBtn;
	}
	
	public Player getSelectedPlayer() 
	{
		return selectedPlayer;
	}
	
	public void setSelectedPlayer(Player player) 
	{
		this.selectedPlayer = player;
	}
	
	public MainFrame(GameEngine gameEngine) 
	{
		super("Card game by You Chan Lee s3850825");
		this.gameEngine = gameEngine;
		
		// get screen size for size and location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		
        mainPanel = new MainPanel();
		setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        add(mainPanel,BorderLayout.CENTER);
        
        createMenuBar();
        createToolBar();
        
        statusBarPanel = new StatusBarPanel();
        add(statusBarPanel, BorderLayout.SOUTH);
        
        setSize(1300, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        revalidate();
	}

	public void createMenuBar() 
	{
		JMenuBar menubar = new JMenuBar();
	    JMenu fileMenu = new JMenu("File");
	    JMenu helpMenu = new JMenu("Help");
	
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    
	    exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
	    exitItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));
	    exitItem.addActionListener(new MenuBarListener(this));
	    		
	    helpItem = new JMenuItem("Rule");
	    helpItem.addActionListener(new MenuBarListener(this));
	    
	    fileMenu.add(exitItem);
	    helpMenu.add(helpItem);
	    menubar.add(fileMenu);
	    menubar.add(helpMenu);
	    setJMenuBar(menubar);
	}
	
    public void createToolBar() 
    {
        JToolBar toolBar = new JToolBar("Menu");
        toolBar.setBackground(Color.LIGHT_GRAY);
        
        dealBtn = new JButton("Deal");
        dealBtn.addActionListener(new DealButtonListener(this, gameEngine));
        dealBtn.setEnabled(false);
        toolBar.add(dealBtn);
        
        betBtn = new JButton("Bet");
        betBtn.addActionListener(new BetButtonListener(this, gameEngine));
        betBtn.setEnabled(false);
        toolBar.add(betBtn);
        
        addBtn = new JButton("Add Player");
        addBtn.addActionListener(new AddButtonListener(this, gameEngine));
        toolBar.add(addBtn);
        
        removeBtn = new JButton("Remove Player");
        removeBtn.addActionListener(new RemoveButtonListener(this, gameEngine));
        toolBar.add(removeBtn);
        
        combo = new JComboBox();
        combo.addActionListener(new ComboBoxListener(this, gameEngine));
        combo.addItem("House");
        toolBar.add(combo);

        add(toolBar,BorderLayout.NORTH);
    }
    
    public JMenuItem getExitItem() 
    {
		return exitItem;
	}

	public void setExitItem(JMenuItem exitItem) 
	{
		this.exitItem = exitItem;
	}

	public JMenuItem getHelpItem() 
	{
		return helpItem;
	}

	public void setHelpItem(JMenuItem helpItem) 
	{
		this.helpItem = helpItem;
	}

	public JComboBox getCombo() 
	{
		return combo;
	}

	public void setCombo(JComboBox combo) 
	{
		this.combo = combo;
	}

	public JButton getBetBtn() 
	{
		return betBtn;
	}

	public void setBetBtn(JButton betBtn) 
	{
		this.betBtn = betBtn;
	}

	public JButton getAddBtn() 
	{
		return addBtn;
	}

	public void setAddBtn(JButton addBtn) 
	{
		this.addBtn = addBtn;
	}

	public JButton getDealBtn() 
	{
		return dealBtn;
	}

	public void setDealBtn(JButton dealBtn) 
	{
		this.dealBtn = dealBtn;
	}

	public void addCard(String userId, PlayingCard card) 
	{
		mainPanel.addCard(userId, card);		
	}
	
	public void removeCardPanel(String userId) 
	{
		mainPanel.removeCardPanel(userId);
	}

	public void clearCards() 
	{
		mainPanel.clearCards();		
	}
	
	public void addPlayer(Player player) 
	{
		mainPanel.addPlayer(player);
	}

	public void removeRowById(String id) 
	{
		mainPanel.removeRowById(id);
	}

	public void updateTableValue(String id, int colIndex, String value) 
	{
		mainPanel.updateTableValue(id, colIndex, value);
	}
	
	public void showSelectedCardById(String userId) 
	{
		mainPanel.showSelectedCardById(userId);
	}
}
