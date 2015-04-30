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

/*
 Tauseef Pirzada
 06122013
 Xor class containing three static methods
 Handling Encryption, hex padding, and decryption
 */
public class Xor
{
  public static final byte[] KEY = {0xD,0xE,0xA,0xD,0xB,0xE,0xE,0xF};
  /*
   * encrypt
   * Will encrypt a string using Xor Encryption
   * Key will be DEADBEEF
   * Will format the encrypted string to hex with two byte padding
   * @argument String to be encrypted
   * @return Encrypted String prepared to be written to file
   * */
  public static String  encrypt(String szPass)
  {
    byte[] bPass = szPass.getBytes();
    String szEncrypt = "";
    for(int i = 0; i < bPass.length; ++i)
    {
      bPass[i] ^= KEY[i%KEY.length];
      szEncrypt+= String.format("%02X",bPass[i]);
    }
    return szEncrypt;
  }
  /*
   * undoHex
   * private, will only be used by this class
   * Will remove the hex two byte padding from the encrypted string
   * @argument String that is encrypted in hex format
   * @return String that is encrypted without hex format
   * */
  private static String undoHex(String str)
  {
    
    StringBuilder sb = new StringBuilder();
    for( int i=0; i<str.length()-1; i+=2 )
      sb.append((char)Byte.parseByte(str.substring(i, (i + 2)), 16));
    return sb.toString();
  }
  /*
   * decrypt
   * Will call undoHex then decrypt the string using Xor Decryption
   * Key will be DEADBEEF
   * @argument Encrypted String (Read from file)
   * @return Decrypted String ready to be parsed
   * */
  public static String decrypt(String szPass)
  {
    byte[] bPass = undoHex(szPass).getBytes();
    for(int i = 0; i < bPass.length;++i)
      bPass[i] ^= KEY[i%KEY.length];
    return new String(bPass);
  }
}