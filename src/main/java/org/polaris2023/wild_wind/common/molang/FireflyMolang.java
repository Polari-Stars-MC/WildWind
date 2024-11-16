package org.polaris2023.wild_wind.common.molang;

import io.github.tt432.eyelib.molang.MolangScope;
import io.github.tt432.eyelib.molang.mapping.api.MolangFunction;
import io.github.tt432.eyelib.molang.mapping.api.MolangMapping;
import org.polaris2023.wild_wind.common.entity.Firefly;

import java.util.function.Function;

@MolangMapping(value = "firefly", pureFunction = false)
public final class FireflyMolang {
    @MolangFunction(value = "ticker", description = "age ticker")
    public static int ticker(MolangScope scope) {
        Function<Firefly, Integer> function = Firefly::getTicker;
        return scope.getOwner().ownerAs(Firefly.class)
                .map(function)
                .orElse(0);
    }

    private FireflyMolang() {}
}
