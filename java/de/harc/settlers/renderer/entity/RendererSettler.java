package de.harc.settlers.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererSettler extends RendererLivingEntity {

	private final ModelBiped villagerModel;

	public RendererSettler() {
		super(new ModelBiped(0.0F), 0.5F);
		villagerModel = (ModelBiped) mainModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(final Entity var1) {
		return new ResourceLocation("textures/entity/steve.png");
	}

	@Override
	protected void passSpecialRender(final EntityLivingBase var1, final double var2, final double var4, final double var6) {
		func_147906_a(var1, "test", var2, var4 - 0.825D, var6, 64);
	}
}
