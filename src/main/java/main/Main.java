package main;

import dbService.DBService;
import gui.ContactFrame;
import gui.GuiResource;

/**
 * Created by Evgeniy Golubtsov on 08.02.2018.
 */
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0){
                GuiResource.initComponentResources();
            } else if (args.length == 1){
                GuiResource.initComponentResources(args[0]);
            } else {
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
