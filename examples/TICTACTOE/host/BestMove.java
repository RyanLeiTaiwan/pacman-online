// �O���̨Ϊ��U�Ѧ�m�A�]�A��������(column)�P�C(row)
// 
final class BestMove
{
    int row;
    int column;
    int val;
    // val�N��ثe���L��
    public BestMove( int v )
      { this( v, 0, 0 ); }
    public BestMove( int v, int r, int c )
      { val = v; row = r; column = c; }
}
