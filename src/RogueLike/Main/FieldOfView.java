package RogueLike.Main;

import RogueLike.Main.Utils.PointShapes.Line;
import RogueLike.Main.Utils.PointShapes.Point;

public class FieldOfView {
    private int depth;

    private boolean[][] visible;
    public boolean isVisible(int x, int y, int z){
        return z == depth && x >= 0 && y >= 0 && x < visible.length && y < visible[0].length && visible[x][y];
    }

    private Tile[][][] tiles;
    public Tile tile(int x, int y, int z){
    	if(x < 0) {
    		x = 0;
    	}
    	if(y < 0) {
    		y = 0;
    	}
    	if(x > World.width()-1) {
    		x = World.width()-1;
    	}
    	if(y > World.height()-1) {
    		y = World.height()-1;
    	}
        return tiles[x][y][z];
    }

    public FieldOfView(){
        this.visible = new boolean[World.width()][World.height()];
        this.tiles = new Tile[World.width()][World.height()][World.depth()];
    
        for (int x = 0; x < World.width(); x++){
            for (int y = 0; y < World.height(); y++){
                for (int z = 0; z < World.depth(); z++){
                    tiles[x][y][z] = Tile.UNKNOWN;
                }
            }
        }
    }
	
    public void update(int wx, int wy, int wz, int r){
		depth = wz;
		visible = new boolean[World.width()][World.height()];
		
		for (int x = -r; x < r; x++){
			for (int y = -r; y < r; y++){
				if (x*x + y*y > r*r)
					continue;
				
				if (wx + x < 0 || wx + x >= World.width() || wy + y < 0 || wy + y >= World.height())
					continue;
				
				for (Point p : new Line(wx, wy, wx + x, wy + y)){
					Tile tile = World.tile(p.x, p.y, wz);
					visible[p.x][p.y] = true;
					tiles[p.x][p.y][wz] = tile; 
					
					if (!tile.isOpen() || tile.isObscuring())
						break;
				}
			}
		}
	}
    
    public void updateLevitating(int wx, int wy, int wz, int r){
		depth = wz;
		visible = new boolean[World.width()][World.height()];
		
		for (int x = -r; x < r; x++){
			for (int y = -r; y < r; y++){
				if (x*x + y*y > r*r)
					continue;
				
				if (wx + x < 0 || wx + x >= World.width() || wy + y < 0 || wy + y >= World.height())
					continue;
				
				for (Point p : new Line(wx, wy, wx + x, wy + y)){
					Tile tile = World.tile(p.x, p.y, wz);
					visible[p.x][p.y] = true;
					tiles[p.x][p.y][wz] = tile; 
					
					if (!tile.isOpen())
						break;
				}
			}
		}
	}
    
    public void updateMagicMapping(int wx, int wy, int wz, int r){
		depth = wz;
		visible = new boolean[World.width()][World.height()];
		
		for (int x = -r; x < r; x++){
			for (int y = -r; y < r; y++){
				if (x*x + y*y > r*r)
					continue;
				
				if (wx + x < 0 || wx + x >= World.width() || wy + y < 0 || wy + y >= World.height())
					continue;
				
				for (Point p : new Line(wx, wy, wx + x, wy + y)){
					Tile tile = World.tile(p.x, p.y, wz);
					visible[p.x][p.y] = true;
					tiles[p.x][p.y][wz] = tile; 
					
					//if (!tile.isGround())
						//break;
				}
			}
		}
	}
	
	
	
	
	
	

}
