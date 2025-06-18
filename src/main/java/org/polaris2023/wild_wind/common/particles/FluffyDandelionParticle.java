package org.polaris2023.wild_wind.common.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.phys.Vec3;
import org.polaris2023.wild_wind.common.init.ModParticles;

public class FluffyDandelionParticle extends TextureSheetParticle {

    public static final Vec3 MAX_WIND = new Vec3(0.5, -0.02, 0.25);
    public static final Vec3 WIND_DIRECTION = MAX_WIND.normalize();
    public static final float MAX_WIND_ADJUSTMENT = 0.012f;
    public static final float VERTICAL_PERTURBATION_STRENGTH = 0.01f;

    private float ticker = 0;

    public FluffyDandelionParticle(ClientLevel level, double x, double y, double z,
                                   double vx, double vy, double vz,
                                   SpriteSet sprites) {
        super(level, x, y, z, vx, vy, vz);

        this.xd = vx;
        this.yd = vy;
        this.zd = vz;

        // 生命周期（100-140刻，约5-7秒）
        this.lifetime = 100 + random.nextInt(40);

        // 物理特性
        this.gravity = 0.02F; // 下坠速度
        this.friction = 0.98F; // 空气阻力
        this.alpha = 0;

        scale(1.5F);

        // 设置随机纹理
        setSprite(sprites.get(random.nextInt(4), 4));
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

        if (ticker < 1) this.ticker += 0.025F;

        // 粒子透明度变化（在生命周期结束时淡出）
        if (age <= 10) {
            this.alpha = age * 0.1F;
        }
        if (age > lifetime - 40) {
            this.alpha = (lifetime - age) / 40.0F;
            this.scale(0.98F);
        }

        applyWindEffect();
        updatePerturbationDirection();

        // 粒子旋转效果
        this.oRoll = this.roll;
        this.roll += 0.1F;

        if (onGround) {
            age++;
        }
    }

    private void applyWindEffect() {
        // 获取当前风向
        Vec3 windDirection = WIND_DIRECTION;

        // 计算风在粒子当前方向上的分量
        Vec3 currentDirection = new Vec3(xd, yd, zd).normalize();
        double dotProduct = currentDirection.dot(windDirection);

        // 根据年龄因子平滑调整风力影响
        float windInfluence = ticker * MAX_WIND_ADJUSTMENT;

        // 添加风力
        this.xd += windDirection.x * windInfluence;
        this.yd += windDirection.y * windInfluence;
        this.zd += windDirection.z * windInfluence;

        this.xd = Math.min(this.xd, MAX_WIND.x);
        this.yd = Math.max(this.yd, MAX_WIND.y);
        this.zd = Math.min(this.zd, MAX_WIND.z);

        // 如果点积为负（逆向风），增加阻力
        if (dotProduct < 0) {
            double resistanceFactor = (1 - dotProduct) * 0.5 * windInfluence;
            this.xd *= (1 - resistanceFactor);
            this.zd *= (1 - resistanceFactor);
        }
    }

    private void updatePerturbationDirection() {
        // 计算垂直于风向的随机方向
        // 首先创建一个与风向垂直的向量
        Vec3 randomVec = new Vec3(random.nextDouble() - 0.5, random.nextDouble() - 0.5, random.nextDouble() - 0.5);

        // 确保不是零向量
        if (randomVec.length() < 0.001) {
            randomVec = new Vec3(0.1, 0.1, 0.1);
        }

        // 计算垂直于风向的向量：减去与风向平行的分量
        double dotProduct = randomVec.dot(WIND_DIRECTION);
        Vec3 parallelComponent = WIND_DIRECTION.scale(dotProduct);
        Vec3 normalize = randomVec.subtract(parallelComponent).normalize();

        float strength = VERTICAL_PERTURBATION_STRENGTH * (0.5f + ticker * 0.8F);

        this.xd += normalize.x * strength * (random.nextDouble() * 0.5 + 0.5);
        this.yd += normalize.y * strength * (random.nextDouble() * 0.5 + 0.5);
        this.zd += normalize.z * strength * (random.nextDouble() * 0.5 + 0.5);
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
