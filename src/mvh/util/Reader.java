package mvh.util;
import mvh.enums.WeaponType;
import mvh.world.Entity;
import mvh.world.Hero;
import mvh.world.Monster;
import mvh.world.World;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to assist reading in world file
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Reader {
    public static World loadWorld(File fileWorld) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileWorld));
            String line;
            int row = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(fileWorld))).get(0));
            int column = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(fileWorld))).get(1));
            int c = 0;
            World newworld = new World(row, column);
            while ((line = br.readLine()) != null) {
                int d = line.length();
                c++;
                if (line.contains("H")) {
                    List<String> e = new ArrayList<>();
                    String[] f = line.split(",");
                    Collections.addAll(e, f);
                    Hero hero = new Hero(Integer.parseInt(e.get(4)),(e.get(3)).charAt(0),Integer.parseInt(e.get(5)),Integer.parseInt(e.get(6)));
                    newworld.addEntity(Integer.parseInt(e.get(0)), Integer.parseInt(e.get(1)), hero);
                } else if (line.contains("M")){
                    List<String> g = new ArrayList<>();
                    String[] k = line.split(",");
                    Collections.addAll(g, k);
                    Monster monster = new Monster(Integer.parseInt(g.get(4)),(g.get(3)).charAt(0), WeaponType.getWeaponType((g.get(5)).charAt(0)));
                    newworld.addEntity(Integer.parseInt(g.get(0)), Integer.parseInt(g.get(1)), monster);






                }

            }return newworld;







        } catch (IOException e) {
            System.out.println("Error");
        }return null;


    }}
