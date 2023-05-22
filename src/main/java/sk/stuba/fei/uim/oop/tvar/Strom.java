package sk.stuba.fei.uim.oop.tvar;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.abs;

public class Strom extends JPanel {
    @Getter @Setter
    int bod1x;
    @Getter @Setter
    int bod1y;
    @Getter @Setter
    int bod2x;
    @Getter @Setter
    int bod2y;
    double xStredKmena;
    double sirkaKmena;
    double yKoniecKmena;
    double vyskaKoruny;
    double sirkaKoruny;
    int dbod1x;
    int dbod2x;
    int dbod1y;
    int dbod2y;
    @Setter
    Color farba;
    public Strom(int x1, int y2, Color farba){
        this.farba = farba;
        this.bod1x = x1;
        this.bod1y = y2;
        this.bod2x = x1;
        this.bod2y = y2;
        setOpaque(false);
    }
    public void nakresli(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(farba);
        if(bod1x>bod2x){
            dbod1x =  bod2x;
            dbod2x =  bod1x;
        }
        else{
            dbod1x =  bod1x;
            dbod2x =  bod2x;
        }
        if(bod1y>bod2y){
            dbod1y =  bod2y;
            dbod2y =  bod1y;
        }
        else{
            dbod1y =  bod1y;
            dbod2y =  bod2y;
        }

        int dlzkaX = abs(dbod1x-dbod2x);
        int dlzkaY = abs(dbod1y-dbod2y);
        int dlzka;
        if(dlzkaX>dlzkaY){
            dlzka = dlzkaX;
        }
        else{
            dlzka = dlzkaY;
        }
        vyskaKoruny = dlzka*(2.0/3.0);
        sirkaKoruny = dlzka;
        g2d.fillOval(dbod1x,dbod1y,(int)sirkaKoruny,(int)vyskaKoruny);

        sirkaKmena = dlzka/3.0;
        xStredKmena = (dbod1x+(dbod1x+dlzka))/2.0 ;
        yKoniecKmena = dbod1y+dlzka-(sirkaKmena/2.0);
        g2d.setStroke(new BasicStroke((int)sirkaKmena));
        g2d.drawLine((int)xStredKmena,dbod1y+(int) sirkaKmena,(int)xStredKmena,(int)yKoniecKmena);
    }

    public void nastavDruhyBod(int x, int y){
//        System.out.println(x + " " + y);
        this.bod2x = x;
        this.bod2y = y;
    }

    public Shape getPozicia() {
        Path2D tvar = new Path2D.Double();
        Line2D usecka = new Line2D.Double((int) xStredKmena, dbod1y + (int) sirkaKmena, (int) xStredKmena, (int) yKoniecKmena);
        BasicStroke hrubka = new BasicStroke((int)sirkaKmena);
        Ellipse2D elipsa = new Ellipse2D.Double(dbod1x,dbod1y,(int)sirkaKoruny,(int)vyskaKoruny);
        tvar.append(hrubka.createStrokedShape(usecka),false);
        tvar.append(elipsa,false);
        return tvar;
    }
}
