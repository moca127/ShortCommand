package kr.mocha;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.server.ServerCommandEvent;
import cn.nukkit.plugin.PluginBase;
import kr.mocha.command.ShortenCommand;
import kr.mocha.manager.ShortCutManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class ShortCommand extends PluginBase implements Listener{
    public static ShortCommand instance;
    public ShortCutManager manager;

    @Override
    public void onEnable() {
	    instance = this;
        getDataFolder().mkdirs();
        save();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getCommandMap().register("shorten", new ShortenCommand());
        manager = new ShortCutManager();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        save();
        super.onDisable();
    }
    /*events*/
    @EventHandler
    public void onServerCommand(ServerCommandEvent event){
        String[] cmd = event.getCommand().split(" ");
        String firstCmd = cmd[0].replaceFirst("/","");

        if(manager.exists(firstCmd)){
            event.setCancelled();
            this.getServer().dispatchCommand(event.getSender(), getConfig().getString(firstCmd)+CmdToStr(cmd));
        }
    }
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event){
        String[] cmd = event.getMessage().split(" ");
        String firstCmd = cmd[0].replaceFirst("/","");

        if(manager.exists(firstCmd)){
            event.setCancelled();
            this.getServer().dispatchCommand(event.getPlayer(), getConfig().getString(firstCmd)+CmdToStr(cmd));
        }
    }

    /*===*/
    public void save(){
        getConfig().save();
    }
    public static ShortCommand getInstance(){
        return instance;
    }
    public String CmdToStr(String[] array){
        String result = "";

        for(int i = 1;i<array.length;i++){
            result += array[i];
            if(i<array.length - 1)result += " ";
        }

        return result;
    }
}
