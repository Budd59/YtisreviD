package diversity.utils;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import diversity.Diversity;

public class ResourceTools {

	private static Map<Class, ResourceLocation> resources =  new HashMap<Class, ResourceLocation>();
	
	public static void register(Class entityClass, String resourcePath)
	{
		if (resourcePath != null) {
			resources.put(entityClass, new ResourceLocation(Diversity.MODID, resourcePath));
		}
	}
	
	public static ResourceLocation getResource(Class entityClass)
	{
		return resources.get(entityClass);
	}
}
