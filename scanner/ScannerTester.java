package scanner;
import java.io.*;

/**
 * Tests the Scanner class. 
 *
 * @author Amiya Chokhawala
 * @version January 28, 2022 
 */
public class ScannerTester
{
    /**
     * Main method for ScannerTester class. 
     * 
     * @param   args arguments from the command line
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ScanErrorException
    {
        File ScannerTest = new File
            ("/Users/amarchokhawala/Desktop/Compilers/Scanner/scanner/ScannerTest.txt");
        InputStream sTest = new FileInputStream(ScannerTest);
        Scanner scannerT = new Scanner(sTest);
        
        File ScannerTestAdvanced = new File
        ("/Users/amarchokhawala/Desktop/Compilers/Scanner/scanner/scannerTestAdvanced.txt");
        InputStream saTest = new FileInputStream(ScannerTestAdvanced);
        Scanner scannerAdvT = new Scanner(saTest);

        while(scannerT.hasNext())
        {
            System.out.println(scannerT.nextToken());
        }

        System.out.println("Status is " + scannerT.hasNext());
    }
}
