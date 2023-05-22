package sk.stuba.fei.uim.oop.okno;

import sk.stuba.fei.uim.oop.logika.LogikaHry;

import javax.swing.*;
import java.awt.*;

public class Aplikacia {
    public Aplikacia(){
        JFrame okno = new JFrame();
        okno.setSize(new Dimension(800,800));
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LogikaHry logika = new LogikaHry(okno);
        JButton strom = new JButton("Strom");
        strom.setFocusable(false);
        strom.addActionListener(logika);

        JButton presun = new JButton("Presun");
        presun.setFocusable(false);
        presun.addActionListener(logika);

        JButton dalsiaFarba = new JButton("Ďalšia farba");
        dalsiaFarba.setFocusable(false);
        dalsiaFarba.addActionListener(logika);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(2,2));
        menu.add(strom);
        menu.add(presun);
        menu.add(dalsiaFarba);
        menu.add(logika.getStavVypis());

        okno.add(menu, BorderLayout.PAGE_END);
        okno.setVisible(true);
    }
}
