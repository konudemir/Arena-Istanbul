package Coloring;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Coloring {
    public static void main(String[] args) {
        String[] itemTypes = {"armor", "helmet", "leggings", "shields", "swords"};
        String baseInput = "graph/character/items/";
        String baseOutput = "graph/character/itemsForOpponents/";

        List<String> validNames = Arrays.asList(
                "1.png", "2.png", "3.png", "a1.png", "a2.png",
                "custScreen.png", "lobby.png", "w1.png", "w2.png", "w3.png", "w4.png"
        );

        for (String type : itemTypes) {
            for (int i = 0; i <= 3; i++) {
                File inputFolder = new File(baseInput + type + "/" + i);
                File outputFolder = new File(baseOutput + type + "/" + i);

                if (!inputFolder.exists()) continue;
                outputFolder.mkdirs();

                File[] imageFiles = inputFolder.listFiles((dir, name) -> validNames.contains(name));
                if (imageFiles == null) continue;

                for (File imgFile : imageFiles) {
                    try {
                        BufferedImage original = ImageIO.read(imgFile);
                        BufferedImage flipped = flipImage(original);
                        File outputFile = new File(outputFolder, imgFile.getName());
                        ImageIO.write(flipped, "png", outputFile);
                    } catch (Exception e) {
                        System.out.println("Failed to process " + imgFile.getName() + ": " + e.getMessage());
                    }
                }
            }
        }

        System.out.println("Flipping completed.");
    }
    /*
    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer>[] colorMaps = (HashMap<Integer, Integer>[]) new HashMap[5];
        Color[] originalColors = getAllOriginalColors();
        for(int i = 0; i < 5; i++)
        {
            
        }
        colorMaps[0].put(originalColors[0].getRGB(), rgb("DB9960"));
        colorMaps[0].put(originalColors[1].getRGB(), rgb("DB7A57"));

        colorMaps[1].put(originalColors[0].getRGB(), rgb("FFB570"));//FACE1
        colorMaps[1].put(originalColors[1].getRGB(), rgb("FF9166"));//FACE2
        colorMaps[1].put(originalColors[2].getRGB(), rgb("000000"));//EYES
        colorMaps[1].put(originalColors[3].getRGB(), rgb("000770"));//BODY1
        colorMaps[1].put(originalColors[4].getRGB(), rgb("000A96"));//BODY2
        colorMaps[1].put(originalColors[5].getRGB(), rgb("005109"));//PANTS1
        colorMaps[1].put(originalColors[6].getRGB(), rgb("00770B"));//PANTS2

        colorMaps[2].put(originalColors[0].getRGB(), rgb("A07046"));//FACE1
        colorMaps[2].put(originalColors[1].getRGB(), rgb("96543C"));//FACE2
        colorMaps[2].put(originalColors[2].getRGB(), rgb("000000"));//EYES
        colorMaps[2].put(originalColors[3].getRGB(), rgb("440011"));//BODY1
        colorMaps[2].put(originalColors[4].getRGB(), rgb("72001C"));//BODY2
        colorMaps[2].put(originalColors[5].getRGB(), rgb("4C3C49"));//PANTS1
        colorMaps[2].put(originalColors[6].getRGB(), rgb("70586B"));//PANTS2

        colorMaps[3].put(originalColors[0].getRGB(), rgb("DB9960"));//FACE1
        colorMaps[3].put(originalColors[1].getRGB(), rgb("DB7A57"));//FACE2
        colorMaps[3].put(originalColors[2].getRGB(), rgb("000000"));//EYES
        colorMaps[3].put(originalColors[3].getRGB(), rgb("57294B"));//BODY1
        colorMaps[3].put(originalColors[4].getRGB(), rgb("874075"));//BODY2
        colorMaps[3].put(originalColors[5].getRGB(), rgb("8C3F5D"));//PANTS1
        colorMaps[3].put(originalColors[6].getRGB(), rgb("BA6156"));//PANTS2

        colorMaps[4].put(originalColors[0].getRGB(), rgb("A07046"));//FACE1
        colorMaps[4].put(originalColors[1].getRGB(), rgb("96543C"));//FACE2
        colorMaps[4].put(originalColors[2].getRGB(), rgb("000000"));//EYES
        colorMaps[4].put(originalColors[3].getRGB(), rgb("000770"));//BODY1
        colorMaps[4].put(originalColors[4].getRGB(), rgb("000A96"));//BODY2
        colorMaps[4].put(originalColors[5].getRGB(), rgb("005109"));//PANTS1
        colorMaps[4].put(originalColors[6].getRGB(), rgb("00770B"));//PANTS2
        for(int i = 0; i < 5; i++)
        {
            createImages(colorMaps[i], i);
        }
    }
    */
    public static int rgb(String hex)
    {
        return Color.decode("#" + hex).getRGB();
    }
    public static final int AMOUNT = 11;
    public static final String[] names = new String[]{"1", "2", "3", "a1", "a2", "w1", "w2", "w3", "w4", "lobby", "custScreen"};
    public static HashMap<Integer, Integer>[] fillColorMapsForOpponents() {
        HashMap<Integer, Integer> colorMap1 = new HashMap<>();
colorMap1.put(new Color(0x000000).getRGB(), new Color(0x000000).getRGB());
colorMap1.put(new Color(0x3e2347).getRGB(), new Color(0x3e2347).getRGB());
colorMap1.put(new Color(0x57294b).getRGB(), new Color(0x57294b).getRGB());
colorMap1.put(new Color(0x8c3f5d).getRGB(), new Color(0x8c3f5d).getRGB());
colorMap1.put(new Color(0xba6156).getRGB(), new Color(0x9e6040).getRGB());  // realistic skin
colorMap1.put(new Color(0xff9166).getRGB(), new Color(0xc97d4a).getRGB());  // highlight
colorMap1.put(new Color(0xffb570).getRGB(), new Color(0xe6a16e).getRGB());  // lightest


HashMap<Integer, Integer> colorMap2 = new HashMap<>();
colorMap2.put(new Color(0x000000).getRGB(), new Color(0x000000).getRGB());
colorMap2.put(new Color(0x3e2347).getRGB(), new Color(0x3e2347).getRGB());
colorMap2.put(new Color(0x57294b).getRGB(), new Color(0x57294b).getRGB());
colorMap2.put(new Color(0x8c3f5d).getRGB(), new Color(0x8c3f5d).getRGB());
colorMap2.put(new Color(0xba6156).getRGB(), new Color(0x7b4634).getRGB());  // darker skin
colorMap2.put(new Color(0xff9166).getRGB(), new Color(0x9f5c3e).getRGB());
colorMap2.put(new Color(0xffb570).getRGB(), new Color(0xc57855).getRGB());


HashMap<Integer, Integer> colorMap3 = new HashMap<>();
colorMap3.put(new Color(0x3e2347).getRGB(), new Color(0x2b1b1b).getRGB());
colorMap3.put(new Color(0x57294b).getRGB(), new Color(0x471d1d).getRGB());
colorMap3.put(new Color(0x8c3f5d).getRGB(), new Color(0x8b1e3f).getRGB());
colorMap3.put(new Color(0xba6156).getRGB(), new Color(0x9a4030).getRGB());
colorMap3.put(new Color(0xff9166).getRGB(), new Color(0xbc5a4a).getRGB());
colorMap3.put(new Color(0xffb570).getRGB(), new Color(0xcf6f5e).getRGB());

HashMap<Integer, Integer> colorMap4 = new HashMap<>();
colorMap4.put(new Color(0x3e2347).getRGB(), new Color(0x3d2c1e).getRGB());
colorMap4.put(new Color(0x57294b).getRGB(), new Color(0x5f3a1c).getRGB());
colorMap4.put(new Color(0x8c3f5d).getRGB(), new Color(0x916748).getRGB());
colorMap4.put(new Color(0xba6156).getRGB(), new Color(0xa36840).getRGB());
colorMap4.put(new Color(0xff9166).getRGB(), new Color(0xd6884e).getRGB());
colorMap4.put(new Color(0xffb570).getRGB(), new Color(0xec9b5a).getRGB());

HashMap<Integer, Integer> colorMap5 = new HashMap<>();
colorMap5.put(new Color(0x3e2347).getRGB(), new Color(0x1a2639).getRGB());
colorMap5.put(new Color(0x57294b).getRGB(), new Color(0x2e3e59).getRGB());
colorMap5.put(new Color(0x8c3f5d).getRGB(), new Color(0x536b99).getRGB());
colorMap5.put(new Color(0xba6156).getRGB(), new Color(0x9a7661).getRGB());
colorMap5.put(new Color(0xff9166).getRGB(), new Color(0xba8a6a).getRGB());
colorMap5.put(new Color(0xffb570).getRGB(), new Color(0xc89b74).getRGB());

HashMap<Integer, Integer> colorMap6 = new HashMap<>();
colorMap6.put(new Color(0x3e2347).getRGB(), new Color(0x3c2f2f).getRGB());
colorMap6.put(new Color(0x57294b).getRGB(), new Color(0x5c3a3a).getRGB());
colorMap6.put(new Color(0x8c3f5d).getRGB(), new Color(0x8e5353).getRGB());
colorMap6.put(new Color(0xba6156).getRGB(), new Color(0xad5e5e).getRGB());
colorMap6.put(new Color(0xff9166).getRGB(), new Color(0xd07878).getRGB());
colorMap6.put(new Color(0xffb570).getRGB(), new Color(0xe4927c).getRGB());

HashMap<Integer, Integer> colorMap7 = new HashMap<>();
colorMap7.put(new Color(0x3e2347).getRGB(), new Color(0x2f3131).getRGB());
colorMap7.put(new Color(0x57294b).getRGB(), new Color(0x3f4b4b).getRGB());
colorMap7.put(new Color(0x8c3f5d).getRGB(), new Color(0x567c7c).getRGB());
colorMap7.put(new Color(0xba6156).getRGB(), new Color(0x84796f).getRGB());
colorMap7.put(new Color(0xff9166).getRGB(), new Color(0xa38e82).getRGB());
colorMap7.put(new Color(0xffb570).getRGB(), new Color(0xb89d8d).getRGB());

HashMap<Integer, Integer> colorMap8 = new HashMap<>();
colorMap8.put(new Color(0x3e2347).getRGB(), new Color(0x443122).getRGB());
colorMap8.put(new Color(0x57294b).getRGB(), new Color(0x6a422c).getRGB());
colorMap8.put(new Color(0x8c3f5d).getRGB(), new Color(0x9c5b3b).getRGB());
colorMap8.put(new Color(0xba6156).getRGB(), new Color(0xad6c45).getRGB());
colorMap8.put(new Color(0xff9166).getRGB(), new Color(0xc8835c).getRGB());
colorMap8.put(new Color(0xffb570).getRGB(), new Color(0xe19e6d).getRGB());

HashMap<Integer, Integer> colorMap9 = new HashMap<>();
colorMap9.put(new Color(0x3e2347).getRGB(), new Color(0x1e2e1e).getRGB());
colorMap9.put(new Color(0x57294b).getRGB(), new Color(0x2b4d2b).getRGB());
colorMap9.put(new Color(0x8c3f5d).getRGB(), new Color(0x4a764a).getRGB());
colorMap9.put(new Color(0xba6156).getRGB(), new Color(0x5b7d5b).getRGB());
colorMap9.put(new Color(0xff9166).getRGB(), new Color(0x739873).getRGB());
colorMap9.put(new Color(0xffb570).getRGB(), new Color(0x94b894).getRGB());

HashMap<Integer, Integer> colorMap10 = new HashMap<>();
colorMap10.put(new Color(0x3e2347).getRGB(), new Color(0x1f1f3f).getRGB());
colorMap10.put(new Color(0x57294b).getRGB(), new Color(0x2e2e70).getRGB());
colorMap10.put(new Color(0x8c3f5d).getRGB(), new Color(0x5050a0).getRGB());
colorMap10.put(new Color(0xba6156).getRGB(), new Color(0x6b617f).getRGB());
colorMap10.put(new Color(0xff9166).getRGB(), new Color(0x857c9a).getRGB());
colorMap10.put(new Color(0xffb570).getRGB(), new Color(0xa895b3).getRGB());


        // Store in array
        HashMap<Integer, Integer>[] colorMaps = new HashMap[] {
            colorMap1, colorMap2, colorMap3, colorMap4, colorMap5,
            colorMap6, colorMap7, colorMap8, colorMap9, colorMap10
        };

        return colorMaps;
    }
    public static void createImagesForOpponent(Map<Integer, Integer> colorMap, int order)
    {
        order++;
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
                results[i] = flipImage(results[i]);;
                File directory = new File("Coloring/opponents/" + order);
                if (!directory.exists())directory.mkdirs();
                ImageIO.write(results[i], "png", new File("Coloring/opponents/" + order + "/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createImages(Map<Integer, Integer> colorMap, int no)
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
                originals[i] = ImageIO.read(new File("graphs/character/current/" + names[i] + ".png"));
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
                File directory = new File("Coloring/" + no);
                if (!directory.exists())directory.mkdirs();
                ImageIO.write(results[i], "png", new File("Coloring/" + no + "/" + names[i] + ".png"));
                System.out.println("Image processed and saved as" + names[i] + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createImagesForHelmet(Map<Integer, Integer> colorMap, int no)
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
                originals[i] = ImageIO.read(new File("graphs/character/items/helmet/0/" + names[i] + ".png"));
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
                File directory = new File("Coloring/items/helmet/" + no);
                if (!directory.exists())directory.mkdirs();
                File directory2 = new File("Coloring/itemsForOpponents/helmet/" + no);
                if (!directory2.exists())directory2.mkdirs();
                ImageIO.write(results[i], "png", new File("Coloring/items/helmet/" + no + "/" + names[i] + ".png"));
                ImageIO.write(flipImage(results[i]), "png", new File("Coloring/itemsForOpponents/helmet/" + no + "/" + names[i] + ".png"));
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
    public static Color[] getOriginalColors()
    {
        return new Color[] {Color.decode("#000000"), Color.decode("#FFB570"), Color.decode("#57294B"), Color.decode("#BA6156")};
    }
    public static Color[] getAllOriginalColors()
    {
        return new Color[] {Color.decode("#FFB570"), Color.decode("#FF9166"), Color.decode("#000000"),  Color.decode("#3E2347"), Color.decode("#57294B"),  Color.decode("#8C3F5D"), Color.decode("#BA6156")};
    }

    //HELPER
    public static Color darkenColor(Color color) {
        int r = (int)(color.getRed() * 0.8);
        int g = (int)(color.getGreen() * 0.8);
        int b = (int)(color.getBlue() * 0.8);

        return new Color(r, g, b);
    }
}