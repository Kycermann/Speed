package xyz.mieszko.speed;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Mieszko Kycermann
 */
public class Speed extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("speed").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender.isOp() || sender.hasPermission("speed.use"))) {
            sender.sendMessage("§4You cannot use this command.");
            return true;
        }
        
        if(!(sender instanceof Player)) {
            sender.sendMessage("§4You cannot use this command from the console.");
            return true;
        }
        
        float speed = 0;
        
        try {
            speed = Float.parseFloat(args[0]);
        } catch(NumberFormatException ex) {
            return false;
        }
        
        if(speed < 1 || speed > 10) {
            sender.sendMessage("Speed must be between 0 and 10.");
            return true;
        }
        
        Player p = (Player) sender;
        
        if(p.isFlying()) {
            p.setFlySpeed(0.1f + ((speed - 1f) / 9f) * 0.9f);
            p.sendMessage("Set fly speed to " + speed);
        } else {
            p.setWalkSpeed(0.2f + ((speed - 1f) / 9f) * 0.8f);
            p.sendMessage("Set walk speed to " + speed);
        }
        
        return true;
    }
}
