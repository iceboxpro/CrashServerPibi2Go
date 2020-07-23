package com.elnaranjo.pibi2goserver.Common;

import com.elnaranjo.pibi2goserver.Model.Request;
import com.elnaranjo.pibi2goserver.Model.User;

public class Common {
    public static User currentUser;
    public static Request currentRequest;

    public static final String UPDATE = "Actualizar";
    public static final String DELETE = "Borrar";
    public static final int PICK_IMAGE_REQUEST = 71;
    public static String convertCodeToStatus(String code) {
        if (code.equals("0"))
            return "Pedido en Proceso";
        else
            if (code.equals("1"))
                return "Con repartidor";
            else
                return "Entregado";
    };
}
