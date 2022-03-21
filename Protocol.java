package pl.edu.pg.student.testowy;

public class Protocol {
    private static final int WAITING = 0;
    private static final int SECONDSTATE = 1;
    private static final int THIRDSTATE = 2;
    private static final int FOURTHSTATE = 3;
    private static final int FIFTHSTATE = 4;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int messages_count = -1;

    public String processMessages(Message message) {
        messages_count--;
        if (messages_count == 0)
            state = FIFTHSTATE;
        return "That's a nice message! ID:" + message.getNumber() + " " + message.getContent();
    }


    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "Ready for your service";
            state = SECONDSTATE;
        } else if (state == SECONDSTATE) {
            theOutput = "Ready for " + theInput + " messages";
            messages_count = Integer.parseInt(theInput);
            state = THIRDSTATE;
        } else if (state == FIFTHSTATE) {
            theOutput = "Finished";
        } else {
            theOutput = "An error has occured";
        }
        return theOutput;
    }
}