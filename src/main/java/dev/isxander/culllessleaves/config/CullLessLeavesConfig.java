/*
 * This file is part of CullLessLeavesReforged - https://github.com/CCr4ft3r/CullLessLeavesReforged
 * Copyright (C) isXanderIsDev and contributors: https://github.com/isXander/CullLessLeaves
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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