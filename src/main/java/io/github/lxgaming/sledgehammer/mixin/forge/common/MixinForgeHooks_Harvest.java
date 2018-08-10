/*
 * Copyright 2018 Alex Thomson
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

package io.github.lxgaming.sledgehammer.mixin.forge.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.common.event.tracking.PhaseTracker;
import org.spongepowered.common.interfaces.world.IMixinWorld;

@Mixin(value = ForgeHooks.class, priority = 1337, remap = false)
public abstract class MixinForgeHooks_Harvest {
    
    @Inject(method = "canHarvestBlock",
            at = @At(value = "INVOKE",
                    target = "Lorg/spongepowered/common/event/tracking/IPhaseState;isInteraction()Z"
            ),
            cancellable = true
    )
    private static void onCanHarvestBlock(Block block, EntityPlayer entityPlayer, IBlockAccess blockAccess, BlockPos blockPos, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        // Prevents ClassCastException caused by Sponge assuming the IBlockAccess is an instanceof IMixinWorld
        if (!PhaseTracker.getInstance().getCurrentPhaseData().state.isInteraction() && !(blockAccess instanceof IMixinWorld)) {
            callbackInfoReturnable.setReturnValue(false);
            
            // Using ACTION_BAR to prevent chat spam
            ((Player) entityPlayer).sendMessage(ChatTypes.ACTION_BAR, Text.of(TextColors.RED, "Harvest denied at (", blockPos.getX(), ", ", blockPos.getY(), ", ", blockPos.getZ(), ")"));
        }
    }
}