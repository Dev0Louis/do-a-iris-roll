package dev.louis.doairisroll.transformer;

import net.irisshaders.iris.mixin.MixinModelViewBobbing;
import net.lenni0451.classtransform.InjectionCallback;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import nl.enjarai.doabarrelroll.api.RollCamera;
import nl.enjarai.doabarrelroll.math.MagicNumbers;
import org.joml.Matrix4f;

@CTransformer(value = MixinModelViewBobbing.class)
public abstract class MixinModelViewBobbingTransformer {

    @CInject(method = "iris$applyBobbingToModelView*", target = @CTarget(value = "INVOKE", target = "Lorg/joml/Matrix4f;rotateXYZ(FFF)Lorg/joml/Matrix4f;", shift = CTarget.Shift.BEFORE))
    public void addSomething(Matrix4f instance, float angleX, float angleY, float angleZ, float tickDelta, InjectionCallback injectionCallback) {
        instance.rotateZ((float) (((RollCamera) ((GameRenderer) (Object) this).getCamera()).doABarrelRoll$getRoll() * MagicNumbers.TORAD));
    }
}
