/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package toughasnails.init;

import static toughasnails.api.achievement.TANAchievements.campfire_song;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import toughasnails.api.TANBlocks;

public class ModAchievements 
{
    public static final AchievementPage achievementPage = new AchievementPage("Tough As Nails");
    
    public static void init()
    {
        AchievementPage.registerAchievementPage(achievementPage);
        
        addAchievements();
    }
    
    private static void addAchievements()
    {
        campfire_song = addAchievement("achievement.campfire_song", "campfire_song", 0, 0, new ItemStack(TANBlocks.campfire), null);
    }
    
    private static Achievement addAchievement(String unlocalizedName, String identifier, int column, int row, ItemStack iconStack, Achievement parent)
    {
        Achievement achievement = new Achievement(unlocalizedName, identifier, column, row, iconStack, parent);
        achievement.registerStat();
        achievementPage.getAchievements().add(achievement);
        return achievement;
    }
}
