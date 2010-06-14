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
    // �M�w�U�@�B�Ӱ�(O)�Τe(X)
    public BestMove TryOneMove( int YouKnowMe )
    {
        int opp;              // �Ĥ�
        BestMove GiveItATry;           // �Ĥ誺�̨ιﵦ
        int CurrentResult;       
        int bestRow = 0;
        int bestColumn = 0;
        int value;
	// �I�sCheckResult()�ݥثe���L�աA���p�٥����ӭt�A�~�~�򩹤U����
        if( ( CurrentResult = CheckResult( ) ) != UNKNOWN )
            return new BestMove( CurrentResult );

        if( YouKnowMe == COMPUTER )
        {   // �H�U�Ѫ��H�ӻ��A�ڭ̷|���]�L�|�]�kĹ
            opp = HUMANOPP; value = HUMANOPP_WIN;
        }
        else
        {   // �H�q���ӻ��A��M�]�O�|�]�kĹ
            opp = COMPUTER; value = COMPUTER_WIN;
        }
	// �C�@�B�i��U���ѨB���n�Ҽ{��
        for( int row = 0; row < 3; row++ )
            for( int column = 0; column < 3; column++ )
                if( squareIsNOLABEL( row, column ) ) // �u���ŴѤ~��U
                {   // �ثe�I�sTryOneMove�����@����U�@�B
                    place( row, column, YouKnowMe );
		    // ���I�sTryOneMove��ܤ@�B�n�ѨB
                    GiveItATry = TryOneMove( opp );
		    // �]���u�O�w���A���]���p�U���ѨB�n�٭�
                    place( row, column, NOLABEL );
		    // ���ެO���@��A�u�n�I�sTryOneMove�A�N�n��̨Ϊ��ѨB
                    if( YouKnowMe == COMPUTER && GiveItATry.val > value ||
                        YouKnowMe == HUMANOPP && GiveItATry.val < value )
                    {
                        value = GiveItATry.val;
                        bestRow = row; bestColumn = column;
                    }
                }
        return new BestMove( value, bestRow, bestColumn );
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
