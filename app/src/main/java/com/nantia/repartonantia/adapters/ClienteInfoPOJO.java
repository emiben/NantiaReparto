package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.widget.ImageView;

/**
 * Created by Emi on 28/5/2018.
 */

public class ClienteInfoPOJO {
    Drawable image;
    String primario;
    String secundario;

    public ClienteInfoPOJO(Context context, int imageView, String primario, int secundario) {
        this.image = context.getResources().getDrawable(imageView, null);
        this.primario = primario;
        this.secundario = context.getResources().getString(secundario);
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getPrimario() {
        return primario;
    }

    public void setPrimario(String primario) {
        this.primario = primario;
    }

    public String getSecundario() {
        return secundario;
    }

    public void setSecundario(String secundario) {
        this.secundario = secundario;
    }
}
