package pt.clubedohardware.userinterface;

import pt.clubedohardware.userinterface.Animation;
import pt.clubedohardware.userinterface.IAnimation;

public class AnimationC implements IAnimationC{
	private String docName = null;
    private String pacName = null;
    private String windowName = null;
    private String speed = "default";
    public void story(String[] falas, String[] personagem){
        IAnimation zombieStory = new Animation();
        if (docName != null && !docName.equals(""))
            zombieStory.setDocName(docName);
        if (pacName != null && !docName.equals(""))
            zombieStory.setPacientName(pacName);
        if (windowName != null && !windowName.equals(""))
            zombieStory.setWindowName(windowName);
        zombieStory.setTempo(speed);
        zombieStory.story(falas, personagem);
    }
    public void setWindowName(String name){
        windowName = name;
    }
    public void setTempo(String v){
        boolean aux = v.equalsIgnoreCase("fast") || v.equalsIgnoreCase("slow") || v.equalsIgnoreCase("default");
        if (aux)
            speed = v;
    }
    public void setPacientName(String pacName){
        this.pacName = pacName;
    }
    public void setDocName(String docName){
        this.docName = docName;
    }
}
