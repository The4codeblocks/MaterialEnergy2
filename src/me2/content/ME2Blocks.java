package me2.content;

import me13.core.block.instance.EnumTextureMapping;
import me13.core.block.instance.Layer;
import me2.world.ME2Block;
import me2.world.ME2Bridge;
import me2.world.ME2Cable;
import me2.world.ME2TransportationBus;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;

public class ME2Blocks {
    public static Block cable, cableJunction, cableSwitch, adapter, bridge,
            balancer, exportBus, importBus, controller, terminal;

    public static void load() {
        cable = new ME2Cable("cable") {{
            drawBase = false;
            requirements(Category.distribution, ItemStack.empty);
            layers.add(new Layer(this, "-", EnumTextureMapping.TF_TYPE) {{
                this.hand = ME2Cable.DEFAULT_HAND;
                this.hand2 = ME2Cable.DEFAULT_SCHEME_HAND;
            }});
        }};

        terminal = new ME2Block("terminal") {{
            size = 3;
            configurable = true;
            typeId = ME2Block.TERMINAL_TYPE;
            requirements(Category.effect, ItemStack.empty);
        }};

        controller = new ME2Block("me-controller") {{
            requirements(Category.effect, ItemStack.empty);
            typeId = ME2Block.CONTROLLER_TYPE;
            consumePowerDynamic((ME2Build b) -> {
                return b.controllerScl() * 360;
            });
        }};

        importBus = new ME2TransportationBus("import-bus", true) {{
            requirements(Category.distribution, ItemStack.empty);
        }};

        exportBus = new ME2TransportationBus("export-bus", false) {{
            requirements(Category.distribution, ItemStack.empty);
            configurable = true;
        }};

        cableSwitch = new ME2Cable("cable-switch") {{
            isGate = true;
            drawBase = false;
            configurable = true;
            requirements(Category.logic, ItemStack.empty);
            layers.add(
                    new SwitchLayer(this, "-enabled", EnumTextureMapping.REGION) {{
                        this.activates = true;
                        this.hand = ME2Cable.DEFAULT_HAND;
                        this.hand2 = ME2Cable.DEFAULT_SCHEME_HAND;
                    }},
                    new SwitchLayer(this, "-disabled", EnumTextureMapping.REGION) {{
                        this.activates = false;
                        this.hand = ME2Cable.DEFAULT_HAND;
                        this.hand2 = ME2Cable.DEFAULT_SCHEME_HAND;
                    }}
            );
        }};

        balancer = new ME2Block("balancer") {{
            requirements(Category.effect, ItemStack.empty);
            typeId = ME2Block.BALANCER_TYPE;
        }};

        adapter = new ME2Block("adapter") {{
            rotate = true;
            rotateDraw = false;
            quickRotate = true;
            drawBase = false;
            requirements(Category.effect, ItemStack.empty);
            layers.add(new Layer(this, "-", EnumTextureMapping.ROT) {{
                this.rotate = false;
            }});
            typeId = ME2Block.ADAPTER_TYPE;
        }};

        cableJunction = new ME2Cable("cable-junction") {{
            isJunction = true;
            requirements(Category.distribution, ItemStack.empty);
        }};

        bridge = new ME2Bridge("bridge") {{
            drawBase = false;
            requirements(Category.distribution, ItemStack.empty);
            layers.add(new Layer(this, "-", EnumTextureMapping.ROT) {{
                this.rotate = false;
            }});
        }};
    }
}