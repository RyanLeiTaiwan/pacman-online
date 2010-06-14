import java.awt.*;
import java.awt.event.*;
import java.io.*; 	// new
import java.net.*;	// new
import java.util.StringTokenizer;  // new
// �ڭ̥���TestTicTacToe1��Computer�o�@�䪺
public class TestTicTacToe1 extends Frame implements WindowListener, ActionListener
{
int portNo;
InetAddress addr;
DatagramSocket ssocket; 
DatagramSocket rsocket;
// �ҰʴѽL, ���ݨϥΪ��I��
    public void init( )
    {
	this.portNo =5556;
	System.out.print("Please input the IP address of destination :");
	BufferedReader uip= new BufferedReader(new
		InputStreamReader(System.in)); 
            try{
	String ServerIP=uip.readLine();
	this.addr=InetAddress.getByName(ServerIP);
	this.ssocket=new DatagramSocket(1500);
	this.rsocket=new DatagramSocket(1600);
            } catch (SocketException e) {
              e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
        }
        setLayout( new GridLayout( 3, 3 ) );
        for( int i = 0; i < 3; i++ )
           for( int j = 0; j < 3; j++ )
           {
               BookKeepArray[ i ][ j ] = new Button( );
               add( BookKeepArray[ i ][ j ] );
               BookKeepArray[ i ][ j ].addActionListener( this );
	       BookKeepArray[ i ][ j ].setFont(new Font("Serif", Font.BOLD, 100));
           }
        addWindowListener( this );
        newBoard( );
//_____________________________________________________
// Computer�o�@��@�}�l����human�U!
	byte buffer1[]=new byte[100];   
	try {
	DatagramPacket packet1=new DatagramPacket(buffer1,100,this.addr,1600);
	System.out.println("wait on receiving ...�����U!");
	this.rsocket.receive(packet1); 
	String msg1=new String(buffer1,0,packet1.getLength());
	System.out.println("����U�����T�� : " + msg1); 
	StringTokenizer stok1 = new StringTokenizer(msg1,",");
	int dontcare = Integer.parseInt(stok1.nextToken());
	int x1=Integer.parseInt(stok1.nextToken());
	int y1=Integer.parseInt(stok1.nextToken());
	BookKeepArray[x1][y1].setLabel(HUMANOPPYouKnowMe);
	BookKeepArray[x1][y1].setEnabled(false);
	t.recordMoveInArray( TicTacToe.HUMANOPP, x1, y1 );
	}catch (SocketException e) {
            		e.printStackTrace();
        	} catch (IOException e) {
            		e.printStackTrace();
}
//_____________________________________________________
    }
    public TestTicTacToe1( String whoami)
    {
        super("TestTicTacToe1 (was computer: " +whoami);
        init( );
    }
// ���� ! �s�ؤ@��TicTacToe���ר�, �M�����s�W������
    public void newBoard( )
    {
        t = new TicTacToe( );
        for( int i = 0; i < 3; i++ )
           for( int j = 0; j < 3; j++ )
           {
               BookKeepArray[ i ][ j ].setLabel( "" );	// �L����
               BookKeepArray[ i ][ j ].setEnabled( true );	// �i�I���U�ѨB
           }
    }
    private int KeepGameInteresting = 0;
// �C��������(�`�N����������), �}�l�t�@�L, ��e�����
// ����ɥ�human���U, �_�h��Ĺ�����U
    public boolean GameOverRest( boolean condition, String message, boolean ComputerMoves )
    {
        if( condition )
        {
            System.out.println( message );
            System.out.println( "���s�}�l�s���@�L�C��!" );
            newBoard( );
            if( ComputerMoves )
            {
                  System.out.println( "���U�o!" );
            }
            else
            {
                  System.out.println( "�������U�a!" );
              	byte buffer2[]=new byte[100];   
	try {
	DatagramPacket packet2=new DatagramPacket(buffer2,100,this.addr,1600);
	System.out.println("wait on receiving ...�����U!");
	this.rsocket.receive(packet2); 
	String msg2=new String(buffer2,0,packet2.getLength());
	System.out.println("����U�����T�� : " + msg2); 
	StringTokenizer stok2 = new StringTokenizer(msg2,",");
	int dontcare2 = Integer.parseInt(stok2.nextToken());
	int x2=Integer.parseInt(stok2.nextToken());
	int y2=Integer.parseInt(stok2.nextToken());
	BookKeepArray[x2][y2].setLabel(HUMANOPPYouKnowMe);
	BookKeepArray[x2][y2].setEnabled(false);
	t.recordMoveInArray( TicTacToe.HUMANOPP, x2, y2 );
	}catch (SocketException ex) {
            		ex.printStackTrace();
        	} catch (IOException ex) {
           		 ex.printStackTrace();
        	}
            }
        }
        return condition;
    }
    // WindowListener��������@
    public void windowClosing( WindowEvent event ) 
    {
        System.exit( 0 );
    }          
    public void windowClosed( WindowEvent event ) { }
    public void windowDeiconified( WindowEvent event ) { }
    public void windowIconified( WindowEvent event ) { }
    public void windowActivated( WindowEvent event ) { }
    public void windowDeactivated( WindowEvent event ) { }
    public void windowOpened( WindowEvent event ) { }
// �B�z���s�I��(button click)�ƥ�A�`�N�����ɪ��B�z�覡
    public void actionPerformed( ActionEvent evt )
    {
        if( evt.getSource( ) instanceof Button )
        {   // �Ѥ�C���@�U�ѽL�A�N�n�i��B�z
            ( (Button)evt.getSource( ) ).setLabel( computerYouKnowMe ); 
// �~�[����
            ( (Button)evt.getSource( ) ).setEnabled( false ); // �w�U�ѨB�A����A��
            int i=0; int  j=0; int ii=0, jj=0;
            for( i = 0; i < 3; i++ )
                for( j = 0; j < 3; j++ )
                    if( evt.getSource( ) == BookKeepArray[ i ][ j ] )
	// �b�}�C��Ƶ��c�W�O���ѨB
                       { t.recordMoveInArray( TicTacToe.COMPUTER, i, j );
				ii=i; jj=j;};
           System.out.println("TestTicTacToe1 : (i,j) "+ii+" "+jj);
           Integer oi = new Integer(ii);  
           Integer oj = new Integer(jj);
           Integer oid = new Integer(TicTacToe.COMPUTER);
           String dataOut = oid.toString()+","+oi.toString()+","+oj.toString();
           int oLength=dataOut.length();   
           byte buffer[]=new byte[oLength];     
           System.out.println(dataOut);
           buffer=dataOut.getBytes();
           try {
	DatagramPacket packet=new DatagramPacket(buffer,oLength,this.addr,1800); 
	System.out.println(this.portNo);
	this.ssocket.send(packet);     
	System.out.println("sent ..., check!");     
	    // �b�Ѥ�U����A�ˬd�ӭt�A�ѽL���ӵL�ӭt�N�O����(Draw)
          	 if (GameOverRest( t.ResultIsWin( TicTacToe.COMPUTER ), 
		"�K!��Ĺ�F", true )) return;
 	if( GameOverRest( t.boardIsFull( ), "DRAW", false ) )
                	return;
	// ��Human�U
	DatagramPacket packet1=new DatagramPacket(buffer,oLength,this.addr,1600); 
	System.out.println("comp wait receiving ...");
	this.rsocket.receive(packet1); 
	String msg=new String(buffer,0,packet1.getLength());
	System.out.println("����U�����T�� : " + msg); 
	StringTokenizer stok = new StringTokenizer(msg,",");
	int dontcare = Integer.parseInt(stok.nextToken());
	int x=Integer.parseInt(stok.nextToken());
	int y=Integer.parseInt(stok.nextToken());
	BookKeepArray[x][y].setLabel(HUMANOPPYouKnowMe);
	BookKeepArray[x][y].setEnabled(false);
	t.recordMoveInArray( TicTacToe.HUMANOPP, x, y );
	} catch (SocketException e) {
            		e.printStackTrace();
        	} catch (IOException e) {
            		e.printStackTrace();
        }
	    // �ˬd�ӭt           
            GameOverRest( t.ResultIsWin( TicTacToe.HUMANOPP ), 
           		"�K!�ڿ�F", false );
	    // �ˬd�O�_����(Draw)
            GameOverRest( t.boardIsFull( ), "DRAW", false );
            return; // ���Ѥ�U��
        }
    }
	// �D�{���A �إ߷s�ѽL�A�}�l��!
    public static void main( String [ ] args )
    {
        Frame f = new TestTicTacToe1("�ϥ�O" );
        f.pack( );
        f.show( );
	// �ҩl����A���Ѥ�U��
    }
    private Button [ ][ ] BookKeepArray = new Button[ 3 ][ 3 ];//�}�l�ɴѽL�O�Ū�
    private TicTacToe t;
    private String computerYouKnowMe = "O";
    private String HUMANOPPYouKnowMe    = "X";
}
