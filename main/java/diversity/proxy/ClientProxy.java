package diversity.proxy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import diversity.Diversity;
import diversity.client.render.entity.RenderApache;
import diversity.client.render.entity.RenderAztec;
import diversity.client.render.entity.RenderDarkSpider;
import diversity.client.render.entity.RenderDart;
import diversity.client.render.entity.RenderEgyptian;
import diversity.client.render.entity.RenderInuit;
import diversity.client.render.entity.RenderLakeside;
import diversity.client.render.entity.RenderMummy;
import diversity.client.render.entity.RenderSettled;
import diversity.client.render.entity.RenderTibetan;
import diversity.client.render.entity.RenderTzitzimime;
import diversity.client.render.entity.RenderWarriorSkeleton;
import diversity.client.render.entity.RenderWorshipper;
import diversity.client.render.entity.RenderYeti;
import diversity.client.render.entity.RenderZulu;
import diversity.entity.EntityApache;
import diversity.entity.EntityAztec;
import diversity.entity.EntityDarkSpider;
import diversity.entity.EntityDart;
import diversity.entity.EntityEgyptian;
import diversity.entity.EntityInuit;
import diversity.entity.EntityLakeside;
import diversity.entity.EntityMummy;
import diversity.entity.EntitySettled;
import diversity.entity.EntityTibetan;
import diversity.entity.EntityTzitzimime;
import diversity.entity.EntityWarriorSkeleton;
import diversity.entity.EntityWorshipper;
import diversity.entity.EntityYeti;
import diversity.entity.EntityZulu;
import diversity.suppliers.EnumEntity;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;
import diversity.utils.ResourceTools;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityApache.class, new RenderApache());
		RenderingRegistry.registerEntityRenderingHandler(EntityAztec.class, new RenderAztec());
		RenderingRegistry.registerEntityRenderingHandler(EntityInuit.class, new RenderInuit());
		RenderingRegistry.registerEntityRenderingHandler(EntityZulu.class, new RenderZulu());
		RenderingRegistry.registerEntityRenderingHandler(EntityTibetan.class, new RenderTibetan());
		RenderingRegistry.registerEntityRenderingHandler(EntityEgyptian.class, new RenderEgyptian());
		RenderingRegistry.registerEntityRenderingHandler(EntityLakeside.class, new RenderLakeside());
		RenderingRegistry.registerEntityRenderingHandler(EntitySettled.class, new RenderSettled());
		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderMummy());
		RenderingRegistry.registerEntityRenderingHandler(EntityTzitzimime.class, new RenderTzitzimime());
		RenderingRegistry.registerEntityRenderingHandler(EntityWarriorSkeleton.class, new RenderWarriorSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
		RenderingRegistry.registerEntityRenderingHandler(EntityWorshipper.class, new RenderWorshipper());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkSpider.class, new RenderDarkSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityYeti.class, new RenderYeti());
	}
	
	/**
	  * To create eggs by getting colors from texture.
	  * @param entity
	  * @return
	  */
	@Override
	public Integer[] searchEggColor(EnumEntity entity)
    {
	    int principalColor = 0x000000;
	    int secondColor = 0xffffff;

		ResourceLocation resource = ResourceTools.getResource(entity.entityClass);
		if (resource == null) {
			return new Integer[] {principalColor, secondColor};
		}
	    
	    InputStream inputstream = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
   		try
   		{

            IResource iresource = Minecraft.getMinecraft().getResourceManager().getResource(resource);
            inputstream = iresource.getInputStream();
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            
		    for (int x = 0; x < bufferedimage.getWidth(); x++)
			{
			    for (int y = 0; y < bufferedimage.getHeight(); y++)
			    {
				    int color = bufferedimage.getRGB(x, y);
				    if (((color>>24) & 0xff) != 0)
				    {
					    if (map.containsKey(color))
					    {
						    map.put(color, map.get(color) + 1);
					    }
					    else
					    {
						    map.put(color, 1);
					    }
				    }
				}
			}    
		} catch (IOException e) {
	   		e.printStackTrace();
	   	}
   		finally
   		{ 
   			if (inputstream != null)
            {
                try {
   					inputstream.close();
   				} catch (IOException e) {
   					e.printStackTrace();
   				}
             }	
   		}
	    Collection<Integer> values = map.values();
	    int principalCount = 0;
	    int secondCount = 0;
	
	    for (Integer color : map.keySet())
	    {
		    if (map.get(color) > principalCount)
		    {
			    principalCount = map.get(color);
			    principalColor = color;
		    }
	
		    if (map.get(color) >= secondCount && map.get(color) != principalCount)
		    {
			    int principal= (principalColor >> 16) & 0x000000FF + (principalColor >>8 ) & 0x000000FF + (principalColor) & 0x000000FF;
			    int thisone= (color >> 16) & 0x000000FF + (color >>8 ) & 0x000000FF + (color) & 0x000000FF;
			    
		    	if (Math.abs(principal-thisone) > 150)
		    	{
				    secondCount = map.get(color);
				    secondColor = color;
		    	}
		    }
		}
	    return new Integer[] {principalColor, secondColor};
	}
	
	/**
	  * To create eggs by getting colors from texture.
	  * @param tribe
	  * @return
	  */
	@Override
	public Integer[] searchEggColor(EnumTribe tribe)
    {
	    int principalColor = 0x000000;
	    int secondColor = 0xffffff;
	    
	    InputStream inputstream = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	   	for (EnumVillager villager : tribe.villagers)
	   	{
   			ResourceLocation resource = VillagerRegistry.getVillagerSkin(villager.profession, null);
   			if (resource == null) {
   				continue;
   			}
	   		try
	   		{
	            IResource iresource = Minecraft.getMinecraft().getResourceManager().getResource(resource);
	            inputstream = iresource.getInputStream();
	            BufferedImage bufferedimage = ImageIO.read(inputstream);
	            
			    for (int x = 0; x < bufferedimage.getWidth(); x++)
				{
				    for (int y = 0; y < bufferedimage.getHeight(); y++)
				    {
					    int color = bufferedimage.getRGB(x, y);
					    if (((color>>24) & 0xff) != 0)
					    {
						    if (map.containsKey(color))
						    {
							    map.put(color, map.get(color) + 1);
						    }
						    else
						    {
							    map.put(color, 1);
						    }
					    }
					}
				}    
			} catch (IOException e) {
		   		e.printStackTrace();
		   	}
	   		finally
	   		{ 
	   			if (inputstream != null)
	            {
	                try {
	   					inputstream.close();
	   				} catch (IOException e) {
	   					e.printStackTrace();
	   				}
	             }	
	   		}
	   	}
	    Collection<Integer> values = map.values();
	    int principalCount = 0;
	    int secondCount = 0;
	
	    for (Integer color : map.keySet())
	    {
		    if (map.get(color) > principalCount)
		    {
			    principalCount = map.get(color);
			    principalColor = color;
		    }
	
		    if (map.get(color) >= secondCount && map.get(color) != principalCount)
		    {
			    int principal= (principalColor >> 16) & 0x000000FF + (principalColor >>8 ) & 0x000000FF + (principalColor) & 0x000000FF;
			    int thisone= (color >> 16) & 0x000000FF + (color >>8 ) & 0x000000FF + (color) & 0x000000FF;
			    
		    	if (Math.abs(principal-thisone) > 150)
		    	{
				    secondCount = map.get(color);
				    secondColor = color;
		    	}
		    }
		}
	    return new Integer[] {principalColor, secondColor};
	}
	
	@Override
	public void registerItemRenderer(EnumItem item) {
		MinecraftForgeClient.registerItemRenderer(item.item, item.renderer);
	}

	@Override
	public void registerVillagerSkin(EnumVillager villager) {
		ResourceLocation resource = new ResourceLocation(Diversity.MODID, villager.resourcePath);
		VillagerRegistry.instance().registerVillagerSkin(villager.profession, resource);
	}
	
	@Override
	public void registerEntityResource(EnumEntity entity) {
		ResourceTools.register(entity.entityClass, entity.resourcePath);
	}
	
	@Override
	public String getI18format(EnumVillager villager) {
		return I18n.format("entity." + Diversity.MODID + '.' + villager.tribe.name().toLowerCase() + '.' + villager.villagerName + ".name", new Object[0]);
	}
}
