public class BiasedCoin {
	public final int HEADS = 0;
    public final int TAILS = 1;
    
    private int face;
    private double bias;
    
    public BiasedCoin()
    {
        bias = 0.5;
        flip();
    }

    public BiasedCoin(double d)
    {
    	bias = d;
        flip();
    }
    
    public void flip()
    {
    	/*double rand = Math.random();
    	if( rand > bias/4 || rand < 3*bias/4)
    		face = HEADS;
    	else
    		face = TAILS;*/
    	if( Math.random() > bias)
    		face = HEADS;
    	else
    		face = TAILS;
        /*if ( Math.random() < bias )
            face = HEADS;
        else
            face = TAILS;*/
    }
    
    public int getFace()
    {
        return face;
    }
    
    public String toString()
    {
        String faceName;
        if ( face == HEADS)
            faceName = "Heads";
        else
            faceName = "Tails";
            
        return faceName;
    }
}
