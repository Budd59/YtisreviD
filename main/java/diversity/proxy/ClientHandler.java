package diversity.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import diversity.MapGenCaveDiversity;
import diversity.MapGenStructureDiversity;
import diversity.MapGenVillageDiversity;
import diversity.entity.EntitySpiderGlandArrow;
import diversity.entity.EntityWorshipper;
import diversity.suppliers.EnumBlock;
import diversity.suppliers.EnumItem;
import diversity.world.WorldGenBlueMushroom;
import diversity.world.WorldGenBlueVine;
import diversity.world.WorldGenFungus;
import diversity.world.WorldGenPhosMushroom;

public class ClientHandler extends ServerHandler
{	
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
