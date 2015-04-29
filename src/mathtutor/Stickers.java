/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathtutor;

/**
 *
 * @author
 */
public class Stickers {
    private final String grade;
    private final String reward;
    public Stickers(String grade,String reward)
    {
        this.grade = grade;
        this.reward = reward;
    }
    public String getReward()
    {
        return reward;
    }
    public String getGrade()
    {
        return grade;
    }
}
