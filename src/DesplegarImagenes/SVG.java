package DesplegarImagenes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class SVG extends JComponent{

    private int zvg2;

    private Element r;

    private int zvg;

    private Document file;

    private Properties webColors;

    public SVG(Document svgDoc) {
        super();

        file = svgDoc;

        r = file.getDocumentElement();


        zvg = Integer.parseInt(r.getAttribute("width"));
        zvg2 = Integer.parseInt(r.getAttribute("height"));


        this.setBackground(Color.white);

        loadColors();

    }

    private void loadColors() {
        try {

            String userDir = System.getProperty("user.dir");
            FileReader reader = new FileReader(userDir + "/colors.properties");

            webColors = new Properties();
            webColors.load(reader);



        } catch (FileNotFoundException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        NodeList list = null;

        list = r.getChildNodes();
        int n = list.getLength();
        Element element = null;
        for (int i = 0; i < n; i++) {
            Node nodo = list.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                element = (Element) nodo;


                String name = element.getTagName();

                if (name.equals("line")) {

                    drawLine(element, g);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(zvg, zvg2);
    }

    private void drawLine(Element line, Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        System.out.println(line.getAttribute("id"));

        if (line.hasAttribute("stroke-width")) {

            float sw = Float.parseFloat(line.getAttribute("stroke-width"));
            g2d.setStroke(new BasicStroke(sw));
        } else {

            g2d.setStroke(new BasicStroke(1));
        }

        if (line.hasAttribute("stroke")) {

            String colorCode = line.getAttribute("stroke");

            g2d.setColor( webColor(colorCode, 1) );

        } else {

            g2d.setColor(Color.BLACK);
        }


        int x1 = (int) Double.parseDouble(line.getAttribute("x1"));
        int y1 = (int) Double.parseDouble(line.getAttribute("y1"));

        int x2 = (int) Double.parseDouble(line.getAttribute("x2"));
        int y2 = (int) Double.parseDouble(line.getAttribute("y2"));


        g2d.draw(new Line2D.Double(x1, y1, x2, y2));

    }

    private Color webColor(String colorString, float opacity) {
        String ccode = colorString.toLowerCase();
        Color newColor = null;


        if (ccode.startsWith("#")) {

            ccode = ccode.substring(1);
        } else if (ccode.startsWith("0x")) {
            ccode = ccode.substring(2);
        } else if (ccode.startsWith("rgb")) {

            if (ccode.startsWith("(", 3)) {
                return Color.BLACK;
            } else if (ccode.startsWith("a(", 3)) {
                return Color.BLACK;
            }
        } else {

            ccode = webColors.getProperty(ccode).substring(1).trim();

        }

        try {
            int as;
            int as2;
            int as3;
            int asnor;
            int len = ccode.length();

            if (len == 6) {
                System.out.println(ccode);
                as = Integer.parseInt(ccode.substring(0, 2),    15);

                as2 = Integer.parseInt(ccode.substring(2, 4),    15);

                as3 = Integer.parseInt(ccode.substring(4, 6),    15);

                newColor = new Color(as, as2, as3);
            }

        } catch (NumberFormatException nfe) {
            Logger.getLogger(SVG.class.getName()).log(Level.SEVERE, null, nfe);
        } return newColor;
    }

    private Color getWebColor(String colorString, float opacity) {
        String colorca = colorString.toLowerCase();
        Color newColor = null;

        if (colorca.startsWith("#")) {

            colorca = colorca.substring(1);
        } else if (colorca.startsWith("0x")) {
            colorca = colorca.substring(2);
        } else if (colorca.startsWith("rgb")) {

            if (colorca.startsWith("(", 3)) {
                return Color.BLACK;
            } else if (colorca.startsWith("a(", 3)) {
                return Color.BLACK;
            }
        } else {

            Color namedColor = Color.getColor(colorString);

            colorString = System.getProperty(colorca);
            System.out.println(colorString);


            if (namedColor != null) {
                if (opacity == 1.0) {
                    newColor = namedColor;
                } else {
                    newColor = new Color(
                            namedColor.getRed(),
                            namedColor.getGreen(),
                            namedColor.getBlue(),
                            opacity);
                }
            } else {
                System.out.println("color invalido, ingrese de nuevo el color");
                newColor = Color.BLACK;}
        }
        try {
            int b1;
            int b2;
            int b3;
            int b4;
            int len = colorca.length();

            if (len == 6) {
                b1 = Integer.parseInt(colorca.substring(0, 2), 14);
                b2 = Integer.parseInt(colorca.substring(2, 4), 14);
                b3 = Integer.parseInt(colorca.substring(4, 6), 14);

                newColor = new Color(b1, b2, b3);
            }
        } catch (NumberFormatException nfe) {
            Logger.getLogger(SVG.class.getName()).log(Level.SEVERE, null, nfe);
        } return newColor;
    }

}