package kr.feathers.mc.minigame.pvp.manager;

import kr.feathers.mc.minigame.pvp.obj.PVPStadium;
import kr.feathers.utils.DataContainor;

import java.util.HashMap;
import java.util.Map;

public class PVPGameManager {
    public static Map<PVPStadium, Boolean> enabledPVPStadiums = new HashMap<>();
    public static PVPStadium stadium1 = new PVPStadium(1, DataContainor.getPVPStadiumSpawn(1, 1), DataContainor.getPVPStadiumSpawn(1, 2));
    public static PVPStadium stadium2 = new PVPStadium(2, DataContainor.getPVPStadiumSpawn(2, 1), DataContainor.getPVPStadiumSpawn(2, 2));
    public static PVPStadium stadium3 = new PVPStadium(3, DataContainor.getPVPStadiumSpawn(3, 1), DataContainor.getPVPStadiumSpawn(3, 2));
    public static PVPStadium stadium4 = new PVPStadium(4, DataContainor.getPVPStadiumSpawn(4, 1), DataContainor.getPVPStadiumSpawn(4, 2));
    public static PVPStadium stadium5 = new PVPStadium(5, DataContainor.getPVPStadiumSpawn(5, 1), DataContainor.getPVPStadiumSpawn(5, 2));
}
