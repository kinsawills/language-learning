package com.model;
/**
 * Defines a class for a user Avatar
 * @author christianruff
 */
public class Avatar {
    private CharacterOptions character;
    private HatOptions hat;

    /**
     * Constructs the default avatar: a llama and no hats
     * @return Returns the character's avatar
     */
    public Avatar() {
        this.character = CharacterOptions.LLAMA;
        this.hat = HatOptions.NONE;
    }

    /**
     * Parameterized constructor for an avatar
     * @param character String value of corresponding CharacterOptions enum
     * @param hat String value of corresponding HatOptions enum
     */
    public Avatar(String character, String hat) {
        this.character = CharacterOptions.getValue(character);
        this.hat = HatOptions.getValue(hat);
    }

    /**
     * Returns this avatar's character option
     * @return Avatar's character option
     */
    public CharacterOptions getCharacter() {
        return this.character;
    }

    /**
     * Returns this avatar's hat option
     * @return Avatar's hat option
     */
    public HatOptions getHat() {
        return this.hat;
    }


    /**
     * Sets the avatar's hat to a passed in parameter
     * @param hat A value in the HatOptions enum 
     * @return TRUE if the hat is set, FALSE if the hat is not set
     */
    public boolean setHat(HatOptions hat) {
        if (hat != null) {
            this.hat = hat;
            return true;
        }
        return false;
    }
    
    /**
     * Sets the character's hat to a passed in parameter
     * @param character A value in the CharacterOptions enum 
     * @return TRUE if the character is set, FALSE if the character is not set
     */
    public boolean setCharacter(CharacterOptions character) {
        if (character != null) {
            this.character = character;
            return true;
        }
        return false;
    }

    /**
     * Generates and returns a file path for the avatar image 
     * @return File path of the avatar's image
     */
    public String getImagePath() {
        if (this.character == CharacterOptions.LLAMA) {
            return "@../../../../resources/images/llama.png";
        } else if (this.character == CharacterOptions.GIRAFFE) {
            return "@../../../../resources/giraffe.png";
        } else if (this.character == CharacterOptions.LIZARD) {
            return "@../../../../resources/images/lizard.png";
        } else
            return "@../../../../resources/images/llama.png";
    }

    /**
     * Override toString to return character configuration
     * @return Returns character configuration in the format "[CHARACTER] [HAT]"
     */
    public String toString(){
        return this.character.toString() + " " + this.hat.toString();
    }
}
