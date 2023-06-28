package xyz.daviddgtnt.sc.doctorattend;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabled " + this.getName());
        this.getCommand("needdoctor").setExecutor(new NeedDoctor());
        this.getCommand("doctorattend").setExecutor(new DoctorAttend());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disabled " + this.getName());
    }
}
