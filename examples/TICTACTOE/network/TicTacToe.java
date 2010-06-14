class TicTacToe
{   // �Τ@�Ǳ`�ƭȪ��W�����{���X��e���\Ū
    public static final int HUMANOPP        = 0; 
    public static final int COMPUTER     = 1;
    public static final int NOLABEL        = 2; // �ѽL��m�O�Ū��A�i�H�U
    public static final int HUMANOPP_WIN    = 0;
    public static final int DRAW         = 1; // ����
    public static final int UNKNOWN      = 2; // �٥����ӭt
    public static final int COMPUTER_WIN = 3;
    public TicTacToe( )
    {
        clearBoard( );
    }
    public int [ ] [ ] getBoard( )
    {
        return board;
    }
    // �U��, �P���ˬd�U����m�O�_�٬O�Ŧ�, �άO�_���W�X�ѽL�d��
    public boolean recordMoveInArray( int YouKnowMe, int row, int column )
    {
        if( row < 0 || row >= 3 || column < 0 || column >= 3
                || board[ row ][ column ] != NOLABEL )
            return false;
        board[ row ][ column ] = YouKnowMe;
        return true;
    }
    // ��L���������ε{��
    public void clearBoard( )
    {
        for( int i = 0; i < 3; i++ )
            for( int j = 0; j < 3; j++ )
                board[ i ][ j ] = NOLABEL;
    }
    public boolean boardIsFull( )
    {
        for( int row = 0; row < 3; row++ )
            for( int column = 0; column < 3; column++ )
                if( board[ row ][ column ] == NOLABEL )
                    return false;
        return true;
    }
    boolean ResultIsWin( int YouKnowMe )
    {
        int row, column;

        // �d�ݬO�_����C�Ʀ������p
	for( row = 0; row < 3; row++ )
	{
            for( column = 0; column < 3; column++ )
                if( board[ row ][ column ] != YouKnowMe )
                    break;
            if( column >= 3 )
                return true;
	}
	// �d�ݬO�_�����Ʀ������p
	for( column = 0; column < 3; column++ )
	{
            for( row = 0; row < 3; row++ )
                if( board[ row ][ column ] != YouKnowMe )
                    break;
            if( row >= 3 )
                return true;
	}
	// �d�ݬO�_���﨤�u�Ʀ������p
        if( board[ 1 ][ 1 ] == YouKnowMe && board[ 2 ][ 2 ] == YouKnowMe
			&& board[ 0 ][ 0 ] == YouKnowMe )
            return true;

        if( board[ 0 ][ 2 ] == YouKnowMe && board[ 1 ][ 1 ] == YouKnowMe
			&& board[ 2 ][ 0 ] == YouKnowMe )
            return true;

        return false;
    }
    private int [ ] [ ] board = new int[ 3 ][ 3 ];
	// �U��
    private void place( int row, int column, int piece )
    {
        board[ row ][ column ] = piece;
    }
    
    private boolean squareIsNOLABEL( int row, int column )
    {
        return board[ row ][ column ] == NOLABEL;
    }
// �T�{�ثe���Ԫp
    private int CheckResult( )
    {
        return ResultIsWin( COMPUTER ) ? COMPUTER_WIN :
               ResultIsWin( HUMANOPP ) ? HUMANOPP_WIN :
               boardIsFull( )? DRAW : UNKNOWN;
    }
}
