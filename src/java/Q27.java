public class Q27 {
  // password validator
  //  Length is at least 5 characters.
  //  At least one Large English letter.
  //  At least one small English letter.
  //  It should contain at least one digit.

  private static final String REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}";

  public boolean checkPassword(String password) {
    if(password == null)
      return false;
    return password.matches(REGEX);
  }

  public static void main(String[] args) {
    Q27 validator = new Q27();
    System.out.printf("%s", validator.checkPassword("22212a3"));
  }
}
