package Coloring;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Coloring {
    public static final int AMOUNT = 11;
    public static final String[] names = new String[]{"1", "2", "3", "a1", "a2", "w1", "w2", "w3", "w4", "lobby", "custScreen"};
    public static void createImages(Map<Integer, Integer> colorMap)
    {
        try {
            // Load the image
            BufferedImage[] originals = new BufferedImage[AMOUNT];
            BufferedImage[] results = new BufferedImage[AMOUNT];
            int widths[] = new int[AMOUNT];
            int heights[] = new int[AMOUNT];
            for(int i = 0; i < AMOUNT; i++)
            {
                System.out.println("Reading " + i);
                originals[i] = ImageIO.read(new File("graphs/character/original/" + names[i] + ".png"));
                System.out.println("READ " + i);
            }
            
            for(int i = 0; i < AMOUNT; i++)
            {
                widths[i] = originals[i].getWidth();
                heights[i] = originals[i].getHeight();
                results[i] = new BufferedImage(widths[i], heights[i], BufferedImage.TYPE_INT_ARGB);
            }
            
            for(int i = 0; i < AMOUNT; i++)
            {
                // Loop through all pixels
                for (int y = 0; y < heights[i]; y++) {
                    for (int x = 0; x < widths[i]; x++) {
                        int originalRGB = originals[i].getRGB(x, y);

                        // Replace color if in map
                        int newRGB = colorMap.getOrDefault(originalRGB, originalRGB);

                        // Set the new RGB value
                        results[i].setRGB(x, y, newRGB);
                    }
                }
                ImageIO.write(results[i], "png", new File("graphs/character/current/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Image[] createImagesByCommand(Color[] colors)//Skin, eyes, top, bottom
    {
        Map<Integer, Integer> colorMap = new HashMap<>();
        colorMap.put(Color.decode("#000000").getRGB(), colors[0].getRGB()); //Eyes
        colorMap.put(Color.decode("#FFB570").getRGB(), colors[1].getRGB()); //Face Big Part
        colorMap.put(Color.decode("#FF9166").getRGB(), darkenColor(colors[1]).getRGB()); //Face Shadow
        colorMap.put(Color.decode("#3E2347").getRGB(), darkenColor(colors[2]).getRGB()); //Upper Body Dark
        colorMap.put(Color.decode("#57294B").getRGB(), colors[2].getRGB()); //Upper Body Light
        colorMap.put(Color.decode("#8C3F5D").getRGB(), darkenColor(colors[3]).getRGB()); //Lower Body Dark
        colorMap.put(Color.decode("#BA6156").getRGB(), colors[3].getRGB()); //Lower Body Light
        BufferedImage[] originals = new BufferedImage[AMOUNT];
        BufferedImage[] results = new BufferedImage[AMOUNT];
        try {
            // Load the image
            int widths[] = new int[AMOUNT];
            int heights[] = new int[AMOUNT];
            for(int i = 0; i < AMOUNT; i++)
            {
                System.out.println("Reading " + i);
                originals[i] = ImageIO.read(new File("graphs/character/original/" + names[i] + ".png"));
                System.out.println("READ " + i);
            }
            
            for(int i = 0; i < AMOUNT; i++)
            {
                widths[i] = originals[i].getWidth();
                heights[i] = originals[i].getHeight();
                results[i] = new BufferedImage(widths[i], heights[i], BufferedImage.TYPE_INT_ARGB);
            }
            
            for(int i = 0; i < AMOUNT; i++)
            {
                // Loop through all pixels
                for (int y = 0; y < heights[i]; y++) {
                    for (int x = 0; x < widths[i]; x++) {
                        int originalRGB = originals[i].getRGB(x, y);

                        // Replace color if in map
                        int newRGB = colorMap.getOrDefault(originalRGB, originalRGB);

                        // Set the new RGB value
                        results[i].setRGB(x, y, newRGB);
                    }
                }
                ImageIO.write(results[i], "png", new File("graphs/character/current/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(results);
        return results;
    }

    public static BufferedImage flipImagea(Image i) {
        // Convert Image to BufferedImage
        BufferedImage original = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = original.createGraphics();
        g.drawImage(i, 0, 0, null);
        g.dispose();
    
        // Flip horizontally
        AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
        transform.translate(-original.getWidth(), 0);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    
        // Apply transformation and return result
        return op.filter(original, null);
    }
    public static Color[] getOriginalColors()
    {
        return new Color[] {Color.decode("#000000"), Color.decode("#FFB570"), Color.decode("#57294B"), Color.decode("#BA6156")};
    }
    public static void fillOriginalCurrents()
    {
        createImages(new HashMap<>());
        //createImagesByCommand(getOriginalColors());
    }

    //HELPER
    public static Color darkenColor(Color color) {
        int r = (int)(color.getRed() * 0.8);
        int g = (int)(color.getGreen() * 0.8);
        int b = (int)(color.getBlue() * 0.8);

        return new Color(r, g, b);
    }
}