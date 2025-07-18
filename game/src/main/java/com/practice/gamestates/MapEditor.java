package com.practice.gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.practice.Game;
import com.practice.handlers.TileHandler;
import com.practice.objects.GameMap;
import com.practice.objects.Tile;
import com.practice.ui.MapEditorBar;
import com.practice.ui.MenuButton;
import com.practice.ui.TileButton;
import com.practice.utilz.ImageLibrary;
import com.practice.utilz.LoadSave;

import static com.practice.Game.TILES_SIZE;
import static com.practice.utilz.Constants.UI.Buttons.B_LOAD_LE_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_OPTIONS_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_SAVE_LE_SPRITE;
import static com.practice.utilz.Constants.UI.Buttons.B_WIDTH;

public class MapEditor extends State implements Statemethods {

    private static final int MAP_EDITOR_BAR_HEIGHT = TileButton.TILE_BUTTON_SIZE * 5;
    private GameMap currentMap;
    private int[][] currentMapEdits;
    private MenuButton[] buttons = new MenuButton[3];
    private List<TileButton> tileButtons = new ArrayList<>();
    private MapEditorBar mapEditorBar;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;


    public MapEditor(Game game, ImageLibrary imageLibrary, TileHandler tileHandler) {
        super(game, imageLibrary);
        mapEditorBar = new MapEditorBar(0, Game.GAME_HEIGHT-MAP_EDITOR_BAR_HEIGHT, Game.GAME_WIDTH, MAP_EDITOR_BAR_HEIGHT);
        currentMap = new GameMap(tileHandler);
        currentMapEdits = currentMap.getMapData();
        loadButtons();
        //TODO: CreateNewMap placeholder, remove when complete
        LoadSave.CreateNewMap(currentMap);
    }

    //TODO: Implement load
    //TODO: Implement save -> needs to get a map name
    //TODO: Add reset button
    private void loadButtons() {
        buttons[0] = new MenuButton( 100 + B_WIDTH, Game.GAME_HEIGHT-50, B_OPTIONS_SPRITE, Gamestate.OPTIONS, imageLibrary);
        buttons[1] = new MenuButton( 3 * 50 + 2*B_WIDTH, Game.GAME_HEIGHT-50, B_LOAD_LE_SPRITE, Gamestate.OPTIONS, imageLibrary);
        buttons[2] = new MenuButton( 4 * 50 + 3*B_WIDTH, Game.GAME_HEIGHT-50, B_SAVE_LE_SPRITE, Gamestate.OPTIONS, imageLibrary);
        ArrayList<Tile> tileList = game.tileHandler.getTileList();
        for (int i=0; i<tileList.size(); i++) {
            tileButtons.add(new TileButton(((i+1)*50 + i*TileButton.TILE_BUTTON_SIZE), Game.GAME_HEIGHT-125, tileList.get(i)));
        }
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int y=0; y<currentMapEdits.length; y++) {
            for (int x=0; x<currentMapEdits[0].length; x++) {
                int id = currentMapEdits[y][x];
                g.drawImage(game.tileHandler.getGroundTileSprite(id), x*TILES_SIZE, y*TILES_SIZE, TILES_SIZE, TILES_SIZE, null);
            }
        }
        
        mapEditorBar.draw(g);

        for (MenuButton mb: buttons)
            mb.draw(g);

        for (TileButton tb: tileButtons)
            tb.draw(g);

        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), Game.GAME_WIDTH-100, Game.GAME_HEIGHT-125, TileButton.TILE_BUTTON_SIZE, TileButton.TILE_BUTTON_SIZE, null);
            g.setColor(Color.black);
            g.drawRect( Game.GAME_WIDTH-100, Game.GAME_HEIGHT-125, TileButton.TILE_BUTTON_SIZE, TileButton.TILE_BUTTON_SIZE);
        }
        if (isMouseInMap(mouseX, mouseY))
            drawSelectedTileCursor(g);
        
    }

    private void drawSelectedTileCursor(Graphics g) {
        if (selectedTile != null) {
            int selectedTileX, selectedTileY;
            selectedTileX = (mouseX/TileButton.TILE_BUTTON_SIZE)*TileButton.TILE_BUTTON_SIZE;
            selectedTileY = (mouseY/TileButton.TILE_BUTTON_SIZE)*TileButton.TILE_BUTTON_SIZE;
            g.drawImage(selectedTile.getSprite(), selectedTileX, selectedTileY, TileButton.TILE_BUTTON_SIZE, TileButton.TILE_BUTTON_SIZE, null);
        }

    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int xCoord = mouseX/TileButton.TILE_BUTTON_SIZE;
            int yCoord = mouseY/TileButton.TILE_BUTTON_SIZE;
            
            if (xCoord >= Game.TILES_IN_WIDTH)
                xCoord = Game.TILES_IN_WIDTH -1;
            if (yCoord >= Game.TILES_IN_HEIGHT)
                yCoord = Game.TILES_IN_HEIGHT -1;

            if (lastTileX == xCoord && lastTileY == yCoord && lastTileId == selectedTile.getTileIndex())
                return;

            lastTileX = xCoord;
            lastTileY = yCoord;
            lastTileId = selectedTile.getTileIndex();
            
            currentMapEdits[yCoord][xCoord] = selectedTile.getTileIndex();
        }
    }

    private void resetChanges() {
        currentMapEdits = currentMap.getMapData();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isMouseInMap(mouseX, mouseY)) {
            if (selectedTile != null) {
                changeTile(mouseX, mouseY);;
            }
        } 
        else {
            for (MenuButton mb : buttons) {
                if(isOverButton(e, mb)) {
                    mb.setMousePressed(true);
                    break;
                }
            }
    
            for (TileButton tb: tileButtons) {
                if(isOverButton(e, tb)) {
                    tb.setMousePressed(true);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isMouseInMap(mouseX, mouseY)){

        } 
        else {
            for (MenuButton mb : buttons) {
                if(isOverButton(e, mb)) {
                    if(mb.isMousePressed()) {
                        mb.applyGamestate();
                    break;
                    }
                }
            }
    
            for (TileButton tb: tileButtons) {
                if(isOverButton(e, tb)) {
                    if(tb.isMousePressed()) {
                        this.selectedTile = tb.getTile();
                    break;
                    }
                }
            }
            resetButtons();
    
            for (TileButton tb : tileButtons) {
                if(isOverButton(e, tb)) {
                    tb.setMouseOver(true);
                    break;
                }
            }
        }
    }

    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (isMouseInMap(mouseX, mouseY)) {

        } else {

            for (MenuButton mb : buttons) {
                mb.setMouseOver(false);
            }
    
            for (TileButton tb: tileButtons) {
                tb.setMouseOver(false);
            }
            
            for (MenuButton mb : buttons) {
                if(isOverButton(e, mb)) {
                    mb.setMouseOver(true);
                    break;
                }
            }
    
            for (TileButton tb : tileButtons) {
                if(isOverButton(e, tb)) {
                    tb.setMouseOver(true);
                    break;
                }
            }
        }

    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (isMouseInMap(mouseX, mouseY) && selectedTile != null) {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            selectedTile = null;
        }
    }
    
    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }

        for (TileButton tb : tileButtons) {
            tb.resetBools();
        }
    }

    private boolean isMouseInMap(int mouseX, int mouseY) {
        return mouseY < Game.GAME_HEIGHT-MAP_EDITOR_BAR_HEIGHT && mouseY >= 0 && mouseX >= 0 && mouseX <= Game.GAME_WIDTH;
    }

}
