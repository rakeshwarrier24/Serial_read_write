/**
 * Read through serial communication between PC and Arduino board
 *
 * @date 11.May.2017
 * @author Krishnanand K.R., EMDL, NUS 
 * @author Hoang Duc Chinh {@literal <chinhhd@gmail.com>}
 */
 
import java.io.*;
import gnu.io.*;
import java.util.Enumeration;

public class Krish_Serial {
    private static SerialPort p;
    public static void main(String[] args) 
    {	Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        System.out.println("Start Program");
        while(ports.hasMoreElements())
        {   CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            //System.out.println(port.getName() + "#" + port.getCurrentOwner());            
            if(port.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {	if(port.getName().equals("COM7"))
				{	System.out.println("Found COM1 Serial-Port");
					try 
					{	p = (SerialPort) port.open("Krish_Serial", 1000);
						int baudRate = 9600; // in bps
						p.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
						p.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
					} 
					catch (PortInUseException | UnsupportedCommOperationException e) { System.out.println(e.getMessage()); }	
				}
            }                        
        }
        System.out.println("Port Initialized");
        try 
        {	InputStream  inStream  = p.getInputStream();
			OutputStream outStream = p.getOutputStream();
			while(true) 
			{	if(inStream.available() > 0) 
				{	int b = inStream.read();
					System.out.print( (char)b );
					if(b=='Q') { System.exit(0);}
				}
			}
		}
		catch (Exception ex) { ex.printStackTrace(); }
        System.out.println("Program Exiting");
    }
}
