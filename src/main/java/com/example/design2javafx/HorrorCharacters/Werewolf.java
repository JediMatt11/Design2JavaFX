package com.example.design2javafx.HorrorCharacters;
import java.util.ArrayList;

/**
 * Child class of HorrorCharacter that has increased attack power after transforming
 */
public class Werewolf extends HorrorCharacter implements Transformable
{
    private boolean isFullMoon;
    private int attackMultiplierFullMoon;
    public Werewolf(String initName, int initHealth, ArrayList<Vulnerability> initVulnerabilities, int initMaxPower, boolean initIsFullMoon, int initAttackMultiplierFullMoon)
    {
        super(initName, initHealth, initVulnerabilities, initMaxPower);
        isFullMoon = initIsFullMoon;
        attackMultiplierFullMoon = initAttackMultiplierFullMoon;
    }

    /**
     * if the target horror character has not fled, calculates base power to be used in attack damage calculations
     * if the werewolf object has transformed, damage is increased
     * if the target has a vulnerability to Sunlight or Fire, damage is further increased
     * @param target another HorrorCharacter
     */
    @Override
    public void attack(HorrorCharacter target)
    {
        if (target.getCanAttack())
        {
            int baseAttack = getPower() * (target.getVulnerabilities().contains(Vulnerability.Sunlight) ? 2 : target.getVulnerabilities().contains(Vulnerability.Fire) ? 2 : 1); //decreases target's health by Werewolf object's power multiplied by 2 if the target has a vulnerability to fire or sunlight
            if (isFullMoon)
            {
                target.setCurHealth(target.getCurHealth() - baseAttack * attackMultiplierFullMoon);
            }
            else
            {
                target.setCurHealth(target.getCurHealth() - baseAttack);
            }
        }
    }

    /**
     * if isFullMoon is false, the werewolf is allowed to flee when this is called
     * it calls the parent HorrorCharacter's flee method, making this object not attackable
     */
    @Override
    public void flee()
    {
        if (!isFullMoon)
        {
            super.flee(); //Werewolves are regular people until a full moon, and will not flee only during one
        }
    }

    /**
     * sets isFullMoon to the opposite of its current state
     */
    public void transform()
    {
        isFullMoon = !isFullMoon;
    }

    /**
     * Gives public access to the object's vulnerabilities
     * @return ArrayList<Vulnerability>
     */
    public static ArrayList<Vulnerability> accessWerewolfVulnerabilities()
    {
        ArrayList<Vulnerability> accessible = new ArrayList<>();
        accessible.add(Vulnerability.Magic);
        accessible.add(Vulnerability.Ice);
        return accessible;
    }


}
