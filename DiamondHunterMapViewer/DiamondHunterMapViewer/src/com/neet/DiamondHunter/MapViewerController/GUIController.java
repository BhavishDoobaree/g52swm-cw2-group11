package com.neet.DiamondHunter.MapViewerController;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

//main controller
//map view location increased by 16 -> 640/40 = 16

public class GUIController implements Initializable{

	
	//variables declaration here
	private Image[] item;
	private Image[][] tile;
	private int axeXreal=26, axeYreal=37,boatXreal=12, boatYreal=4;
	public static int boatCY = 416,boatCX = 592, axeCY = 192, axeCX = 64; //Change me to adapt to real program
	int select = 0;
	private int rowNum, colsNum, tilesAcr;
	private int sizeT=16;
	boolean set_boat=true, set_axe=true;
	private int[][] map;
	
	
	Alert savemap = new Alert(Alert.AlertType.INFORMATION);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		//getting initial graphics
		GraphicsContext graphics1 = canvasfxml.getGraphicsContext2D();
		ResTiles("Resources/Tilesets/testtileset.gif");  //loading of giving resources
		ResMap("Resources/Sprites/items.gif");
		ResItem("Resources/Maps/testmap.map");
		
		//draw map and place items
		generategraphics(graphics1);
		graphics1.drawImage(item[0],boatCY, boatCX); //draws initial position of objects. PREDEFINE ME
		
		graphics1.drawImage(item[1], axeCY, axeCX);
		
		
		//text box initial value -> gives indication to user on which type of data needed
		axextxt.setText(Integer.toString(axeCX/16));  //compensate for 16x during display 
		axeytxt.setText(Integer.toString(axeCY/16));
		boatxtxt.setText(Integer.toString(boatCX/16));
		boatytxt.setText(Integer.toString(boatCY/16));
		
		//detect clicks on Boat and Axe button for set
		
