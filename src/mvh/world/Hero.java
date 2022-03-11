package mvh.world;

import mvh.enums.Direction;

/**
 * A Monster is an Entity with a user provide WEAPON STRENGTH and ARMOR STRENGTH
 * @author Jonathan Hudson
 * @version 1.0
 */
public final class Hero extends Entity{

    /**
     * The user provided weapon strength
     */
    private final int weaponStrength;

    /**
     * The user provided armor strength
     */
    private final int armorStrength;

    /**
     * A Hero has regular health and symbol as well as a weapon strength and armor strength
     * @param health Health of hero
     * @param symbol Symbol for map to show hero
     * @param weaponStrength The weapon strength of the hero
     * @param armorStrength The armor strength of the hero
     */
    public Hero(int health, char symbol, int weaponStrength, int armorStrength) {
        super(symbol, health);
        this.weaponStrength = weaponStrength;
        this.armorStrength = armorStrength;
    }

    /**
     * The weapon strength of monster is from user value
     * @return The weapon strength of monster is from user value
     */
    @Override
    public int weaponStrength() {
        return weaponStrength;
    }

    /**
     * The armor strength of monster is from user value
     * @return The armor strength of monster is from user value
     */
    @Override
    public int armorStrength() {
        return armorStrength;
    }

    @Override
    public Direction chooseMove(World local) {
        int a=0;


        for  (int i=-2;i<3;i+=1){
            for ( int j=-2;j<3;j+=1){
                Entity b=local.getEntity(i,j);
                if (b instanceof Monster){
                    if (b.isAlive()){
                        for (Direction k:Direction.getDirections(i,j)){
                            Entity c= local.getEntity(i,j,k);
                            if (c.canMoveOnTopOf()){
                                return k;
                            }
                        }
                    }else{
                        a=2;
                    }

                }
            }

        }
        if (a==2){
            return Direction.NORTHWEST;
        }
        else if (a==){
            a=4;
        }
        if (a==4){
            m=false;
            return Direction.getRandomDirection();
        }
        boolean o=true;
        if (m){
            return  Direction.STAY;
        }
    }

    @Override
    public Direction attackWhere(World local) {


        for (int i = -1; i < 2 && i > -2; i += 1) {
            for (int j = -1; j < 2 && j > -2; j += 1) {
                Entity a = local.getEntity(i, j);
                if (a.isAlive()) {
                    if (a instanceof Monster) {
                        return Direction.getDirection(i, j);
                    }
                } else {
                    return null;
                }
            }
        }return null;
    }


    /**
     * Can only be moved on top of if dad
     * @return isDead()
     */x
    @Override
    public boolean canMoveOnTopOf() {
        return isDead();
    }

    /**
     * Can only be attacked if alive
     * @return isAlive()
     */
    @Override
    public boolean canBeAttacked() {
        return isAlive();
    }

    @Override
    public String toString(){
        return super.toString()+"\t"+weaponStrength+"\t"+armorStrength;
    }
}
