package de.marcely.pcel;

import org.bukkit.entity.EntityType;

public enum CameraEffect {
	
	Normal(EntityType.VILLAGER),
	Creeper(EntityType.CREEPER),
	Enderman(EntityType.ENDERMAN),
	Spider(EntityType.SPIDER);
	
	private EntityType selected_type;
	
	private CameraEffect(EntityType type){
		this.selected_type = type;
	}
	
	public EntityType getEntityType(){
		return this.selected_type;
	}
	
	public static CameraEffect getCameraEffect(String str){
		for(CameraEffect effect:values()){
			if(effect.name().equalsIgnoreCase(str))
				return effect;
		}
		
		return null;
	}
}
