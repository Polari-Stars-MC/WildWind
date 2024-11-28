package org.polaris2023.wild_wind.util;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

@EqualsAndHashCode
@ToString
public final class MinMaxValue<T extends Number> {
    private boolean hasMax;
    private final T min;
    @Nullable
    private final T max;

    MinMaxValue(T min, @Nullable T max) {
        if (max == null) {
            this.hasMax = true;
        }

        this.min = min;
        this.max = max;
    }

    public static <T extends Number> MinMaxValue<T> of(T min, @Nullable T max) {
        return new MinMaxValue<T>(min, max);
    }

    public T min() {
        return min;
    }

    public T max() {
        if (max == null) {
            return min;
        }
        return max;
    }

    public boolean hasMax() {
        return hasMax;
    }

    public static class Other {
        public static final Codec<BigInteger> BIG_INTEGER_CODEC = getCodec(BigInteger::new);
        public static final Codec<BigDecimal> BIG_DECIMAL_CODEC = getCodec(BigDecimal::new);

        private static <T extends Number> Codec<T> getCodec(Function<String, T> function) {
            return new PrimitiveCodec<>() {
                @Override
                public <T1> DataResult<T> read(DynamicOps<T1> ops, T1 input) {
                    return ops.getStringValue(input).map(function);
                }

                @Override
                public <T1> T1 write(DynamicOps<T1> ops, T value) {
                    return ops.createString(value.toString());
                }
            };
        }
    }



    public static class Codecs {

        public static final Codec<MinMaxValue<Integer>> INT_CODEC = getCodec(Codec.INT);
        public static final Codec<MinMaxValue<Short>> SHORT_CODEC = getCodec(Codec.SHORT);
        public static final Codec<MinMaxValue<Long>> LONG_CODEC = getCodec(Codec.LONG);
        public static final Codec<MinMaxValue<Float>> FLOAT_CODEC = getCodec(Codec.FLOAT);
        public static final Codec<MinMaxValue<Double>> DOUBLE_CODEC = getCodec(Codec.DOUBLE);
        public static final Codec<MinMaxValue<Byte>> BYTE_CODEC = getCodec(Codec.BYTE);
        public static final Codec<MinMaxValue<BigInteger>> BIG_INTEGER_CODEC = getCodec(Other.BIG_INTEGER_CODEC);


        private static <T extends Number> Codec<MinMaxValue<T>> getCodec(Codec<T> codec) {
            return RecordCodecBuilder.create(instance -> instance
                    .group(
                            codec.fieldOf("min").forGetter(MinMaxValue::min),
                            codec.fieldOf("max").forGetter(MinMaxValue::max)
                    )
                    .apply(instance, MinMaxValue::of));
        }
    }

    public static class StreamCodecs {

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<Integer>> INT_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeInt(value.min());
                    buffer.writeInt(value.max() == null ? value.min() : value.max());
                }, buffer -> {
                    int min = buffer.readInt();
                    int max = buffer.readInt();
                    return MinMaxValue.of(min, max == min ? null : min);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<Short>> SHORT_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeShort(value.min());
                    buffer.writeShort(value.max() == null ? value.min() : value.max());
                }, buffer -> {
                    short min = buffer.readShort();
                    short max = buffer.readShort();
                    return MinMaxValue.of(min, max == min ? null : min);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<Long>> LONG_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeLong(value.min());
                    buffer.writeLong(value.max() == null ? value.min() : value.max());
                }, buffer -> {
                    long min = buffer.readShort();
                    long max = buffer.readShort();
                    return MinMaxValue.of(min, max == min ? null : min);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<Float>> FLOAT_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeFloat(value.min());
                    buffer.writeFloat(value.max() == null ? value.min() : value.max());
                }, buffer -> {
                    float min = buffer.readFloat();
                    float max = buffer.readFloat();
                    return MinMaxValue.of(min, max == min ? null : min);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<Double>> DOUBLE_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeDouble(value.min());
                    buffer.writeDouble(value.max() == null ? value.min() : value.max());
                }, buffer -> {
                    double min = buffer.readDouble();
                    double max = buffer.readDouble();
                    return MinMaxValue.of(min, max == min ? null : min);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<Byte>> BYTE_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeByte(value.min());
                    buffer.writeByte(value.max() == null ? value.min() : value.max());
                }, buffer -> {
                    byte min = buffer.readByte();
                    byte max = buffer.readByte();
                    return MinMaxValue.of(min, max == min ? null : min);
                }
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, MinMaxValue<BigInteger>> BIG_INTEGER_STREAM_CODEC = StreamCodec.of(
                (buffer, value) -> {
                    buffer.writeUtf(value.min().toString());
                    buffer.writeUtf(value.max() == null ? value.min().toString() : value.max().toString());
                }, buffer -> {
                    BigInteger min = new BigInteger(buffer.readUtf());
                    BigInteger max = new BigInteger(buffer.readUtf());
                    return MinMaxValue.of(min, min.compareTo(max) == 0 ? null : min);
                }
        );

    }


}
