package kr.mocha.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import kr.mocha.manager.ShortCutManager;

/**
 * Created by mocha on 16. 11. 13.
 */
public class ShortenCommand extends Command{
    public ShortCutManager manager = new ShortCutManager();

    public ShortenCommand(){
        super("shorten", "명령어를 줄여줍니다.", "/shorten <add|del|list|reset>");
        this.setPermission("shorten.cmd");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!sender.hasPermission(this.getPermission()))
            sender.sendMessage(TextFormat.RED+"명령어의 권한이 없습니다.");
        else{
            try{
                switch (args[0]){
                    case "add":
                    case "a":
                    case "추가":
                        manager.addShortCut(arrayToString(args),args[1]);
                        sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"명령어가 단축되었습니다.");
                        break;
                    case "del":
                    case "d":
                    case "삭제":
                        manager.delShortCut(args[1]);
                        sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"명령어단축이 삭제되었습니다.");
                        break;
                    case "list":
                    case "l":
                    case "목록":
                        String result = "";

                        for(String s : manager.getList())
                            result += s+", ";
                        sender.sendMessage("=== ShortCuts ===");
                        sender.sendMessage(TextFormat.GREEN+result);
                        break;
                    case "reset":
                    case "r":
                    case "초기화":
                        manager.reset();
                        sender.sendMessage(TextFormat.AQUA+"[ 알림 ] "+TextFormat.GRAY+"명령어단축이 초기화되었습니다.");
                        break;
                    default:
                        sender.sendMessage(TextFormat.RED+this.getUsage());
                        break;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                sender.sendMessage(TextFormat.RED+this.getUsage());
            }
        }
        return false;
    }
    private String arrayToString(String[] args){
        String result = "";

        for(int i = 2;i<args.length;i++)
            result += args[i]+" ";
        return result;
    }
}
