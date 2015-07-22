package diversity.proxy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import diversity.Diversity;
import diversity.block.BlockFrozenChest;
import diversity.block.TileEntityFrozenChest;
import diversity.client.render.block.RenderBlockFrozenChest;
import diversity.client.render.block.TileEntityFrozenChestRenderer;
import diversity.client.render.entity.RenderApache;
import diversity.client.render.entity.RenderAztec;
import diversity.client.render.entity.RenderDarkSpider;
import diversity.client.render.entity.RenderDarkSpiderTest;
import diversity.client.render.entity.RenderDart;
import diversity.client.render.entity.RenderDwarf;
import diversity.client.render.entity.RenderEgyptian;
import diversity.client.render.entity.RenderInuit;
import diversity.client.render.entity.RenderLakeside;
import diversity.client.render.entity.RenderMummy;
import diversity.client.render.entity.RenderSettled;
import diversity.client.render.entity.RenderSpiderProjectile;
import diversity.client.render.entity.RenderTibetan;
import diversity.client.render.entity.RenderTzitzimime;
import diversity.client.render.entity.RenderWarriorSkeleton;
import diversity.client.render.entity.RenderWorshipper;
import diversity.client.render.entity.RenderYeti;
import diversity.client.render.entity.RenderZulu;
import diversity.client.render.item.RenderBlowgun;
import diversity.client.render.item.RenderSpear;
import diversity.entity.EntityApache;
import diversity.entity.EntityAztec;
import diversity.entity.EntityDarkSpider;
import diversity.entity.EntityDarkSpiderTest;
import diversity.entity.EntityDart;
import diversity.entity.EntityDwarf;
import diversity.entity.EntityEgyptian;
import diversity.entity.EntityInuit;
import diversity.entity.EntityLakeside;
import diversity.entity.EntityMummy;
import diversity.entity.EntitySettled;
import diversity.entity.EntitySpiderProjectile;
import diversity.entity.EntityTibetan;
import diversity.entity.EntityTzitzimime;
import diversity.entity.EntityWarriorSkeleton;
import diversity.entity.EntityWorshipper;
import diversity.entity.EntityYeti;
import diversity.entity.EntityZulu;
import diversity.item.ItemBlowgun;
import diversity.item.ItemSpear;
import diversity.suppliers.EnumBlock;
import diversity.suppliers.EnumEntity;
import diversity.suppliers.EnumItem;
import diversity.suppliers.EnumTribe;
import diversity.suppliers.EnumVillager;
import diversity.utils.ResourceTools;
import diversity.utils.VillagerRegistry;

public class ClientProxy extends ServerProxy
{
	@Override
	public void registerHandler() {
		handler = new ClientHandler();
    	MinecraftForge.TERRAIN_GEN_BUS.register(handler);    	  	
    	MinecraftForge.EVENT_BUS.register(handler);   
	}
	
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
		RenderingRegistry.registerEntityRenderingHandler(EntityDwarf.class, new RenderDwarf());
		RenderingRegistry.registerEntityRenderingHandler(EntityDarkSpiderTest.class, new RenderDarkSpiderTest());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpiderProjectile.class, new RenderSpiderProjectile());
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
	
	private static Map<Class, Class> map = new HashMap<Class, Class>();
	static {
		map.put(ItemBlowgun.class, RenderBlowgun.class);
		map.put(ItemSpear.class, RenderSpear.class);
	}
	
	@Override
	public void registerItemRenderer(EnumItem item) {
		if (map.containsKey(item.item.getClass())) {
			try {
				MinecraftForgeClient.registerItemRenderer(item.item, (IItemRenderer)map.get(item.item.getClass()).getConstructor().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	static {
		map.put(BlockFrozenChest.class, RenderBlockFrozenChest.class);
	}
	
	@Override
	public void registerBlockRenderer(EnumBlock block) {
		if (map.containsKey(block.blockClass)) {
			try {
				RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)map.get(block.blockClass).getConstructor().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	static {
		map.put(TileEntityFrozenChest.class, TileEntityFrozenChestRenderer.class);
	}
	
	@Override
	public void registerTileEntityRenderer(EnumBlock block) {
		if (map.containsKey(block.tileEntityClass)) {
			try {
				TileEntitySpecialRenderer renderer = (TileEntitySpecialRenderer) map.get(block.tileEntityClass).getConstructor().newInstance();
				TileEntityRendererDispatcher.instance.mapSpecialRenderers.put(block.tileEntityClass, renderer);
				renderer.func_147497_a(TileEntityRendererDispatcher.instance);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
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
	public void registerBlockRessource(EnumBlock block) {
		ResourceTools.register(block.blockClass, block.resourcePath);
	}

	@Override
	public String getI18format(EnumVillager villager) {
		return I18n.format("entity." + Diversity.MODID + '.' + villager.tribe.name().toLowerCase() + '.' + villager.villagerName + ".name", new Object[0]);
	}
}
