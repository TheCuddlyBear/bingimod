package me.thecuddlybear.bingi.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FollowPlayerGoal extends Goal {
    private final Mob mob;

    @Nullable
    private Player followingMob;
    @Nullable
    private String followingPlayerName;
    private final Class<? extends LivingEntity> followingMobType;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private float oldWaterCost;
    private final float areaSize;

    public FollowPlayerGoal(Mob pMob, Class<? extends LivingEntity> pFollowingMobType, double pSpeedModifier, float pStopDistance, float pAreasize){
        this(pMob, pFollowingMobType, pSpeedModifier, pStopDistance, pAreasize, null);
    }

    public FollowPlayerGoal(Mob pMob, Class<? extends LivingEntity> pFollowingMobType, double pSpeedModifier, float pStopDistance, float pAreasize, String pPlayerFollowName){
        this.mob = pMob;
        this.followingPlayerName = pPlayerFollowName;
        this.followingMobType = pFollowingMobType;
        this.speedModifier = pSpeedModifier;
        this.navigation = pMob.getNavigation();
        this.stopDistance = pStopDistance;
        this.areaSize = pAreasize;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        if (!(pMob.getNavigation() instanceof GroundPathNavigation) && !(pMob.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowPlayerGoal");
        }
    }
    @Override
    public boolean canUse() {
        if(this.followingMobType == Player.class){
            if(this.followingPlayerName != null){
                List<Player> list = this.mob.level.getEntitiesOfClass(Player.class, this.mob.getBoundingBox().inflate((double)this.areaSize));
                for(Player player : list){
                    if(player.getName().getString().equals(this.followingPlayerName)){
                        if(!player.isInvisible()){
                            this.followingMob = player;
                            return true;
                        }
                    }
                }
            } else{
                List<Player> list = this.mob.level.getEntitiesOfClass(Player.class, this.mob.getBoundingBox().inflate((double)this.areaSize));
                for(Player player : list){
                    if(!player.isInvisible()){
                        this.followingMob = player;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.followingMob != null && !this.navigation.isDone() && this.mob.distanceToSqr(this.followingMob) > (double)(this.stopDistance * this.stopDistance);
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
        this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.followingMob = null;
        this.navigation.stop();
        this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        if(this.followingMob != null && !this.mob.isLeashed()){
            this.mob.getLookControl().setLookAt(this.followingMob, 10.0F, (float)this.mob.getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0){
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                double d0 = this.mob.getX() - this.followingMob.getX();
                double d1 = this.mob.getY() - this.followingMob.getY();
                double d2 = this.mob.getZ() - this.followingMob.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if(!(d3 <= (double)(this.stopDistance * this.stopDistance))){
                    this.navigation.moveTo(this.followingMob, this.speedModifier);
                }else {
                    this.navigation.stop();
                    LookControl lookControl = this.mob.getLookControl();
                    if (d3 <= (double)this.stopDistance || lookControl.getWantedX() == this.mob.getX() && lookControl.getWantedY() == this.mob.getY() && lookControl.getWantedZ() == this.mob.getZ()){
                        double d4 = this.followingMob.getX() - this.mob.getX();
                        double d5 = this.followingMob.getZ() - this.mob.getZ();
                        this.navigation.moveTo(this.mob.getX() - d4, this.mob.getY(), this.mob.getZ()-d5, this.speedModifier);
                    }
                }
            }
        }
    }
}
