/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: EDUARDO BARROS BENETTI
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {
    
    private ObjectInputStream input;
    
    /**
     *  opens an ObjectInputStream using a FileInputStream
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    
    private void readObjectsFromFile(String name) throws ClassNotFoundException, IOException
    {
        MusicRecord record ;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }
        
        /* The following loop is supposed to use readObject method of
         * ObjectInputStream to read a MusicRecord object from a binary file that
         * contains several records.
         * Loop should terminate when an EOFException is thrown.
         */
        
        try
        {
            while ( true )
            {
                
               	record =  (MusicRecord) input.readObject();
                
            	
                System.out.print(record.getYear() + "   "
                		+ record.getSongName() + "   "
                		+ record.getSingerName() + "   "
                		+ record.getPurchasePrice() + "   \n");
                
                // TO BE COMPLETED BY THE STUDENTS
           
            }   // END OF WHILE
        } catch (EOFException e) {
        	System.out.println("The end of the file being read, was reached");
        } catch (IOException e) {
        	e.printStackTrace();
        } catch(ClassNotFoundException e) {
        	e.printStackTrace();
        }
                // ADD NECESSARY catch CLAUSES HERE
        
    }           // END OF METHOD 
    
    
    public static void main(String [] args) throws ClassNotFoundException, IOException
    {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("mySongs.ser");
    }
}