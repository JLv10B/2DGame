package com.practice.utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.imageio.ImageIO;


public class ImageLibrary {
    public Map<String, Map<String, List<BufferedImage>>> charAnimationLibrary = new HashMap<>();
    public Map<String, Map<String, Integer>> charSpriteCountLib = new HashMap<>();
    public Map<String, List<BufferedImage>> tileSpriteLibrary = new HashMap<>();
    public Map<String, List<BufferedImage>> uiSpriteLibrary = new HashMap<>();

    public static final String CHAR_ANIMATIONS = "game\\src\\main\\resources\\Sprites";
    public static final String TILE_IMAGES = "game\\src\\main\\resources\\NonCharSprites\\Tile-Sets";
    public static final String MENU_IMAGES = "game\\src\\main\\resources\\NonCharSprites\\UI";

    public ImageLibrary() throws IOException {
        loadCharAnimationLibrary();
        loadTileImages();
        loadUIImages();
    }

    private void loadCharAnimationLibrary() throws IOException {
        Path rootPath = Paths.get(CHAR_ANIMATIONS);
        
        try (Stream<Path> walk = Files.walk(rootPath)) {
            walk.filter(Files::isRegularFile).filter(Files::isReadable).forEach(filePath -> {
                Path relativePath = rootPath.relativize(filePath);
                if (relativePath.getNameCount() >= 3) {
                    String spriteModel = relativePath.getName(relativePath.getNameCount()-3).toString();
                    String animationState = relativePath.getName(relativePath.getNameCount()-2).toString();
                    try {
                        BufferedImage image = ImageIO.read(filePath.toFile());
                        if (image != null) {
                            Map<String, List<BufferedImage>> charaterAnimations = charAnimationLibrary.computeIfAbsent(spriteModel, k -> new HashMap<>());
                            List<BufferedImage> animationFrames = charaterAnimations.computeIfAbsent(animationState, k -> new ArrayList<>());
                            animationFrames.add(image);
                            
                            Map<String, Integer> charaterAnimationsSpriteCountLib = charSpriteCountLib.computeIfAbsent(spriteModel, k -> new HashMap<>());
                            charaterAnimationsSpriteCountLib.put(animationState, animationFrames.size());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                   System.out.println("skipping file" + filePath); 
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTileImages() {
        Path rootPath = Paths.get(TILE_IMAGES);

        try (Stream<Path> walk = Files.walk(rootPath)) {
            walk.filter(Files::isRegularFile).filter(Files::isReadable).forEach(filePath -> {
                Path relativePath = rootPath.relativize(filePath);
                if (relativePath.getNameCount() >= 2) {
                    String tileType = relativePath.getName(relativePath.getNameCount()-2).toString();
                    try { BufferedImage image = ImageIO.read(filePath.toFile());
                        if (image != null) {
                            List<BufferedImage> tileList = tileSpriteLibrary.computeIfAbsent(tileType, k -> new ArrayList<>());
                            tileList.add(image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUIImages() {
        Path rootPath = Paths.get(MENU_IMAGES);

        try (Stream<Path> walk = Files.walk(rootPath)) {
            walk.filter(Files::isRegularFile).filter(Files::isReadable).forEach(filePath -> {
                Path relativePath = rootPath.relativize(filePath);
                if (relativePath.getNameCount() >= 2) {
                    String tileType = relativePath.getName(relativePath.getNameCount()-2).toString();
                    try { BufferedImage image = ImageIO.read(filePath.toFile());
                        if (image != null) {
                            List<BufferedImage> tileList = uiSpriteLibrary.computeIfAbsent(tileType, k -> new ArrayList<>());
                            tileList.add(image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSpriteAmount(String charModel, String playerAction) {
        return charSpriteCountLib.get(charModel).get(playerAction);
    }

    public Map<String, Map<String, List<BufferedImage>>> getCharLibrary() {
        return charAnimationLibrary;
    }

    public Map<String, List<BufferedImage>> getTileLibrary() {
        return tileSpriteLibrary;
    }

    public Map<String, List<BufferedImage>> getUILibrary() {
        return uiSpriteLibrary;
    }
}
