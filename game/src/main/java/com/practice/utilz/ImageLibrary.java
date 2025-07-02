package com.practice.utilz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public static Map <String, Map<String, List<BufferedImage>>> charAnimationLibrary = new HashMap<>();
    //TODO: load NonCharSprites

    public ImageLibrary() throws IOException {
        loadCharAnimationLibrary();
    }

    private void loadCharAnimationLibrary() throws IOException {
        Path rootPath = Paths.get("game\\src\\main\\resources\\Sprites");
        
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
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                   System.out.println("skipping file" + filePath); 
                }
            });
        }
    }

    private void loadLevelAnimations() {
        
    }

    public Map <String, Map<String, List<BufferedImage>>> getCharLibrary() {
        return charAnimationLibrary;
    }
}
