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
        Map<Integer, Integer> colorMap3 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap4 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap5 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap6 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap7 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> colorMap8 = new HashMap<Integer, Integer>();
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

        colorMap3.put(Color.decode("#000000").getRGB(), Color.decode("#221811").getRGB()); // Eyes
        colorMap3.put(Color.decode("#FFB570").getRGB(), Color.decode("#D4A474").getRGB()); // Face Big Part
        colorMap3.put(Color.decode("#FF9166").getRGB(), Color.decode("#B0754F").getRGB()); // Face Shadow
        colorMap3.put(Color.decode("#3E2347").getRGB(), Color.decode("#6B4C3B").getRGB()); // Upper Body Dark
        colorMap3.put(Color.decode("#57294B").getRGB(), Color.decode("#9B6E4B").getRGB()); // Upper Body Light
        colorMap3.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#5F4025").getRGB()); // Lower Body Dark
        colorMap3.put(Color.decode("#BA6156").getRGB(), Color.decode("#B6895B").getRGB()); // Lower Body Light

        colorMap4.put(Color.decode("#000000").getRGB(), Color.decode("#1A1A1A").getRGB()); // Eyes
        colorMap4.put(Color.decode("#FFB570").getRGB(), Color.decode("#C69C6D").getRGB()); // Face Big Part
        colorMap4.put(Color.decode("#FF9166").getRGB(), Color.decode("#A25E3E").getRGB()); // Face Shadow
        colorMap4.put(Color.decode("#3E2347").getRGB(), Color.decode("#3E4730").getRGB()); // Upper Body Dark
        colorMap4.put(Color.decode("#57294B").getRGB(), Color.decode("#64724C").getRGB()); // Upper Body Light
        colorMap4.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#843E2A").getRGB()); // Lower Body Dark
        colorMap4.put(Color.decode("#BA6156").getRGB(), Color.decode("#A85C3A").getRGB()); // Lower Body Light

        colorMap5.put(Color.decode("#000000").getRGB(), Color.decode("#1F0F28").getRGB()); // Eyes
        colorMap5.put(Color.decode("#FFB570").getRGB(), Color.decode("#E4B386").getRGB()); // Face Big Part
        colorMap5.put(Color.decode("#FF9166").getRGB(), Color.decode("#C47A56").getRGB()); // Face Shadow
        colorMap5.put(Color.decode("#3E2347").getRGB(), Color.decode("#3C2A52").getRGB()); // Upper Body Dark
        colorMap5.put(Color.decode("#57294B").getRGB(), Color.decode("#7C5C82").getRGB()); // Upper Body Light
        colorMap5.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#B08C5B").getRGB()); // Lower Body Dark
        colorMap5.put(Color.decode("#BA6156").getRGB(), Color.decode("#D6A05C").getRGB()); // Lower Body Light

        colorMap6.put(Color.decode("#000000").getRGB(), Color.decode("#2C2C2C").getRGB()); // Eyes
        colorMap6.put(Color.decode("#FFB570").getRGB(), Color.decode("#C2976D").getRGB()); // Face Big Part
        colorMap6.put(Color.decode("#FF9166").getRGB(), Color.decode("#9F6C4A").getRGB()); // Face Shadow
        colorMap6.put(Color.decode("#3E2347").getRGB(), Color.decode("#4A4A4A").getRGB()); // Upper Body Dark – Chainmail look
        colorMap6.put(Color.decode("#57294B").getRGB(), Color.decode("#6E6E6E").getRGB()); // Upper Body Light – Metal armor shine
        colorMap6.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#3E3B2F").getRGB()); // Lower Body Dark – Worn leather
        colorMap6.put(Color.decode("#BA6156").getRGB(), Color.decode("#6B4F3A").getRGB()); // Lower Body Light – Hardened brown cloth

        colorMap7.put(Color.decode("#000000").getRGB(), Color.decode("#101420").getRGB()); // Eyes – Shadow blue
        colorMap7.put(Color.decode("#FFB570").getRGB(), Color.decode("#E0B68C").getRGB()); // Face Big Part
        colorMap7.put(Color.decode("#FF9166").getRGB(), Color.decode("#C07D57").getRGB()); // Face Shadow
        colorMap7.put(Color.decode("#3E2347").getRGB(), Color.decode("#283A6B").getRGB()); // Upper Body Dark – Dyed navy
        colorMap7.put(Color.decode("#57294B").getRGB(), Color.decode("#506A92").getRGB()); // Upper Body Light – Faded blue-gray
        colorMap7.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#5B453A").getRGB()); // Lower Body Dark – Neutral cloth
        colorMap7.put(Color.decode("#BA6156").getRGB(), Color.decode("#AD9983").getRGB()); // Lower Body Light – Beige-linen blend

        colorMap8.put(Color.decode("#000000").getRGB(), Color.decode("#2A2A2A").getRGB()); // Eyes – Dusty black
        colorMap8.put(Color.decode("#FFB570").getRGB(), Color.decode("#D8A876").getRGB()); // Face Big Part
        colorMap8.put(Color.decode("#FF9166").getRGB(), Color.decode("#B1794C").getRGB()); // Face Shadow
        colorMap8.put(Color.decode("#3E2347").getRGB(), Color.decode("#5E5E4F").getRGB()); // Upper Body Dark – Old cloth gray
        colorMap8.put(Color.decode("#57294B").getRGB(), Color.decode("#78785F").getRGB()); // Upper Body Light – Worn greenish gray
        colorMap8.put(Color.decode("#8C3F5D").getRGB(), Color.decode("#805B42").getRGB()); // Lower Body Dark – Worn brown
        colorMap8.put(Color.decode("#BA6156").getRGB(), Color.decode("#A98C6C").getRGB()); // Lower Body Light – Pale brown



        colorMaps.add(colorMap);
        colorMaps.add(colorMap1);
        colorMaps.add(colorMap2);
        colorMaps.add(colorMap3);
        colorMaps.add(colorMap4);
        colorMaps.add(colorMap5);
        colorMaps.add(colorMap6);
        colorMaps.add(colorMap7);
        colorMaps.add(colorMap8);
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
                File folderE = new File("Coloring/" + i + "e");
                if (!folder1.exists() && !folder.exists()) {
                    folder.mkdirs();
                    folderE.mkdirs();
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
            for(int i = 0; i < AMOUNT; i++)
            {
                BufferedImage result = flipImage(originals[i]);
                ImageIO.write(result, "png", new File("Coloring/" + folderLocation + "e/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage flipImage(Image i) {
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
}