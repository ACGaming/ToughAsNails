package toughasnails.client.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.ResourceLocation;
import toughasnails.core.ToughAsNails;

public class TextureAnimationFrame extends TextureAtlasSprite
{
    private String animationName;
    private int frame;
    
    public TextureAnimationFrame(String iconName, String animationName, int frame)
    {
        super(iconName);
        
        this.animationName = animationName;
        this.frame = frame;
    }
    
    //TODO: This still currently classifies as an animation, it should be turned into a proper static texture
    @Override
    public void updateAnimation() {}
    
    @Override
    public int[][] getFrameTextureData(int index)
    {
        return super.getFrameTextureData(this.frame);
    }
    
    @Override
    public boolean hasCustomLoader(IResourceManager manager, net.minecraft.util.ResourceLocation location)
    {
        return true;
    }

    @Override
    public boolean load(IResourceManager manager, ResourceLocation location)
    {
        location = new ResourceLocation(animationName);
        ResourceLocation fullLocation = TextureUtils.completeResourceLocation(location, 0);
        int mipmapLevels = Minecraft.getMinecraft().gameSettings.mipmapLevels;

        try
        {
            IResource iresource = manager.getResource(fullLocation);
            BufferedImage[] image = new BufferedImage[1 + mipmapLevels];
            AnimationMetadataSection animation = (AnimationMetadataSection) iresource.getMetadata("animation");
            
            if (animation != null)
            {
                image[0] = TextureUtil.readBufferedImage(iresource.getInputStream());

                int cachedFrameCount = this.frameCounter;
                this.loadSprite(image, animation);
                this.frameCounter = cachedFrameCount;
            }
            else
            {
                ToughAsNails.logger.error("Resource " + location + " isn't animated");
                return true;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return true;
        }
        
        return false;
    }
}
