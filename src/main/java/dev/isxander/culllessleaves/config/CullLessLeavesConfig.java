package dev.isxander.culllessleaves.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CullLessLeavesConfig {
    public static final CullLessLeavesConfig INSTANCE = new CullLessLeavesConfig();

    public final Path configFile = FMLPaths.CONFIGDIR.get().resolve("cull-less-leaves.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public boolean enabled = true;
    public int depth = 2;

    public void save() {
        try {
            Files.deleteIfExists(configFile);

            JsonObject json = new JsonObject();
            json.addProperty("enabled", enabled);
            json.addProperty("depth", depth);

            Files.writeString(configFile, gson.toJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            if (Files.notExists(configFile)) {
                save();
                return;
            }

            JsonObject json = gson.fromJson(Files.readString(configFile), JsonObject.class);

            if (json.has("enabled"))
                enabled = json.getAsJsonPrimitive("enabled").getAsBoolean();
            if (json.has("depth"))
                depth = json.getAsJsonPrimitive("depth").getAsInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}