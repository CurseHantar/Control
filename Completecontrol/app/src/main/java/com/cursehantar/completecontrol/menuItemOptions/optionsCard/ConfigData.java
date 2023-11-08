package com.cursehantar.completecontrol.menuItemOptions.optionsCard;

import com.cursehantar.completecontrol.MainActivity;

import com.cursehantar.completecontrol.R;

public class ConfigData {
    public static String[] configArray = {"Pantalla inicial", "Camara", "Notificacion"};
    public static String[] descArray = {"Colores, tamaños, cambiar interfaz, etc","Uso de camara, avazado, brillo, etc","Sonidos, volumen, duracion, etc"};
    static Class<?>[] classArray = {MainActivity.class, MainActivity.class, MainActivity.class};

    public static Integer[] imagenArray = {R.drawable.ic_home_black_24dp, R.drawable.ic_menu_camera, R.drawable.ic_notifications_black_24dp};

}
