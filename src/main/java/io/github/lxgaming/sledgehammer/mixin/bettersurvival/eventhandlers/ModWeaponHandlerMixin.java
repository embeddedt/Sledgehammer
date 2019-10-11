/*
 * Copyright 2019 Alex Thomson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.sledgehammer.mixin.bettersurvival.eventhandlers;

import com.mujmajnkraft.bettersurvival.eventhandlers.ModWeaponHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

@Mixin(value = ModWeaponHandler.class, priority = 1337, remap = false)
public abstract class ModWeaponHandlerMixin {
    
    @Redirect(method = "onEvent(Lnet/minecraftforge/event/entity/living/LivingAttackEvent;)V",
            at = @At(value = "INVOKE",
                    target = "Ljava/io/PrintStream;println(Ljava/lang/Object;)V"
            )
    )
    private void onPrintln(PrintStream printStream, Object object) {
        // no-op
    }
}