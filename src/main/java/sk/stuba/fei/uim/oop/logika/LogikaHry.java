package sk.stuba.fei.uim.oop.logika;

import lombok.Getter;
import sk.stuba.fei.uim.oop.doska.Doska;
import sk.stuba.fei.uim.oop.tvar.Strom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class LogikaHry extends UniverzalnyAdapter{
    private JFrame okno;
    private final String STROM = "Strom";
    private final String PRESUN = "Presun";
    private final String DALSIAFARBA = "Ďalšia farba";
    @Getter
    private JLabel stavVypis;

    private Doska doska;
    private Color farba;
    private Strom strom;

    private String stav;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int klikX;
    private int klikY;
    private Strom oznacenyStrom;

    public LogikaHry(JFrame okno){
        stav = STROM;
        this.farba = Color.BLUE;
        this.okno = okno;
        stavVypis = new JLabel("    KRESLENIE");
        stavVypis.setOpaque(true);
        stavVypis.setBackground(Color.BLUE);
        this.doska = new Doska();
        this.okno.add(doska);
        this.doska.addMouseListener(this);
        this.doska.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(stav.equals(STROM)){
            this.strom = new Strom(e.getX(),e.getY(), farba);
            doska.pridajStrom(this.strom);
            this.strom.addMouseListener(this);
            this.strom.addMouseMotionListener(this);
        }
        if(stav.equals(PRESUN)){
            for(int i=this.doska.getStromy().size()-1; i>=0; i--){
                if(this.doska.getStromy().get(i).getPozicia().contains(e.getX(),e.getY())){
                    klikX = e.getX();
                    klikY = e.getY();
                    oznacenyStrom = this.doska.getStromy().get(i);
                    x1 = oznacenyStrom.getBod1x();
                    x2 = oznacenyStrom.getBod2x();
                    y1 = oznacenyStrom.getBod1y();
                    y2 = oznacenyStrom.getBod2y();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(stav.equals(STROM)){
            this.strom.nastavDruhyBod(e.getX(),e.getY());
        }
        if(stav.equals(PRESUN) && oznacenyStrom!=null){
            int posunX = e.getX()-klikX;
            int posunY = e.getY()-klikY;
            oznacenyStrom.setBod1x(x1+posunX);
            oznacenyStrom.setBod1y(y1+posunY);
            oznacenyStrom.setBod2x(x2+posunX);
            oznacenyStrom.setBod2y(y2+posunY);
        }
        this.doska.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(stav.equals(PRESUN) && oznacenyStrom!=null){
            oznacenyStrom = null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case STROM:
                stavVypis.setText("    KRESLENIE");
                stav = STROM;
                break;
            case PRESUN:
                stavVypis.setText("    PRESUVANIE");
                stav = PRESUN;
                break;
            case DALSIAFARBA:
                zmenFarbu();
                break;
        }
    }

    private void zmenFarbu(){
        if(this.farba == Color.BLUE){
            stavVypis.setBackground(Color.RED);
            this.farba = Color.RED;
        }
        else if(this.farba == Color.RED){
            stavVypis.setBackground(Color.ORANGE);
            this.farba = Color.ORANGE;
        }
        else if(this.farba == Color.ORANGE){
            stavVypis.setBackground(Color.BLUE);
            this.farba = Color.BLUE;
        }
    }
}
