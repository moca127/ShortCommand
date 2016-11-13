package kr.mocha.manager;

import cn.nukkit.utils.Config;
import kr.mocha.ShortCommand;

import java.util.Set;

/**
 * Created by mocha on 16. 11. 13.
 */
public class ShortCutManager {
    public Config config = ShortCommand.getInstance().getConfig();

    public boolean addShortCut(String target, String shortCut){
        try{
            config.set(shortCut, target);
            config.save();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean delShortCut(String shortCut){
        try{
            config.remove(shortCut);
            config.save();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public void reset(){
        for(String s : config.getKeys())
            config.remove(s);
    }
    public boolean exists(String target){
        return config.exists(target);
    }
    public Set<String> getList(){
        return config.getKeys();
    }
}
