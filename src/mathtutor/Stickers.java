/*
*Learn-2-Math team:
 *  Eric Sullivan
 *  Tauseef Pirzada
 *  Leonid Melnikov
 *
 * Date Produced:
 *  4/2/15 - 4/30/15
 *
 *  Purpose of project:
 *The purpose of the project was to create a software that could be used by 
 *children aged 3-10 in Pre-k to Grade 4. The software is a Math tutoring software 
 *that will teach kids number sense and other important skills that they will use 
 *throughout life. Our goal was that the software be easy to learn because our clients 
 *would be mostly children, and that it helped teach the important concepts that they are
 *supposed to learn in each grade. This is meant to be a supplement to schoolwork and helps 
 *reinforce concepts that are learned in the class room. 
 */
package mathtutor;

/**
 *Stickers class
 *gets the sticker based on the grade/reward for module
 */
public class Stickers {
    private final String grade;
    private final String reward;
    private boolean isShown;
    /**
     * constructor for the stickers class
     * @param grade
     * @param reward 
     */
    public Stickers(String grade,String reward)
    {
        this.grade = grade;
        this.reward = reward;
        isShown = false;
    }
    /**
     * Getters and setters for various functions
     * @return 
     */
    public String getReward()
    {
        return reward;
    }
    public String getGrade()
    {
        return grade;
    }
    public boolean getVisibility()
    {
        return isShown;
    }
    public void setVisibility(boolean isShown)
    {
        this.isShown = isShown;
    }
    
}
