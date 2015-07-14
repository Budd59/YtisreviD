package diversity.utils;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import diversity.Diversity;

public class ResourceTools {

	private static Map<Class, ResourceLocation> resources =  new HashMap<Class, ResourceLocation>();
	
	public static void register(Class objectClass, String resourcePath)
	{
		if (resourcePath != null) {
			resources.put(objectClass, new ResourceLocation(Diversity.MODID, resourcePath));
		}
	}
	
	public static ResourceLocation getResource(Class objectClass)
	{
		return resources.get(objectClass);
	}
}
