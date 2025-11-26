package com.example.design2javafx.HorrorCharacters;

import java.util.ArrayList;

/**
 * Child class of HorrorCharacter that has different behaviour during daytime or no daytime
 */
public class Zombie extends HorrorCharacter
{
    private boolean isDaytime;
    public Zombie(String initName, int initHealth, ArrayList<Vulnerability> initVulnerabilities, int initMaxPower, boolean initIsDaytime)
    {
        super(initName, initHealth, initVulnerabilities, initMaxPower);
        isDaytime = initIsDaytime;
    }

    /**
     * if the target horror character has not fled, calculates base power to be used in attack damage calculations
     * if the target has a vulnerability to Sword or holyWater, damage is increased
     * @param target another HorrorCharacter
     */
    @Override
    public void attack(HorrorCharacter target)
    {
        if (target.getCanAttack())
        {
            target.setCurHealth(target.getCurHealth() - (getPower() * (target.getVulnerabilities().contains(Vulnerability.Sword) ? 2 : target.getVulnerabilities().contains(Vulnerability.holyWater) ? 2 : 1))); //decreases target's health by Zombie object's power multiplied by 2 if the target has a vulnerability to swords or holy water
        }
    }

    /**
     * if isDaytime is true, the zombie is allowed to flee when this is called
     * it calls the parent HorrorCharacter's flee method, making this object not attackable
     */
    @Override
    public void flee()
    {
        if (isDaytime)
        {
            super.flee(); //Zombies can't survive sunlight but otherwise always attack, so they will only want to flee a battle in the daytime
        }
    }

    /**
     * Gives public access to the object's vulnerabilities
     * @return ArrayList<Vulnerability>
     */
    public static ArrayList<Vulnerability> accessZombieVulnerabilities()
    {
        ArrayList<Vulnerability> accessible = new ArrayList<>();
        accessible.add(Vulnerability.Sword);
        accessible.add(Vulnerability.Fire);
        return accessible;
    }

}