		setBoatbutton.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
			{@Override
			
				public void handle(javafx.scene.input.MouseEvent event)
				{
					select = 0;
				}
			});
		
		setAxebutton.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
		{@Override
		
			public void handle(javafx.scene.input.MouseEvent event)
			{
				select = 0;
			}
		});
		
		canvasfxml.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
		
		{

			@Override
			public void handle(javafx.scene.input.MouseEvent event) {
				// TODO Auto-generated method stub
				GraphicsContext mapmod = canvasfxml.getGraphicsContext2D();
				
				//moves boat or keep at original position
				
				if(select == 0) //boat selected
				{
					generategraphics(mapmod);
					if (set_boat){
						mapmod.drawImage(item[0],boatCY,boatCX);
						
					}
					if(!set_boat)
					{
						mapmod.drawImage(item[0],boatXreal*16,boatYreal*16);
						
					}
					
					//determine position of axe and updating the text boxes
					
					axeXreal=(int)event.getX()/16;
					axeYreal=(int)event.getY()/16;
					axextxt.setText(Integer.toString(axeXreal));
					axeytxt.setText(Integer.toString(axeYreal));
					set_axe= false;
					
					mapmod.drawImage(item[1], axeXreal*16, axeYreal*16);
					
				}
				else //axe selected
				{
					generategraphics(mapmod);
					if(set_axe){
						mapmod.drawImage(item[1],axeCX , axeCY);
					}
					if(!set_axe)
					{
						mapmod.drawImage(item[1], axeXreal *16, axeXreal*16);
					}
					
					boatXreal=(int)event.getX()/16;
					boatYreal=(int)event.getY()/16;
					boatxtxt.setText(Integer.toString(boatXreal));
					boatytxt.setText(Integer.toString(boatYreal));
					
					set_boat= false;
					mapmod.drawImage(item[0], boatXreal*16, boatYreal*16);
					
					
				}
				
			}
			
			
			
		});
		
		saveButton.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
				{

					@Override
					public void handle(javafx.scene.input.MouseEvent event) {
						// TODO Auto-generated method stub
						try {
							
							if(set_axe==true && set_boat==true){ //if true then y,x else x,y
								checkposition(axeYreal,axeXreal,boatYreal,boatXreal);
							}
							if(set_axe==false && set_boat==false){
								checkposition(axeXreal,axeYreal,boatXreal,boatYreal);
							}
							if(set_axe==true && set_boat==false){
								checkposition(axeYreal,axeXreal,boatXreal,boatYreal);
							}
							if(set_axe==false && set_boat==true){
								checkposition(axeXreal,axeYreal,boatYreal,boatXreal);
							}
							
							if(set_axe==false){
								axeCX=axeXreal*16;
								axeCY=axeYreal*16;
							}
							
							if(set_boat==false){
								boatCX=boatYreal*16;
								boatCY=boatXreal*16;
							}
							
							
							
							savemap.setTitle("Map Changes Made");
							savemap.setContentText("The following changes have been made.\nAxe = ("+axeCX/16+","+axeCY/16+")"+"\nBoat = ("+boatCX+","+boatCY+")"+"\n Click on exit and the run game");
							savemap.showAndWait();
							
						}catch(ExceptionHandler excptxt){
							GenerateAlert.show("Alert", excptxt.excptxt);
						}
						
						
					}

					private void checkposition(int axeYreal, int axeXreal, int boatYreal, int boatXreal) throws ExceptionHandler {
						// TODO Auto-generated method stub
						if((axeXreal*16)==axeCX && (axeYreal*16)==axeCY && (boatXreal*16)==boatCX &&(boatYreal*16)==boatCY){
							throw new ExceptionHandler("No changes detected.");
						}
						if(map[axeYreal][axeXreal] == 20 || map[axeYreal][axeXreal]==21){
		                    throw new ExceptionHandler("Position taken by tree. Reposition the axe.");
		                }
		                if(map[axeYreal][axeXreal]==22){
		                    throw new ExceptionHandler("Position taken by water. Reposition the axe.");
		                }
		                if(map[boatYreal][boatXreal] == 20 || map[boatYreal][boatXreal]==21){
		                    throw new ExceptionHandler("Postion taken by tree. Reposition the boat.");
		                }
		                if(map[boatYreal][boatXreal]==22){
		                    throw new ExceptionHandler("Position taken by water. Reposition the boat.");
		                }
						
					}
					
					
				}
				
				);
	}
	@FXML
	public void exit() throws Exception{
		Scene scene = exitButton.getScene();
		Stage current = (Stage)scene.getWindow();
		current.hide();
	}

	//get images from resources
	
	private void ResItem(String string) {
		// TODO Auto-generated method stub
		Image tileSet = new Image(string);
		item = new Image[2];
		for (int x=0;x<2;x++){
			item[x] = new WritableImage(tileSet.getPixelReader(), x * sizeT, 16, sizeT, sizeT);
		}
		
	}

	private void ResMap(String string) {
		// TODO Auto-generated method stub
		  try {

	            InputStream in = getClass().getResourceAsStream(string);
	            BufferedReader br = new BufferedReader(
	                    new InputStreamReader(in)
	            );

	            colsNum = Integer.parseInt(br.readLine());
	            rowNum = Integer.parseInt(br.readLine());
	            map = new int[rowNum][colsNum];

	            String delims = "\\s+"; //regex expression
	            for(int row = 0; row < rowNum; row++) {
	                String line = br.readLine();
	                String[] tokens = line.split(delims);
	                for(int col = 0; col < colsNum; col++) {
	                    map[row][col] = Integer.parseInt(tokens[col]);
	                }
	            }

	        }
	        catch(Exception expt) { 
	            expt.printStackTrace();
	        }
	}

	private void ResTiles(String string) {
		// TODO Auto-generated method stub
		try {

            Image setTile=new Image(string);
            tilesAcr = (int) setTile.getWidth() / sizeT;
            tile = new Image[2][tilesAcr];

            for(int y = 0; y < tilesAcr; y++) {
                tile[0][y] = new WritableImage(
                        setTile.getPixelReader(),
                        y * sizeT,
                        0,
                        sizeT,
                        sizeT);
                tile[1][y] = new WritableImage(
                        setTile.getPixelReader(),
                        y * sizeT,
                        sizeT,
                        sizeT,
                        sizeT);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}

	private void generategraphics(GraphicsContext graphics1) {
		// TODO Auto-generated method stub
		for(int tilerow = 0; tilerow < 40; tilerow++){
			if (tilerow >= rowNum) break;
			for (int z=0;z<40;z++){
				if (z>=colsNum) break;
				if(map[tilerow][z]==0) continue;
				
				int rowcol = map[tilerow][z];
				int row = rowcol / tilesAcr;
				int col = rowcol % tilesAcr;
				
				graphics1.drawImage(tile[row][col], col * sizeT, row * sizeT);
			}
		}
	}

	//FXML declarations here
	@FXML 
	public Canvas canvasfxml; //add menu option later
	
	@FXML
	public Button setAxebutton;
	
	@FXML 
	public Button setBoatbutton;
	
	@FXML 
	public Button saveButton;
	
	@FXML
	public Button exitButton;
	
	@FXML
	public TextField axextxt;
	
	@FXML
	public TextField axeytxt;
	
	@FXML
	public TextField boatxtxt;
	
	@FXML
	public TextField boatytxt;
		
	
}

