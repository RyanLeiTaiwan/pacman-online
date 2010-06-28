import java.awt.*;
import java.awt.event.*;
public class TestTicTacToe extends Frame implements WindowListener, ActionListener
{
// �ҰʴѽL, ���ݨϥΪ��I��
    public void init( )
    {
        setLayout( new GridLayout( 3, 3 ) );
        for( int i = 0; i < 3; i++ )
           for( int j = 0; j < 3; j++ )
           {
               BookKeepArray[ i ][ j ] = new Button( );
               add( BookKeepArray[ i ][ j ] );
               BookKeepArray[ i ][ j ].addActionListener( this );
	       BookKeepArray[ i ][ j ].setFont(new Font("Serif", Font.BOLD, 72));
           }
        addWindowListener( this );
        newBoard( );
    }
    public TestTicTacToe( )
    {
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
    // ���q����@�ӴѨB
    // �Y�O�q�����U�A�Q��KeepGameInteresting�H����ܴѨB�A�W�[�C�����ܤ�
    // �_�h�N�n�I�sTryOneMove�ӿ�Ӧn�ѨB
    public void doComputerMove( boolean predictToWin )
    {
        BestMove ComputerMove;

        if( predictToWin )
            ComputerMove = t.TryOneMove( TicTacToe.COMPUTER );
        else
        {
            ComputerMove = new BestMove( 0, KeepGameInteresting % 3, KeepGameInteresting / 3 );
            KeepGameInteresting = ( KeepGameInteresting + 1 ) % 9;
        }

        System.out.println( " �q���諸�C����m = " + ComputerMove.row +
                            " �q���諸�檺��m = " + ComputerMove.column );
BookKeepArray[ ComputerMove.row ][ ComputerMove.column ].setLabel( computerYouKnowMe );
BookKeepArray[ ComputerMove.row ][ ComputerMove.column ].setEnabled( false );
        t.recordMoveInArray( TicTacToe.COMPUTER, ComputerMove.row, ComputerMove.column );
    }
// �C��������(�`�N����������), �}�l�t�@�L, ��e���
    public boolean GameOverRest( boolean condition, String message, boolean ComputerMoves )
    {
        if( condition )
        {
            System.out.println( message );
            System.out.println( "���s�}�l�s���@�L�C��!" );
            newBoard( );
            if( ComputerMoves )
            {
                System.out.println( "�q�����U�o!" );
                computerYouKnowMe = "X";
                HUMANOPPYouKnowMe = "O";
                doComputerMove( false );
            }
            else
            {
                HUMANOPPYouKnowMe = "X";
                computerYouKnowMe = "O";
                System.out.println( "�z���U�a!" );
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
            ( (Button)evt.getSource( ) ).setLabel( HUMANOPPYouKnowMe ); 
// �~�[����
            ( (Button)evt.getSource( ) ).setEnabled( false ); // �w�U�ѨB�A����A��
            for( int i = 0; i < 3; i++ )
                for( int j = 0; j < 3; j++ )
                    if( evt.getSource( ) == BookKeepArray[ i ][ j ] )
			// �b�}�C��Ƶ��c�W�O���ѨB
                        t.recordMoveInArray( TicTacToe.HUMANOPP, i, j );
	    // �b�Ѥ�U����A�ˬd�ӭt�A�ѽL���ӵL�ӭt�N�O����(Draw)
            if( GameOverRest( t.boardIsFull( ), "DRAW", true ) )
                return;
	    // ���q���U
            doComputerMove( true );
	    // �ˬd�ӭt           
            GameOverRest( t.ResultIsWin( TicTacToe.COMPUTER ), 
"�K!��Ĺ�F", true );
	    // �ˬd�O�_����(Draw)
            GameOverRest( t.boardIsFull( ), "DRAW", false );
            return; // ���Ѥ�U��
        }
    }
	// �D�{���A �إ߷s�ѽL�A�}�l��!
    public static void main( String [ ] args )
    {
        Frame f = new TestTicTacToe( );
        f.pack( );
        f.show( );
	// �ҩl����A���Ѥ�U��
    }
    private Button [ ][ ] BookKeepArray = new Button[ 3 ][ 3 ];//�}�l�ɴѽL�O�Ū�
    private TicTacToe t;
    private String computerYouKnowMe = "O";
    private String HUMANOPPYouKnowMe    = "X";
}
