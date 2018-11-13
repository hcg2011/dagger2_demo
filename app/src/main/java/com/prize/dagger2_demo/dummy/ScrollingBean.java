package com.prize.dagger2_demo.dummy;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/30 15:44
 */
public class ScrollingBean {
    private String name1 = " \"Material is the metaphor.\\n\\n\"\n" +
            "\n" +
            "        \"A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
            "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
            "        \"technologically advanced and open to imagination and magic.\\n\"\n" +
            "        \"Surfaces and edges of the material provide visual cues that are grounded in reality. The \"\n" +
            "        \"use of familiar tactile attributes helps users quickly understand affordances. Yet the \"\n" +
            "        \"flexibility of the material creates new affordances that supercede those in the physical \"\n" +
            "        \"world, without breaking the rules of physics.\\n\"\n" +
            "        \"The fundamentals of light, surface, and movement are key to conveying how objects move, \"\n" +
            "        \"interact, and exist in space and in relation to each other. Realistic lighting shows \"\n" +
            "        \"seams, divides space, and indicates moving parts.\\n\\n\"";
    private String name2;
    private String name3;
    private String name4;
    private String name5;
    private String name6;
    private String name7;

    public ScrollingBean() {

    }

    public ScrollingBean(String name1) {
        this.name1 = name1;
    }

    public ScrollingBean(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getName5() {
        return name5;
    }

    public void setName5(String name5) {
        this.name5 = name5;
    }

    public String getName6() {
        return name6;
    }

    public void setName6(String name6) {
        this.name6 = name6;
    }

    public String getName7() {
        return name7;
    }

    public void setName7(String name7) {
        this.name7 = name7;
    }
}
