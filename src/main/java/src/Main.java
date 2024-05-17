/*



5/3
- wincheck now works in full for all boards

5/2
- Downsized diamond & emerald textures needs fix
- Background of main menu and game window
- Win checking for 6x6 & 9x9 board
- Button size restrictions on main menu


*/
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main implements ActionListener{

    public static void sound(String sfx) {

    try{
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Main.class.getResource(sfx));
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    } catch (Exception ex) {
    System.out.println("error");
        }
    }


    public static int turn = 0;
    public static boolean winCheck(int player, int sidelength, int[][] gameboard){
        if (sidelength==3){
            if (gameboard[0][0]==gameboard[0][1] && gameboard[0][1]==gameboard[0][2] && gameboard[0][0] != 0||
                    gameboard[1][0]==gameboard[1][1] && gameboard[1][1]==gameboard[1][2] && gameboard[1][0] != 0||
                    gameboard[2][0]==gameboard[2][1] && gameboard[2][1]==gameboard[2][2] && gameboard[2][0] != 0||
                    gameboard[0][0]==gameboard[1][0] && gameboard[1][0]==gameboard[2][0] && gameboard[0][0] != 0||
                    gameboard[0][1]==gameboard[1][1] && gameboard[1][1]==gameboard[2][1] && gameboard[0][1] != 0||
                    gameboard[0][2]==gameboard[1][2] && gameboard[1][2]==gameboard[2][2] && gameboard[0][2] != 0||
                    gameboard[0][0]==gameboard[1][1] && gameboard[1][1]==gameboard[2][2] && gameboard[0][0] != 0||
                    gameboard[0][2]==gameboard[1][1] && gameboard[1][1]==gameboard[2][0] && gameboard[0][2] != 0){
                return true;
            }

        }

        else if (sidelength==6){
            for(int i=0; i<sidelength;i++){
                for(int o=0; o<sidelength;o++){
                    if(gameboard[i][o]!=0) {
                        //diagonal sl-1
                        if (i < 3 && o < 3 && gameboard[i][o] == gameboard[i + 1][o + 1] && gameboard[i + 1][o + 1] == gameboard[i + 2][o + 2] && gameboard[i + 2][o + 2] == gameboard[i + 3][o + 3]) {
                            return true;
                        }
                        //diagonal sl+1
                        if (i>=3 && o<3 && gameboard[i][o] == gameboard[i - 1][o + 1] && gameboard[i - 1][o + 1] == gameboard[i - 2][o + 2] && gameboard[i - 2][o + 2] == gameboard[i - 3][o + 3]) {
                            return true;
                        }

                        //horizontal
                        if (o <= sidelength - 4) {
                            if (gameboard[i][o] == gameboard[i][o + 1] && gameboard[i][o + 1] == gameboard[i][o + 2] && gameboard[i][o + 2] == gameboard[i][o + 3]) {
                                return true;
                            }
                        }
                        //vertical
                        if (i <= sidelength - 4) {
                            if (gameboard[i][o] == gameboard[i + 1][o] && gameboard[i + 1][o] == gameboard[i + 2][o] && gameboard[i + 2][o] == gameboard[i + 3][o]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        else{
            for(int i=0; i<sidelength;i++){
                for(int o=0; o<sidelength;o++){
                    if(gameboard[i][o]!=0) {
                        //diagonal sl-1
                        if (i < 5 && o < 5 && gameboard[i][o] == gameboard[i + 1][o + 1] && gameboard[i + 1][o + 1] == gameboard[i + 2][o + 2] && gameboard[i + 2][o + 2] == gameboard[i + 3][o + 3] && gameboard[i+3][o+3]==gameboard[i+4][o+4]) {
                            return true;
                        }
                        //diagonal sl+1
                        if (i>=5 && o<5 && gameboard[i][o] == gameboard[i - 1][o + 1] && gameboard[i - 1][o + 1] == gameboard[i - 2][o + 2] && gameboard[i - 2][o + 2] == gameboard[i - 3][o + 3] && gameboard[i-3][o+3]==gameboard[i-4][o+4]) {
                            return true;
                        }

                        //horizontal
                        if (o <= sidelength - 5) {
                            if (gameboard[i][o] == gameboard[i][o + 1] && gameboard[i][o + 1] == gameboard[i][o + 2] && gameboard[i][o + 2] == gameboard[i][o + 3] && gameboard[i][o+3] == gameboard[i][o+4]) {
                                return true;3
                            }
                        }
                        //vertical
                        if (i <= sidelength - 5) {
                            if (gameboard[i][o] == gameboard[i + 1][o] && gameboard[i + 1][o] == gameboard[i + 2][o] && gameboard[i + 2][o] == gameboard[i + 3][o] && gameboard[i+3][o] == gameboard[i+4][o]) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    static void gameprocess(JFrame game_window, int[][] gameboard, JButton[][] buttonboard, String name1, String name2, int sidelength, int row, int col){

        int player = 0;
        String winname = null;
        if (turn%2==0){player = 1; winname = name1;}
        else if (turn%2==1){player = 2; winname = name2;}

        ImageIcon player1icon;
        ImageIcon player2icon;

        if(sidelength==3) {
            player1icon = new ImageIcon(Main.class.getResource("images/backdi3.png"));
            player2icon = new ImageIcon(Main.class.getResource("images/backem3.png"));
        }

        else if(sidelength==6){
            player1icon = new ImageIcon(Main.class.getResource("images/6x6_di.png"));
            player2icon = new ImageIcon(Main.class.getResource("images/6x6_em.png"));
        }
        else{
            player1icon = new ImageIcon(Main.class.getResource("images/9x9_di.png"));
            player2icon = new ImageIcon(Main.class.getResource("images/9x9_em.png"));
        }

        if(gameboard[row][col]==0){
            if(turn%2==0){
                gameboard[row][col] = 1;
                buttonboard[row][col].setIcon(player1icon);
                sound("audio/stone_click.wav");
            }
            else if(turn%2==1){
                gameboard[row][col] = 2;
                buttonboard[row][col].setIcon(player2icon);
                sound("audio/block_sfx.wav");

            }
            turn++;
        }
        else {
            JDialog invpopup = new JDialog(game_window,"Notification");
            JLabel notification = new JLabel("Invalid Location!");
            notification.setFont(new Font("Arial",Font.BOLD,56));
            notification.setAlignmentX(Component.CENTER_ALIGNMENT);
            invpopup.add(notification);
            invpopup.setSize(500,150);
            invpopup.setVisible(true);
        }

        buttonboard[row][col].revalidate();

        if (winCheck(player, sidelength, gameboard)){
            System.out.println("WIN!");
            JDialog winpopup = new JDialog(game_window,"Notification");
            JLabel notification = new JLabel(winname+" Wins!");
            notification.setFont(new Font("Arial",Font.BOLD,56));
            notification.setAlignmentX(Component.CENTER_ALIGNMENT);
            winpopup.add(notification);
            winpopup.setSize(500,150);
            winpopup.setVisible(true);
        }


    }

    static void gamewindow(String name1, String name2, String boardsizechoice){

        JFrame game_window = new JFrame();
        game_window.setSize(800,800);


        int sidelength = 0;
        if (boardsizechoice.equals("3x3 (connect 3)")){sidelength = 3;}
        else if(boardsizechoice.equals("6x6 (connect 4)")){sidelength = 6;}
        else {sidelength = 9;}

        int[][] gameboard = new int[sidelength][sidelength];
        JButton[][] buttonboard = new JButton[sidelength][sidelength];

        ImageIcon buttonBg = new ImageIcon(Main.class.getResource("images/stone.png"));
        game_window.setIconImage(buttonBg.getImage());

        for (int row=0; row<sidelength; row++){
            for (int col=0; col<sidelength; col++) {
                buttonboard[row][col] = new JButton();
                //buttonboard[row][col].setBackground(Color.WHITE);
                buttonboard[row][col].setBorder(BorderFactory.createLineBorder(new Color(107,178,166)));
                buttonboard[row][col].setOpaque(false);
                buttonboard[row][col].setContentAreaFilled(true);
                if (boardsizechoice.equals("3x3 (connect 3)")){ImageIcon image = new ImageIcon(Main.class.getResource("images/stone.png"));
                buttonboard[row][col].setIcon(image);}
                else if(boardsizechoice.equals("6x6 (connect 4)")){ImageIcon image = new ImageIcon(Main.class.getResource("images/6x6_stone.png"));
                buttonboard[row][col].setIcon(image);}
                else {ImageIcon image = new ImageIcon(Main.class.getResource("images/9x9_stone.png"));
                buttonboard[row][col].setIcon(image);}



                int finalSidelength = sidelength;
                int finalCol = col;
                int finalRow = row;

                buttonboard[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameprocess(game_window, gameboard, buttonboard, name1, name2, finalSidelength, finalRow, finalCol);
                    }
                });

                game_window.add(buttonboard[row][col]);
            }
        }

        game_window.setLayout(new GridLayout(sidelength,sidelength));
        game_window.setVisible(true);
        sound("audio/board_start.wav");


        //System.out.println(name1 + name2 + boardsizechoice);
    }

    static void init(){

        sound("audio/menu_music.wav");
        String[] board_sizes = {"3x3 (connect 3)","6x6 (connect 4)","9x9 (connect 5)"};

        JFrame main_window = new JFrame();
        main_window.getContentPane().setBackground(new Color(182,211,175));
        main_window.setSize(400,300);

        //main_window.addWindowListener(new WindowAdapter() {
        //    public void windowClosed (WindowEvent e) {
        //     clip.stop();
        //    }
        //});

        main_window.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                sound("audio/button_click.wav");
            }
        });

        JPanel mainmenupanel = new JPanel();
        mainmenupanel.setLayout(new BoxLayout(mainmenupanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Tic Tac Toe Pro Max Ultra");
        title.setFont(new Font("Arial",Font.BOLD,26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainmenupanel.add(title);

        JLabel option = new JLabel("Choose size of board:");
        option.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainmenupanel.add(option);

        final JComboBox boardsizeoptions = new JComboBox(board_sizes);
        mainmenupanel.add(boardsizeoptions);
        boardsizeoptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                sound("audio/button_click.wav");
            }
        });


        JTextField player1name = new JTextField("Input player 1 name:");
        JTextField player2name = new JTextField("Input player 2 name:");
        player1name.setSize(150,20);
        player2name.setSize(150,20);

        mainmenupanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainmenupanel.add(player1name);
        mainmenupanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainmenupanel.add(player2name);
        mainmenupanel.add(Box.createRigidArea(new Dimension(0,20)));

        JButton start_game = new JButton("Start game");
        start_game.setBackground(new Color(107,178,166));
        start_game.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainmenupanel.add(start_game);
        mainmenupanel.add(Box.createRigidArea(new Dimension(0,20)));

        start_game.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turn = 0;
                String name1 = player1name.getText();
                String name2 = player2name.getText();
                sound("audio/button_click.wav");
                String boardsizechoice = ""+boardsizeoptions.getItemAt(boardsizeoptions.getSelectedIndex());
                gamewindow(name1,name2,boardsizechoice);
            }
        });

        main_window.add(mainmenupanel);
        main_window.setVisible(true);


    }

    public static void main (String[] args){

        init();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}