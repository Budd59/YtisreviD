package diversity;

import org.apache.logging.log4j.Level;

import com.sun.xml.internal.stream.Entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import diversity.entity.EntityWorshipper;
import diversity.suppliers.EnumBlock;

public class DiversityHandler
{
	public final static MapGenVillageDiversity mapGenVillageDiversity = new MapGenVillageDiversity();
	public final static MapGenStructureDiversity mapGenStructureDiversity = new MapGenStructureDiversity();
	public final static MapGenCaveDiversity mapGenCaveStructureDiversity = new MapGenCaveDiversity();
	
	@SubscribeEvent
	public void checkUpdate(InitMapGenEvent event)
	{
		if (EventType.VILLAGE == event.type) {
			event.newGen = mapGenVillageDiversity;
		} else if (EventType.SCATTERED_FEATURE == event.type) {
			event.newGen = mapGenStructureDiversity;
		}
	}
    
	@SubscribeEvent
    public void OnSpawnEntity(CheckSpawn event) {
    	int x = (int) event.x;
    	int y = (int) event.y;
    	int z = (int) event.z;

    	boolean flag = false;
    	for (int tempX = -1; tempX <= 1; tempX++)
        for (int tempZ = -1; tempZ <= 1; tempZ++)
        {
        	int tempY = y;
        	while (event.world.getBlock(x, tempY, z).equals(Blocks.air) || event.world.getBlock(x, tempY, z) instanceof BlockFalling) {
        		tempY--;
        	}
	    	if (event.world.getBlock(x, tempY, z).equals(EnumBlock.fungal.block)
	    			|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_mushroom_cap.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_mushroom_cap.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_water.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_mushroom.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.phos_mushroom.block)
					|| event.world.getBlock(x, tempY, z).equals(EnumBlock.blue_vine.block)) {
	    		flag = true;
	    	}
        }
    	if (flag) {
			if (event.entity instanceof EntityWitch || event.entity instanceof EntityWorshipper) {
				event.setResult(Result.ALLOW);
			} else {
				event.setResult(Result.DENY);
			}
    	} else if (event.entity instanceof EntityWorshipper) {
			event.setResult(Result.DENY);
    	}
    	
    	if (event.entity instanceof EntitySpider)
    	{
	    	for (int tempX = -1; tempX <= 1; tempX++)
	        for (int tempZ = -1; tempZ <= 1; tempZ++)
            {
    	    	if (event.world.getBlock(x, y, z).equals(Blocks.web)) {
    	    		event.setResult(Result.ALLOW);
    	    	}
            }
		}
    }
	
	@SubscribeEvent
    public void OnFogEvent(FogColors event)
	{		
		Block block = ActiveRenderInfo.getBlockAtEntityViewpoint(Minecraft.getMinecraft().theWorld, event.entity, (float) event.renderPartialTicks);
        float f10;
        
        float red = event.red;
        float green = event.green;;
        float blue = event.blue;;
        
        if (block.equals(EnumBlock.phos_water.block))
        {
            f10 = (float)EnchantmentHelper.getRespiration(event.entity) * 0.2F;
            red = 0.16F + f10;
            green = 0.02F + f10;
            blue = 0.2F + f10;
        }
        if (block.equals(EnumBlock.poison_water.block))
        {
            f10 = (float)EnchantmentHelper.getRespiration(event.entity) * 0.2F;
            red = 0.02F + f10;
            green = 0.2F + f10;
            blue = 0.02F + f10;
        }
        

        double d0 = (event.entity.lastTickPosY + (event.entity.posY - event.entity.lastTickPosY) * event.renderPartialTicks) * Minecraft.getMinecraft().theWorld.provider.getVoidFogYFactor();

        if (event.entity.isPotionActive(Potion.blindness))
        {
            int i = event.entity.getActivePotionEffect(Potion.blindness).getDuration();

            if (i < 20)
            {
                d0 *= (double)(1.0F - (float)i / 20.0F);
            }
            else
            {
                d0 = 0.0D;
            }
        }

        if (d0 < 1.0D)
        {
            if (d0 < 0.0D)
            {
                d0 = 0.0D;
            }

            d0 *= d0;
            red = (float)((double)red * d0);
            green = (float)((double)green * d0);
            blue = (float)((double)blue * d0);
        }

        float f11;
        float f6;

        if (event.entity.isPotionActive(Potion.nightVision))
        {
        	int i = Minecraft.getMinecraft().thePlayer.getActivePotionEffect(Potion.nightVision).getDuration();
        	f11 = i > 200 ? 1.0F : 0.7F + MathHelper.sin(((float)i - (float)event.renderPartialTicks) * (float)Math.PI * 0.2F) * 0.3F;
            f6 = 1.0F / red;

            if (f6 > 1.0F / green)
            {
                f6 = 1.0F / green;
            }

            if (f6 > 1.0F / blue)
            {
                f6 = 1.0F / blue;
            }

            red = red * (1.0F - f11) + red * f6 * f11;
            green = green * (1.0F - f11) + green * f6 * f11;
            blue = blue * (1.0F - f11) + blue * f6 * f11;
        }

        if (Minecraft.getMinecraft().gameSettings.anaglyph)
        {
            f11 = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            f6 = (red * 30.0F + green * 70.0F) / 100.0F;
            float f7 = (red * 30.0F + blue * 70.0F) / 100.0F;
            red = f11;
            green = f6;
            blue = f7;
        }
        
        event.red = red;
        event.green = green;
        event.blue = blue;
    }
	
	
}
