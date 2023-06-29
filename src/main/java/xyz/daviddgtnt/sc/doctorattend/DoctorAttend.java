package xyz.daviddgtnt.sc.doctorattend;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DoctorAttend extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabled " + this.getName());
        this.getCommand("needdoctor").setExecutor(new NeedDoctorCommand());
        this.getCommand("doctorattend").setExecutor(new DoctorAttendCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disabled " + this.getName());
    }
}
