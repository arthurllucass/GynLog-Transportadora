package com.gynlog;

import com.gynlog.view.TelaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        configurarLookAndFeel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    private static void configurarLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }
}