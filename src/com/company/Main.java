package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> links = new ArrayList();

    private static List<String> titres = new ArrayList<>();

    public static void main(String[] args) {

        add();
        for (int i = 0; i < links.size(); i++) {

            URL website = null;
            try {
                website = new URL(links.get(i));
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream((i + 1) + "-" + titres.get(i) + ".mp4");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static void add() {
        //System.out.println(links + "\n" + titres);
        System.out.println("Tap 'quit' pour quitter");
        boolean encore = true;
        while (encore) {
            System.out.print("Link : ");
            Scanner bf = new Scanner(System.in);
            String url = bf.nextLine();
            if (url.equals("quit")) {
                encore = false;
            } else {
                System.out.print("Titre : ");
                String titre = bf.nextLine();
                links.add(url);
                titres.add(titre);
            }
        }
    }
}
