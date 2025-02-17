package com.ifcolab.safesoft;

import com.ifcolab.safesoft.controller.AdminController;
import com.ifcolab.safesoft.view.FrLogin;

public class SafeSoft {

    public static void main(String[] args) {
       FrLogin tela = new FrLogin();
       tela.setVisible(true);

        AdminController adminController = new AdminController();
        adminController.criarAdminPadrao();
    }
}
