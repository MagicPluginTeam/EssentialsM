package kr.feathers.mc.minigame.pvp.obj;

import org.bukkit.Location;

public class PVPStadium {
    private final int id;
    private final Location spawn1;
    private final Location spawn2;


    public PVPStadium(int id, Location spawn1, Location spawn2) {
        this.id = id;
        this.spawn1 = spawn1;
        this.spawn2 = spawn2;
    }


    public int getId() {
        return id;
    }

    public Location getSpawn1() {
        return spawn1;
    }

    public Location getSpawn2() {
        return spawn2;
    }
}
