package Coloring;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Coloring {
    public static final int AMOUNT = 9;
    public static ArrayList<Map<Integer, Integer>> colorMaps = new ArrayList<>();
    public static void main(String[] args) {
        fillMaps();
        for(Map<Integer, Integer> m : colorMaps)
        {
            createImages(m);
        }
    }
    public static void fillMaps()
    {
        Map<Integer, Integer> colorMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap2 = new HashMap<Integer, Integer>();
        //ORIGINAL TEMPLATE
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
        //END OF ORIGINAL TEMPLATE

        colorMap1.put(Color.decode("#000000").getRGB(), Color.decode("#2D1B0A").getRGB()); //Eyes – Dark brown for a more organic look
        colorMap1.put(Color.decode("#FFB570").getRGB(), Color.decode("#D99B6C").getRGB()); //Face Big Part – A slightly dustier, older tone
        colorMap1.put(Color.decode("#FF9166").getRGB(), Color.decode("#B86B4B").getRGB()); //Face Shadow – More muted orange-brown
        colorMap1.put(Color.decode("#3E2347").getRGB(), Color.decode("#312B2A").getRGB()); //Upper Body Dark – Like faded leather or wool
        colorMap1.put(Color.decode("#57294B").getRGB(), Color.decode("#4A3A37").getRGB()); //Upper Body Light – Earthy brown-purple blend
        colorMap1.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#6D3E31").getRGB()); //Lower Body Dark – Leather-like reddish brown
        colorMap1.put(Color.decode("#BA6156").getRGB(), Color.decode("#A4492B").getRGB()); //Lower Body Light – Clay or brick-colored tone

        colorMap2.put(Color.decode("#000000").getRGB(), Color.decode("#1C1C1C").getRGB()); //Eyes – Softer dark gray, less harsh than pure black
        colorMap2.put(Color.decode("#FFB570").getRGB(), Color.decode("#CFA77B").getRGB()); //Face Big Part – Tanned sun-exposed skin
        colorMap2.put(Color.decode("#FF9166").getRGB(), Color.decode("#A86D49").getRGB()); //Face Shadow – Deep bronze
        colorMap2.put(Color.decode("#3E2347").getRGB(), Color.decode("#1C2F3A").getRGB()); //Upper Body Dark – Ottoman navy/indigo tone
        colorMap2.put(Color.decode("#57294B").getRGB(), Color.decode("#34584B").getRGB()); //Upper Body Light – Faded green, like aged wool
        colorMap2.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#5B3B2A").getRGB()); //Lower Body Dark – Dyed leather brown
        colorMap2.put(Color.decode("#BA6156").getRGB(), Color.decode("#C9A66B").getRGB()); //Lower Body Light – Faded linen or sand color

        



        colorMaps.add(colorMap);
        colorMaps.add(colorMap1);
        colorMaps.add(colorMap2);
    }
    public static void createImages(Map<Integer, Integer> colorMap)
    {
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

            
            int folderLocation = -1;
            for(int i = 0; i >= 0; i++)
            {
                File folder1 = new File("graphs/character/" + i);
                File folder = new File("Coloring/" + i);
                if (!folder1.exists() && !folder.exists()) {
                    folder.mkdirs();
                    folderLocation = i;
                    break;
                }
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
                ImageIO.write(results[i], "png", new File("Coloring/" + folderLocation + "/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}