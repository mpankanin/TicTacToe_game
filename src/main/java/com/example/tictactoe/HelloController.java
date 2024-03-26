package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    public AnchorPane anchorPane;
    public FlowPane flowPane1, flowPane2, flowPane3, flowPane4, flowPane5, flowPane6, flowPane7, flowPane8, flowPane9;
    public Pane pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8, pane9;
    public ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;


    private int numberOfTable, numberOfPcTable;
    private int player = 0;

    Image circleImage = new Image(new File("A:\\Projekty\\TicTacToe\\src\\Circle.png").toURI().toString());
    Image crossImage = new Image(new File("A:\\Projekty\\TicTacToe\\src\\Cross.png").toURI().toString());

    private int[][] gameTable = new int[3][3];
    private boolean gameWon;

    public Button button11, button12, button13, button14, button15, button16, button17, button18, button19;
    ArrayList<Button> buttons1;
    public Button button21, button22, button23, button24, button25, button26, button27, button28, button29;
    ArrayList<Button> buttons2;
    public Button button31, button32, button33, button34, button35, button36, button37, button38, button39;
    ArrayList<Button> buttons3;
    public Button button41, button42, button43, button44, button45, button46, button47, button48, button49;
    ArrayList<Button> buttons4;
    public Button button51, button52, button53, button54, button55, button56, button57, button58, button59;
    ArrayList<Button> buttons5;
    public Button button61, button62, button63, button64, button65, button66, button67, button68, button69;
    ArrayList<Button> buttons6;
    public Button button71, button72, button73, button74, button75, button76, button77, button78, button79;
    ArrayList<Button> buttons7;
    public Button button81, button82, button83, button84, button85, button86, button87, button88, button89;
    ArrayList<Button> buttons8;
    public Button button91, button92, button93, button94, button95, button96, button97, button98, button99;
    ArrayList<Button> buttons9;
    public Button player1, player2, player1Open, player1Closed, player2Open, player2Closed;
    public CheckBox gameHistory;
    public Text ticTacToeText, historyDisplayer;
    private String gameHistoryString = "waiting for moves";
    private String lastMove;

    HashMap<Integer, ArrayList<Button>> buttonMap;
    HashMap<Integer, Boolean> endedMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons1 = new ArrayList<>(Arrays.asList(button11, button12, button13, button14, button15, button16, button17, button18, button19));
        buttons2 = new ArrayList<>(Arrays.asList(button21, button22, button23, button24, button25, button26, button27, button28, button29));
        buttons3 = new ArrayList<>(Arrays.asList(button31, button32, button33, button34, button35, button36, button37, button38, button39));
        buttons4 = new ArrayList<>(Arrays.asList(button41, button42, button43, button44, button45, button46, button47, button48, button49));
        buttons5 = new ArrayList<>(Arrays.asList(button51, button52, button53, button54, button55, button56, button57, button58, button59));
        buttons6 = new ArrayList<>(Arrays.asList(button61, button62, button63, button64, button65, button66, button67, button68, button69));
        buttons7 = new ArrayList<>(Arrays.asList(button71, button72, button73, button74, button75, button76, button77, button78, button79));
        buttons8 = new ArrayList<>(Arrays.asList(button81, button82, button83, button84, button85, button86, button87, button88, button89));
        buttons9 = new ArrayList<>(Arrays.asList(button91, button92, button93, button94, button95, button96, button97, button98, button99));

        buttonMap = new HashMap<Integer, ArrayList<Button>>();
        buttonMap.put(1, buttons1);
        buttonMap.put(2, buttons2);
        buttonMap.put(3, buttons3);
        buttonMap.put(4, buttons4);
        buttonMap.put(5, buttons5);
        buttonMap.put(6, buttons6);
        buttonMap.put(7, buttons7);
        buttonMap.put(8, buttons8);
        buttonMap.put(9, buttons9);

        historyDisplayer.setText(gameHistoryString);

        endedMap = new HashMap<Integer, Boolean>();
        for (int i = 1; i <= 9; i++) {
            endedMap.put(i, false);
        }

        for (int i = 1; i <= 9; i++) {
            buttonMap.get(i).forEach(button -> {
                button.setDisable(true);
            });
        }
    }

    private void setupButton1o(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            button.setText("O");
            button.setDisable(true);
            lastMove = ("o" + button.getId().substring(6, 8));
            gameHistoryAdd();
            checkIfGameIsOverLittle();
            checkIfGameIsOverHuge();

            numberOfTable = Integer.parseInt(button.getId().substring(6, 7));
            numberOfTable = choosingTable(numberOfTable);
            computerTurnO(buttonMap.get(numberOfTable));

        });
    }
    private void setupButton1c(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            button.setText("O");
            button.setDisable(true);
            lastMove = ("o" + button.getId().substring(6, 8));
            gameHistoryAdd();
            checkIfGameIsOverLittle();
            checkIfGameIsOverHuge();

            numberOfTable = Integer.parseInt(button.getId().substring(7, 8));
            System.out.println("PLAYER ENDEDMAP I=" + numberOfTable + " VALUE:" + endedMap.get(numberOfTable));
            System.out.println("BEFORE: " + numberOfTable);
            numberOfTable = choosingTable(numberOfTable);
            System.out.println("AFTER " + numberOfTable);
            switch (numberOfTable) {
                case 1 -> unlockingRoll(buttons1);
                case 2 -> unlockingRoll(buttons2);
                case 3 -> unlockingRoll(buttons3);
                case 4 -> unlockingRoll(buttons4);
                case 5 -> unlockingRoll(buttons5);
                case 6 -> unlockingRoll(buttons6);
                case 7 -> unlockingRoll(buttons7);
                case 8 -> unlockingRoll(buttons8);
                case 9 -> unlockingRoll(buttons9);
            }

            for (int i = 1; i <= 9; i++) {
                buttonMap.get(i).forEach(a -> a.setDisable(true));
            }
            if (!gameWon) {
                buttonMap.get(numberOfPcTable).forEach(a -> {
                    if (!a.getText().equals("O") && !a.getText().equals("X"))
                        a.setDisable(false);
                });
            }
        });
    }
    private void setupButton2o(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOverLittle();
            checkIfGameIsOverHuge();
        });
    }
    private void setupButton2c(Button button){
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOverLittle();
            checkIfGameIsOverHuge();

            numberOfTable = Integer.parseInt(button.getId().substring(7, 8));
            numberOfTable = choosingTable(numberOfTable);

            for (int i = 1; i <= 9; i++) {
                buttonMap.get(i).forEach(a -> a.setDisable(true));
            }

            if(!gameWon) {
                buttonMap.get(numberOfTable).forEach(arrayButton -> {
                    if ((!arrayButton.getText().equals("O") && !arrayButton.getText().equals("X")))
                        arrayButton.setDisable(false);
                });
            }
        });
    }

    private void computerTurnC(ArrayList<Button> arrayList) {
        Button buttonTmp = arrayList.get((int) (Math.random() * 9));
        if (!buttonTmp.isDisable()) {
            buttonTmp.setText("X");
            buttonTmp.setDisable(true);
            lastMove = ("x" + buttonTmp.getId().substring(6, 8));
            gameHistoryAdd();
            checkIfGameIsOverLittle();
            checkIfGameIsOverHuge();
            numberOfPcTable = Integer.parseInt(buttonTmp.getId().substring(7, 8));
            System.out.println("ENDEDMAP I=" + numberOfPcTable + " VALUE:" + endedMap.get(numberOfPcTable));
            System.out.println("BEFORE PC: " + numberOfPcTable);
            numberOfPcTable = choosingTable(numberOfPcTable);
            System.out.println("AFTER PC: " + numberOfPcTable);
            System.out.println("/-------------------------/");
        } else
            computerTurnC(arrayList);
    }
    private void computerTurnO(ArrayList<Button> arrayList) {
        Button buttonTmp = arrayList.get((int) (Math.random() * 9));
        if (!buttonTmp.isDisable()) {
            buttonTmp.setText("X");
            buttonTmp.setDisable(true);
            lastMove = ("x" + buttonTmp.getId().substring(6, 8));
            gameHistoryAdd();
            checkIfGameIsOverLittle();
            checkIfGameIsOverHuge();
        } else
            computerTurnO(arrayList);
    }

    private int choosingTable(int number) {
        System.out.println("CT:IN: " + number);
        if (endedMap.get(number).equals(true)) {
            System.out.println("LOOP1: OK");
            for (int i = 1; i <= 9; i++) {
                System.out.println("I: " + i);
                if (!endedMap.get(i).equals(true)) {
                    System.out.println("RETURNIG: " + i);
                    return i;
                }
            }
        }
        System.out.println("RETURNING WITHOUT LOOP: " + number);
        return number;
    }
    private void unlockingRoll(ArrayList<Button> arrayList) {
        arrayList.forEach(arrayButton -> {
            if ((!arrayButton.getText().equals("O") && !arrayButton.getText().equals("X")))
                arrayButton.setDisable(false);
        });
        computerTurnC(arrayList);
        arrayList.forEach(arrayButton -> arrayButton.setDisable(true));
    }

    public void setPlayerSymbol(Button button) {
        if (player % 2 == 0) {
            button.setText("O");
            lastMove = ("o" + button.getId().substring(6, 8));
            gameHistoryAdd();
            ticTacToeText.setText("Turn: X");
            player = 1;
        } else {
            button.setText("X");
            lastMove = ("x" + button.getId().substring(6, 8));
            gameHistoryAdd();
            ticTacToeText.setText("Turn: O");
            player = 0;
        }
    }

    public void checkIfGameIsOver1() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button11.getText() + button12.getText() + button13.getText();
                case 1 -> button14.getText() + button15.getText() + button16.getText();
                case 2 -> button17.getText() + button18.getText() + button19.getText();
                case 3 -> button11.getText() + button15.getText() + button19.getText();
                case 4 -> button13.getText() + button15.getText() + button17.getText();
                case 5 -> button11.getText() + button14.getText() + button17.getText();
                case 6 -> button12.getText() + button15.getText() + button18.getText();
                case 7 -> button13.getText() + button16.getText() + button19.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons1.forEach(button -> button.setDisable(true));
                imageView1.setImage(crossImage);
                pane1.setVisible(true);
                gameTable[0][0] = -1;
                endedMap.replace(1, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons1.forEach(button -> button.setDisable(true));
                imageView1.setImage(circleImage);
                pane1.setVisible(true);
                gameTable[0][0] = 1;
                endedMap.replace(1, true);
            }
        }
    }
    public void checkIfGameIsOver2() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button21.getText() + button22.getText() + button23.getText();
                case 1 -> button24.getText() + button25.getText() + button26.getText();
                case 2 -> button27.getText() + button28.getText() + button29.getText();
                case 3 -> button21.getText() + button25.getText() + button29.getText();
                case 4 -> button23.getText() + button25.getText() + button27.getText();
                case 5 -> button21.getText() + button24.getText() + button27.getText();
                case 6 -> button22.getText() + button25.getText() + button28.getText();
                case 7 -> button23.getText() + button26.getText() + button29.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons2.forEach(button -> button.setDisable(true));
                imageView2.setImage(crossImage);
                pane2.setVisible(true);
                gameTable[0][1] = -1;
                endedMap.replace(2, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons2.forEach(button -> button.setDisable(true));
                imageView2.setImage(circleImage);
                pane2.setVisible(true);
                gameTable[0][1] = 1;
                endedMap.replace(2, true);
            }
        }
    }
    public void checkIfGameIsOver3() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button31.getText() + button32.getText() + button33.getText();
                case 1 -> button34.getText() + button35.getText() + button36.getText();
                case 2 -> button37.getText() + button38.getText() + button39.getText();
                case 3 -> button31.getText() + button35.getText() + button39.getText();
                case 4 -> button33.getText() + button35.getText() + button37.getText();
                case 5 -> button31.getText() + button34.getText() + button37.getText();
                case 6 -> button32.getText() + button35.getText() + button38.getText();
                case 7 -> button33.getText() + button36.getText() + button39.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons3.forEach(button -> button.setDisable(true));
                imageView3.setImage(crossImage);
                pane3.setVisible(true);
                gameTable[0][2] = -1;
                endedMap.replace(3, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons3.forEach(button -> button.setDisable(true));
                imageView3.setImage(circleImage);
                pane3.setVisible(true);
                gameTable[0][2] = 1;
                endedMap.replace(3, true);
            }
        }
    }
    public void checkIfGameIsOver4() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button41.getText() + button42.getText() + button43.getText();
                case 1 -> button44.getText() + button45.getText() + button46.getText();
                case 2 -> button47.getText() + button48.getText() + button49.getText();
                case 3 -> button41.getText() + button45.getText() + button49.getText();
                case 4 -> button43.getText() + button45.getText() + button47.getText();
                case 5 -> button41.getText() + button44.getText() + button47.getText();
                case 6 -> button42.getText() + button45.getText() + button48.getText();
                case 7 -> button43.getText() + button46.getText() + button49.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons4.forEach(button -> button.setDisable(true));
                imageView4.setImage(crossImage);
                pane4.setVisible(true);
                gameTable[1][0] = -1;
                endedMap.replace(4, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons4.forEach(button -> button.setDisable(true));
                imageView4.setImage(circleImage);
                pane4.setVisible(true);
                gameTable[1][0] = 1;
                endedMap.replace(4, true);
            }
        }
    }
    public void checkIfGameIsOver5() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button51.getText() + button52.getText() + button53.getText();
                case 1 -> button54.getText() + button55.getText() + button56.getText();
                case 2 -> button57.getText() + button58.getText() + button59.getText();
                case 3 -> button51.getText() + button55.getText() + button59.getText();
                case 4 -> button53.getText() + button55.getText() + button57.getText();
                case 5 -> button51.getText() + button54.getText() + button57.getText();
                case 6 -> button52.getText() + button55.getText() + button58.getText();
                case 7 -> button53.getText() + button56.getText() + button59.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons5.forEach(button -> button.setDisable(true));
                imageView5.setImage(crossImage);
                pane5.setVisible(true);
                gameTable[1][1] = -1;
                endedMap.replace(5, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons5.forEach(button -> button.setDisable(true));
                imageView5.setImage(circleImage);
                pane5.setVisible(true);
                gameTable[1][1] = 1;
                endedMap.replace(5, true);
            }
        }
    }
    public void checkIfGameIsOver6() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button61.getText() + button62.getText() + button63.getText();
                case 1 -> button64.getText() + button65.getText() + button66.getText();
                case 2 -> button67.getText() + button68.getText() + button69.getText();
                case 3 -> button61.getText() + button65.getText() + button69.getText();
                case 4 -> button63.getText() + button65.getText() + button67.getText();
                case 5 -> button61.getText() + button64.getText() + button67.getText();
                case 6 -> button62.getText() + button65.getText() + button68.getText();
                case 7 -> button63.getText() + button66.getText() + button69.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons6.forEach(button -> button.setDisable(true));
                imageView6.setImage(crossImage);
                pane6.setVisible(true);
                gameTable[1][2] = -1;
                endedMap.replace(6, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons6.forEach(button -> button.setDisable(true));
                imageView6.setImage(circleImage);
                pane6.setVisible(true);
                gameTable[1][2] = 1;
                endedMap.replace(6, true);
            }
        }
    }
    public void checkIfGameIsOver7() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button71.getText() + button72.getText() + button73.getText();
                case 1 -> button74.getText() + button75.getText() + button76.getText();
                case 2 -> button77.getText() + button78.getText() + button79.getText();
                case 3 -> button71.getText() + button75.getText() + button79.getText();
                case 4 -> button73.getText() + button75.getText() + button77.getText();
                case 5 -> button71.getText() + button74.getText() + button77.getText();
                case 6 -> button72.getText() + button75.getText() + button78.getText();
                case 7 -> button73.getText() + button76.getText() + button79.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons7.forEach(button -> button.setDisable(true));
                imageView7.setImage(crossImage);
                pane7.setVisible(true);
                gameTable[2][0] = -1;
                endedMap.replace(7, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons7.forEach(button -> button.setDisable(true));
                imageView7.setImage(circleImage);
                pane7.setVisible(true);
                gameTable[2][0] = 1;
                endedMap.replace(7, true);
            }
        }
    }
    public void checkIfGameIsOver8() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button81.getText() + button82.getText() + button83.getText();
                case 1 -> button84.getText() + button85.getText() + button86.getText();
                case 2 -> button87.getText() + button88.getText() + button89.getText();
                case 3 -> button81.getText() + button85.getText() + button89.getText();
                case 4 -> button83.getText() + button85.getText() + button87.getText();
                case 5 -> button81.getText() + button84.getText() + button87.getText();
                case 6 -> button82.getText() + button85.getText() + button88.getText();
                case 7 -> button83.getText() + button86.getText() + button89.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons8.forEach(button -> button.setDisable(true));
                imageView8.setImage(crossImage);
                pane8.setVisible(true);
                gameTable[2][1] = -1;
                endedMap.replace(8, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons8.forEach(button -> button.setDisable(true));
                imageView8.setImage(circleImage);
                pane8.setVisible(true);
                gameTable[2][1] = 1;
                endedMap.replace(8, true);
            }
        }
    }
    public void checkIfGameIsOver9() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button91.getText() + button92.getText() + button93.getText();
                case 1 -> button94.getText() + button95.getText() + button96.getText();
                case 2 -> button97.getText() + button98.getText() + button99.getText();
                case 3 -> button91.getText() + button95.getText() + button99.getText();
                case 4 -> button93.getText() + button95.getText() + button97.getText();
                case 5 -> button91.getText() + button94.getText() + button97.getText();
                case 6 -> button92.getText() + button95.getText() + button98.getText();
                case 7 -> button93.getText() + button96.getText() + button99.getText();
                default -> null;
            };

            //X winner
            if (line.equals("XXX")) {
                buttons9.forEach(button -> button.setDisable(true));
                imageView9.setImage(crossImage);
                pane9.setVisible(true);
                gameTable[2][2] = -1;
                endedMap.replace(9, true);
            }

            //O winner
            else if (line.equals("OOO")) {
                buttons9.forEach(button -> button.setDisable(true));
                imageView9.setImage(circleImage);
                pane9.setVisible(true);
                gameTable[2][2] = 1;
                endedMap.replace(9, true);
            }
        }
    }

    public void checkIfGameIsOverLittle() {
        checkIfGameIsOver1();
        checkIfGameIsOver2();
        checkIfGameIsOver3();
        checkIfGameIsOver4();
        checkIfGameIsOver5();
        checkIfGameIsOver6();
        checkIfGameIsOver7();
        checkIfGameIsOver8();
        checkIfGameIsOver9();
    }
    public void checkIfGameIsOverHuge() {
        for (int a = 0; a < 8; a++) {
            int counter = switch (a) {
                case 0 -> gameTable[0][0] + gameTable[0][1] + gameTable[0][2];
                case 1 -> gameTable[1][0] + gameTable[1][1] + gameTable[1][2];
                case 2 -> gameTable[2][0] + gameTable[2][1] + gameTable[2][2];
                case 3 -> gameTable[0][0] + gameTable[1][0] + gameTable[2][0];
                case 4 -> gameTable[0][1] + gameTable[1][1] + gameTable[2][1];
                case 5 -> gameTable[0][2] + gameTable[1][2] + gameTable[2][2];
                case 6 -> gameTable[0][0] + gameTable[1][1] + gameTable[2][2];
                case 7 -> gameTable[2][0] + gameTable[1][1] + gameTable[0][2];
                default -> 0;
            };

            //X winner
            if (counter == -3) {
                ticTacToeText.setText("X wins !!!");
                for (int i = 1; i <= 9; i++) {
                    buttonMap.get(i).forEach(button -> button.setDisable(true));
                }
                gameTable = new int[3][3];
                gameWon = true;
            }


            //O winner
            else if (counter == 3) {
                ticTacToeText.setText("0 wins !!!");
                for (int i = 1; i <= 9; i++) {
                    buttonMap.get(i).forEach(button -> button.setDisable(true));
                }
                gameTable = new int[3][3];
                gameWon = true;
            }

        }
    }

    public void resetButton(Button button) {
        button.setDisable(false);
        button.setText("");
    }

    @FXML
    void restartGame() {
        for (int i = 1; i <= 9; i++) {
            buttonMap.get(i).forEach(this::resetButton);
        }
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        pane6.setVisible(false);
        pane7.setVisible(false);
        pane8.setVisible(false);
        pane9.setVisible(false);
        for (int i = 1; i <= 9; i++) {
            endedMap.replace(i, false);
        }
        player = 0;
        ticTacToeText.setText("TicTacToe");
        gameTable = new int[3][3];
        gameWon = false;
        gameHistoryString = "waiting for moves";
        historyDisplayer.setText(gameHistoryString);

    }

    @FXML
    void player1game() {
        player1.setOnMouseClicked(mouseEvent -> {
            if (player2.isDisable()) {
                player2.setDisable(false);
                restartGame();
            }
            player1.setDisable(true);
            player1Open.setVisible(true);
            player1Closed.setVisible(true);
            player2Open.setVisible(false);
            player2Closed.setVisible(false);
        });
        if(player1Open.isDisable()){
            for (int i = 1; i <= 9; i++) {
                buttonMap.get(i).forEach(button -> {
                    button.setDisable(false);
                    setupButton1o(button);
                });
            }
        }
        if(player1Closed.isDisable()){
            for (int i = 1; i <= 9; i++) {
                buttonMap.get(i).forEach(button -> {
                    button.setDisable(false);
                    setupButton1c(button);
                });
            }
        }

    }

    @FXML
    void player2game() {
        player2.setOnMouseClicked(mouseEvent -> {
            if (player1.isDisable()) {
                player1.setDisable(false);
                restartGame();
            }
            player2.setDisable(true);
            player2Open.setVisible(true);
            player2Closed.setVisible(true);
            player1Open.setVisible(false);
            player1Closed.setVisible(false);
        });
        if(player2Open.isDisable()){
            for (int i = 1; i <= 9; i++) {
                buttonMap.get(i).forEach(button -> {
                    button.setDisable(false);
                    setupButton2o(button);

                });
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                buttonMap.get(i).forEach(button -> {
                    button.setDisable(false);
                    setupButton2c(button);
                });
        }
    }
    }

    @FXML
    void  setPlayer1Open(){
        player1Open.setOnMouseClicked(mouseEvent -> {
            if (player1Closed.isDisable())
                player1Closed.setDisable(false);
            player1Open.setDisable(true);
            player1game();
            restartGame();
        });
    }

    @FXML
    void  setPlayer1Closed(){
        player1Closed.setOnMouseClicked(mouseEvent -> {
            if (player1Open.isDisable())
                player1Open.setDisable(false);
            player1Closed.setDisable(true);
            player1game();
            restartGame();
        });
    }

    @FXML
    void  setPlayer2Open(){
        player2Open.setOnMouseClicked(mouseEvent -> {
            if (player2Closed.isDisable())
                player2Closed.setDisable(false);
            player2Open.setDisable(true);
            player2game();
            restartGame();
        });
    }

    @FXML
    void  setPlayer2Closed(){
        player2Closed.setOnMouseClicked(mouseEvent -> {
            if (player2Open.isDisable())
                player2Open.setDisable(false);
            player2Closed.setDisable(true);
            player2game();
            restartGame();
        });
    }

    @FXML
    void showGameHistory(){
        historyDisplayer.setVisible(gameHistory.isSelected());
    }

    public void gameHistoryAdd(){
        if(gameHistoryString.equals("waiting for moves"))
            gameHistoryString = lastMove;
        else {
            if (gameHistoryString.length() <= 20)

                gameHistoryString = lastMove + " " + gameHistoryString;
            else
                gameHistoryString = lastMove + " " + gameHistoryString.substring(0, 19);
            historyDisplayer.setText(gameHistoryString);
        }
    }

}
