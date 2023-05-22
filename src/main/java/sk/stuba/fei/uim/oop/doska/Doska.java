package sk.stuba.fei.uim.oop.doska;

import lombok.Getter;
import sk.stuba.fei.uim.oop.tvar.Strom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Doska extends JPanel {
    @Getter
    private ArrayList<Strom> stromy;
    public Doska(){
        this.setBackground(Color.GREEN);
        stromy = new ArrayList<>();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Strom strom : stromy){
            strom.nakresli(g);
        }
        this.repaint();
    }

    public void pridajStrom(Strom strom){
        this.stromy.add(strom);
        this.add(strom);
    }
}
