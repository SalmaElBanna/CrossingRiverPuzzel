/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmemento;

/**
 *
 * @author Salma
 */
import Logic.Cabbage;
import Logic.Carrots;
import Logic.Farmer;
import Logic.Fox;
import Logic.Goat;
import Logic.ICrosser;
import Logic.Lion;
import Logic.Rabbit;
import fmemento.Game;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import java.util.ArrayList;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Progress {

    Game g;

    public Progress(Game g) {
        this.g = g;
    }
    
    public void SaveProgress() throws TransformerException  {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //
            // root element
            Element rootElement = doc.createElement("story1");
            doc.appendChild(rootElement);
            //
            // numofmoves element
            Element numofmoves = doc.createElement("numofmoves");
            rootElement.appendChild(numofmoves);
            //
            // lefttbankposition element
            Element leftbankposition = doc.createElement("leftbankposition");
            rootElement.appendChild(leftbankposition);

            numofmoves.setTextContent(Integer.toString(g.score));

            if (g.position) {
                leftbankposition.setTextContent("true");
            } else {
                leftbankposition.setTextContent("false"); 
            }
            //
            // getting characters on the right bank
            int i = 0;
            while (i < g.righttbank.size() ) {
                Element rightchars = doc.createElement("rightchars");
                if (g.righttbank.get(i) instanceof Fox) {
                    rightchars.setTextContent("Fox"); 
                } else if (g.righttbank.get(i) instanceof Cabbage) {
                    rightchars.setTextContent("Cabage");
                } else if (g.righttbank.get(i) instanceof Carrots) {
                    rightchars.setTextContent("Carrots");
                } else if (g.righttbank.get(i) instanceof Farmer) {
                    rightchars.setTextContent("Farmer");
                } else if (g.righttbank.get(i) instanceof Goat) {
                    rightchars.setTextContent("Goat");
                } else if (g.righttbank.get(i) instanceof Lion) {
                    rightchars.setTextContent("Lion");
                } else if (g.righttbank.get(i) instanceof Rabbit) {
                    rightchars.setTextContent("Rabbit");
                }
                rootElement.appendChild(rightchars);
                i++; 
            }
            //
            // getting characters on the left bank
            i = 0;
            while (i < g.leftbank.size() ) {
                Element leftchars = doc.createElement("leftchars");
                if (g.leftbank.get(i) instanceof Fox) {
                    leftchars.setTextContent("Fox");
                } else if (g.leftbank.get(i) instanceof Cabbage) {
                    leftchars.setTextContent("Cabage");
                } else if (g.leftbank.get(i) instanceof Carrots) {
                    leftchars.setTextContent("Carrots");
                } else if (g.leftbank.get(i) instanceof Farmer) {
                    leftchars.setTextContent("Farmer");
                } else if (g.leftbank.get(i) instanceof Goat) {
                    leftchars.setTextContent("Goat");
                } else if (g.leftbank.get(i) instanceof Lion) {
                    leftchars.setTextContent("Lion");
                } else if (g.leftbank.get(i) instanceof Rabbit) {
                    leftchars.setTextContent("Rabbit");
                }
                rootElement.appendChild(leftchars);
                i++;
            }
            //
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("story1.xml"));
            transformer.transform(source, result);
            //
            // printing result
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Progress.class.getName()).log(Level.SEVERE, null, ex);
           printStackTrace();
        }
    }

    
    public Game getGprogress() throws ParserConfigurationException
    {
        try {
            ArrayList<ICrosser> lbcrosser = new ArrayList<>();
            ArrayList<ICrosser> rbcrosser = new ArrayList<>();
            int score = 0;
            boolean pos = false;
            Fox f = null;
            Farmer fa = null;
            Cabbage cab = null;
            Carrots car = null;
            Goat go = null;
            Lion le = null;
            Rabbit bunny = null;
            File inputFile = new File("story1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList Listleft = doc.getElementsByTagName("leftchars");
            NodeList Listright = doc.getElementsByTagName("rightchars");
            
            int j;
            for(j=0; j< Listleft.getLength() ; j++)
            {
                Node node = Listleft.item(j);
                String str = node.getTextContent();
                System.out.println(str+"\n");
                if(str.equalsIgnoreCase("Fox"))
                    lbcrosser.add(f);
                else if(str.equalsIgnoreCase("Farmer"))
                    lbcrosser.add(fa);
                else if(str.equalsIgnoreCase("Carrot"))
                    lbcrosser.add(car);
                else if(str.equalsIgnoreCase("Cabbage"))
                    lbcrosser.add(cab);
                else if(str.equalsIgnoreCase("Rabbit"))
                    lbcrosser.add(bunny);
                else if(str.equalsIgnoreCase("Goat"))
                    lbcrosser.add(go);
                else if(str.equalsIgnoreCase("Lion"))
                    lbcrosser.add(le);
            }
            
            for(j=0; j< Listright.getLength() ; j++)
            {
                Node node = Listright.item(j);
                String str = node.getTextContent();
                System.out.println(str+"\n");
                if(str.equalsIgnoreCase("Fox"))
                    rbcrosser.add(f);
                else if(str.equalsIgnoreCase("Farmer"))
                    rbcrosser.add(fa);
                else if(str.equalsIgnoreCase("Carrot"))
                    rbcrosser.add(car);
                else if(str.equalsIgnoreCase("Cabbage"))
                    rbcrosser.add(cab);
                else if(str.equalsIgnoreCase("Rabbit"))
                    rbcrosser.add(bunny);
                else if(str.equalsIgnoreCase("Goat"))
                    rbcrosser.add(go);
                else if(str.equalsIgnoreCase("Lion"))
                    rbcrosser.add(le);
            }
            
            NodeList placeplace = doc.getElementsByTagName("leftbankposition");
            
            for(j=0;j<placeplace.getLength();j++)
            {
                Node n = placeplace.item(j);
                String str = n.getTextContent();
                System.out.println(str+"\n");
                pos = str.equalsIgnoreCase("true"); 
            }
            
            NodeList mylist = doc.getElementsByTagName("numofmoves");
            
            for(j=0; j<mylist.getLength(); j++)
            {
                Node node = mylist.item(j);
                String str = node.getTextContent();
                score = Integer.parseInt(str);
                System.out.println(score+"\n");
            }

           Game game = new Game(score, pos,lbcrosser, rbcrosser);
           return game;
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Progress.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
