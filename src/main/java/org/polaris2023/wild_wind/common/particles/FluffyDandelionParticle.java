package org.polaris2023.wild_wind.common.particles;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import lombok.Getter;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.polaris2023.wild_wind.common.init.ModParticles;

import java.util.List;

public class FluffyDandelionParticle extends TextureSheetParticle {

    private static final double MAXIMUM_COLLISION_VELOCITY_SQUARED = 10000;

    public FluffyDandelionParticle(ClientLevel level, double x, double y, double z,
                                   double vx, double vy, double vz,
                                   SpriteSet sprites) {
        super(level, x, y, z, vx, vy, vz);

        // 生命周期（100-140刻，约5-7秒）
        this.lifetime = 100 + random.nextInt(40);

        // 物理特性
        this.gravity = 0.02F; // 下坠速度
        this.friction = 0.98F; // 空气阻力

        // 设置随机纹理
        setSprite(sprites.get(random.nextInt(4), 4));
    }

    @Override
    public void move(double x, double y, double z) {
        double d0 = x;
        double d1 = y;
        double d2 = z;
        if (this.hasPhysics
                && (x != 0.0 || y != 0.0 || z != 0.0)
                && x * x + y * y + z * z < MAXIMUM_COLLISION_VELOCITY_SQUARED) {
            Vec3 vec3 = Entity.collideBoundingBox(null, new Vec3(x, y, z), this.getBoundingBox(), this.level, List.of());
            x = vec3.x;
            y = vec3.y;
            z = vec3.z;
        }

        if (x != 0.0 || y != 0.0 || z != 0.0) {
            this.setBoundingBox(this.getBoundingBox().move(x, y, z));
            this.setLocationFromBoundingbox();
        }

        this.onGround = d1 != y && d1 < 0.0;
        if (d0 != x) {
            this.xd = 0.0;
        }

        if (d2 != z) {
            this.zd = 0.0;
        }
    }

    public static class Provider implements ParticleProvider<Data> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(Data data, ClientLevel level,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            // 使用粒子传递的水平初速度和垂直初速度
            FluffyDandelionParticle particle = new FluffyDandelionParticle(
                    level, x, y, z,
                    vx, vy, vz,
                    this.sprites
            );
            // 设置随机纹理索引
            particle.setSprite(sprites.get(level.random.nextInt(4), 4));
            return particle;
        }
    }

    @Override
    public void tick() {
        super.tick();

        // 粒子透明度变化（在生命周期结束时淡出）
        if (age > lifetime - 40) {
            this.alpha = (lifetime - age) / 40.0F;
        }

        // 粒子旋转效果
        this.oRoll = this.roll;
        this.roll += 0.1F;

        if(onGround){
            age++;
        }
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Data extends ParticleType<Data> implements ParticleOptions {
        public static final MapCodec<Data> MAP_CODEC = MapCodec.unit(Data::new);

        public Data() {
            super(false);
        }

        @Override
        public ParticleType<?> getType() {
            return ModParticles.FLUFFY_DANDELION.get();
        }

        @Override
        public MapCodec<Data> codec() {
            return MAP_CODEC;
        }

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, Data> streamCodec() {
            return StreamCodec.unit(new Data());
        }
    }
}
