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

package io.github.lxgaming.sledgehammer.configuration.category.mixin;

import io.github.lxgaming.sledgehammer.configuration.annotation.Mapping;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class VaultopicMixinCategory {
    
    @Mapping(value = "vaultopic.client.ProjectorMixin", dependencies = {"vaultopic"})
    @Setting(value = "load-matrixes", comment = "If 'true', fixes NullPointerException in Projector::loadMatrixes.")
    private boolean loadMatrixes = false;
    
    @Mapping(value = "vaultopic.VaultopicMixin", dependencies = {"vaultopic"})
    @Setting(value = "packet-handling", comment = "If 'true', processes VTOpenVice and VTRecipe packets on the Main Thread")
    private boolean packetHandling = false;
    
    public boolean isLoadMatrixes() {
        return loadMatrixes;
    }
    
    public boolean isPacketHandling() {
        return packetHandling;
    }
}