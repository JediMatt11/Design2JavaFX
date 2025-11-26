package com.example.design2javafx.HorrorCharacters;

import java.util.ArrayList;

/**
 * Child class of HorrorCharacter that can attack with increased power after transforming
 */
public class Vampire extends HorrorCharacter implements Transformable
{
    private boolean isDaytime;
    private boolean isHumanoid;
    private int attackMultiplierHumanoid;
    public Vampire(String initName, int initHealth, ArrayList<Vulnerability> initVulnerabilities, int initMaxPower, boolean initIsDaytime, boolean initIsHumanoid, int initAttackMultiplierHumanoid)
    {
        super(initName, initHealth, initVulnerabilities, initMaxPower);
        isDaytime = initIsDaytime;
        isHumanoid = initIsHumanoid;
        attackMultiplierHumanoid = initAttackMultiplierHumanoid;
    }

    /**
     * if the target horror character has not fled, calculates base power to be used in attack damage calculations
     * if the vampire object has transformed into a humanoid, damage is increased
     * if the target has a vulnerability to Ice or Magic, damage is further increased
     * @param target another HorrorCharacter
     */
    @Override
    public void attack(HorrorCharacter target)
    {
        if (target.getCanAttack())
        {
            int baseAttack = getPower() * (target.getVulnerabilities().contains(Vulnerability.Ice) ? 2 : target.getVulnerabilities().contains(Vulnerability.Magic) ? 2 : 1); //decreases target's health by Vampire object's power multiplied by 2 if the target has a vulnerability to ice or magic
            if (isHumanoid)
            {
                target.setCurHealth(target.getCurHealth() - baseAttack * attackMultiplierHumanoid);
            }
            else
            {
                target.setCurHealth(target.getCurHealth() - baseAttack);
            }
        }
    }

    /**
     * if isDaytime is true, the vampire is allowed to flee when this is called
     * it calls the parent HorrorCharacter's flee method, making this object not attackable
     */
    @Override
    public void flee()
    {
        if (isDaytime)
        {
            super.flee(); //Vampires are more dangerous at night, so they will only want to flee a battle in the daytime
        }
    }

    /**
     * sets isHumanoid to the opposite of its current state
     */
    public void transform()
    {
        isHumanoid = !isHumanoid;
    }

    /**
     * Gives public access to the object's vulnerabilities
     * @return ArrayList<Vulnerability>
     */
    public static ArrayList<Vulnerability> accessVampireVulnerabilities()
    {
        ArrayList<Vulnerability> accessible = new ArrayList<>();
        accessible.add(Vulnerability.holyWater);
        accessible.add(Vulnerability.Sunlight);
        return accessible;
    }

}
