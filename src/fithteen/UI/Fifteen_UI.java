package fithteen.UI;

import fithteen.engine.core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class Fifteen_UI extends JFrame {
    private JPanel gamePanel = new JPanel(new GridLayout(4, 4, 2, 2));
    private Game game = new Game(4);
    private int turns = 0;

    public Fifteen_UI(){
        super("Fifteen");
        setBounds(200,200,500,500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMenu();

        Container container = getContentPane();
        gamePanel.setDoubleBuffered(true);
        container.add(gamePanel);

        newGame();
    }

    private void createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        for (String fileItem : new String [] { "New", "Exit" }) {
            JMenuItem item = new JMenuItem(fileItem);
            item.setActionCommand(fileItem.toLowerCase());
            item.addActionListener(new NewMenuListener());
            fileMenu.add(item);
        }
        JMenuItem stepMenu = new JMenuItem("Step Back");

        stepMenu.addActionListener(new StepBackListener());
        fileMenu.insertSeparator(1);

        menu.add(fileMenu);
        menu.add(stepMenu);
        setJMenuBar(menu);
    }

    private void generate(){
        for(int i=0;i<2;i++) {
            game.makeRandomTurn();
        }
        repaintField();
    }

    private void repaintField(){
        int size =gamePanel.getComponentCount();
        for(int i =0;i<size;i++)
            gamePanel.remove(0);
        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                JButton button = new JButton(Integer.toString(game.getValueFromPosition(i,j)));
                button.setFocusable(false);
                gamePanel.add(button);
                if(i==0&&j==0)
                    button.setBackground(Color.RED);
                if (game.getValueFromPosition(i,j) == 0) {
                    button.setVisible(false);
                } else
                    button.addActionListener(new NewClickListener());
            }
        }
        gamePanel.validate();
        gamePanel.repaint();
    }

    private class NewMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if ("exit".equals(command)) {
                System.exit(0);
            }
            if ("new".equals(command)) {
                newGame();
            }
        }
    }

    private class NewClickListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            game.saveSate();
            JButton button = (JButton) e.getSource();
            button.setVisible(false);
            game.makeTurn(Integer.parseInt(button.getText()));
            repaintField();
            if(game.isGameEnd()) {
                    JOptionPane.showMessageDialog(null, "You Won!");
                    newGame();
                }
        }
    }

    private class StepBackListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                game.restore();
                repaintField();
            }catch (EmptyStackException ex){
                JOptionPane.showMessageDialog(null, "No more saves!");
            }
        }
    }

    private void newGame(){
        game.newGame();
        generate();
        repaintField();
    }
}
