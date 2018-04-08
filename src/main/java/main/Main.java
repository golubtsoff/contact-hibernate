package main;

import gui.ContactFrame;
import gui.GuiResource;

public class Main {
    public static void main(String[] args) {
        try {
            switch (args.length) {
                case 0:
                    GuiResource.initComponentResources();
                    break;
                case 1:
                    GuiResource.initComponentResources(args[0]);
                    break;
                default:
                    return;
            }
            GuiResource.initComponentResources();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            return;
        }
        ContactFrame cf = new ContactFrame();
        cf.setVisible(true);
    }
}
