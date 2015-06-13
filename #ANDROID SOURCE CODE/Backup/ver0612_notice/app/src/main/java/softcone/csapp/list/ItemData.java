package softcone.csapp.list;

import android.media.Image;

/**
 * Created by 쟈 on 2015-05-08.
 */
public class ItemData {
    private Image item_img;
    private String item_name;
    private String item_day;
    private String item_time;

    public ItemData(Image item_img, String item_name, String item_day, String item_time){
        this.item_img = item_img;
        this.item_name = item_name;
        this.item_day = item_day;
        this.item_time = item_time;
    }

    public Image getItem_img(){
        return item_img;
    }

    public String getItem_name(){
        return item_name;
    }

    public String getItem_day(){
        return item_day;
    }

    public String getItem_time(){
        return item_time;
    }
}
