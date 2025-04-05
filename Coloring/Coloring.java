package Coloring;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Coloring {
    public static final int AMOUNT = 9;

    public static void main(String[] args) {
        try {
            // Load the image
            BufferedImage[] originals = new BufferedImage[AMOUNT];
            BufferedImage[] results = new BufferedImage[AMOUNT];
            String[] names = new String[]{"1", "2", "3", "a1", "a2", "w1", "w2", "w3", "w4"};
            int widths[] = new int[AMOUNT];
            int heights[] = new int[AMOUNT];
            for(int i = 0; i < AMOUNT; i++)
            {
                System.out.println("Reading " + i);
                originals[i] = ImageIO.read(new File("graphs/character/0/" + names[i] + ".png"));
                System.out.println("READ " + i);
            }
            
            for(int i = 0; i < AMOUNT; i++)
            {
                widths[i] = originals[i].getWidth();
                heights[i] = originals[i].getHeight();
                results[i] = new BufferedImage(widths[i], heights[i], BufferedImage.TYPE_INT_ARGB);
            }

            // Define your color mappings here (from original RGB to new RGB)
            Map<Integer, Integer> colorMap = new HashMap<>();

            // First color is the original, second color is the replacement
            //RGB
            //colorMap.put(Color.decode("#000000").getRGB(), new Color(255, 253, 211).getRGB());
            //HEX
            colorMap.put(Color.decode("#000000").getRGB(), Color.decode("#000000").getRGB()); //Eyes
            colorMap.put(Color.decode("#FFB570").getRGB(), Color.decode("#FFB570").getRGB()); //Face Big Part
            colorMap.put(Color.decode("#FF9166").getRGB(), Color.decode("#FF9166").getRGB()); //Face Shadow
            colorMap.put(Color.decode("#3E2347").getRGB(), Color.decode("#3E2347").getRGB()); //Upper Body Dark
            colorMap.put(Color.decode("#57294B").getRGB(), Color.decode("#57294B").getRGB()); //Upper Body Light
            colorMap.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#8C3F5D").getRGB()); //Lower Body Dark
            colorMap.put(Color.decode("#BA6156").getRGB(), Color.decode("#BA6156").getRGB()); //Lower Body Light
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
                ImageIO.write(results[i], "png", new File("Coloring/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}