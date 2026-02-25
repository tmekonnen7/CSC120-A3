// You should **not** update any call signatures in this file
// only modify the body of each function
import java.util.Random;
import java.util.Scanner;

class Conversation implements ConversationRequirements {

  // Attributes 
  private String transcript;
  private String[] cannedResponses;
  private Random rand;
  /**
   * Constructor 
   */
  Conversation() {
    transcript = "";
    cannedResponses = new String[] {
      "Mmhmm.",
      "Okay, tell me more.",
      "Interesting."
    };

    rand = new Random();
    
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    Scanner sc = new Scanner(System.in);
    System.out.print("How many rounds?");
    int rounds = sc.nextInt();
    sc.nextLine();

    
    String greet = "Hi there!  What's on your mind?";
    System.out.println(greet);

    transcript += greet +"\n";


    for (int i = 0; i < rounds; i++){
      
      String userInput = sc.nextLine();
      transcript += userInput + "\n";

      String response = respond(userInput);
      System.out.println(response);

      transcript += response + "\n";
    }

    String bye = "See ya!";
    System.out.println(bye);
    transcript += bye + "\n";
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT:");
    System.out.println(transcript);
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    String lowInput = inputString.toLowerCase();

    if (containsMirrorWord(lowInput)){
      return mirror(inputString);
    } else {
      int index = rand.nextInt(cannedResponses.length);
      return cannedResponses[index];
    }
  }

  //This method will check for words that can be mirrored 
  private boolean containsMirrorWord(String input) {
    return input.contains (" i ") || 
    input.contains (" me ") || 
    input.contains(" am ") || 
    input.contains("you ") ||
    input.contains(" you ") || 
    input.contains(" my ") || 
    input.contains(" your ");
    
  }

  //Mirror
  private String mirror(String input){
    String response = input.toLowerCase();

    response = response.replaceAll("\\bi\\b", "placeholder");
    response = response.replaceAll("\\bme\\b", "you");
    response = response.replaceAll("\\bam\\b", "are");
    response = response.replaceAll("\\byou\\b", "I");
    response = response.replaceAll("you\\b", "I");
    response = response.replaceAll("are\\b", "am");
    response = response.replaceAll("\\bmy\\b", "your");
    response = response.replaceAll("\\byour\\b", "my");
    response = response.replaceAll("placeholder", "you");

    return response + "?";

  }



  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
