package com.example.airhockey;

import javafx.scene.Scene;

public class Setting
{
    public Scene setting ( )
    {
        PanDiscView panDiscView=new PanDiscView(400 ,600, 150,
                7, 15, 30);
        panDiscView.setPan1Position(100, 500);
        return( panDiscView.getScene());
    }
}
