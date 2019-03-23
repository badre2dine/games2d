package games2d;

import java.io.Serializable;
import java.util.Random;

import javax.lang.model.util.ElementScanner6;

public class NeuralNetwork implements Serializable  {

	private static final long serialVersionUID = 1L;
	protected int input, hidenput, output;
	protected Matrix inHidenput, hidenOutput,hbias,obias;
	public NeuralNetwork(int input, int hidenput, int output) {
		super();
		this.input = input;
		this.hidenput = hidenput;
		this.output = output;	
				Random r=new Random();
	
		this.inHidenput=new Matrix(input,hidenput,x -> 10*(float)(r.nextFloat()-r.nextFloat()));
		this.hidenOutput=new Matrix(hidenput,output,x -> 10*(float)(r.nextFloat()-r.nextFloat()));
		this.hbias=new Matrix(1,hidenput,x ->10* (float)(r.nextFloat()-r.nextFloat()));
		this.obias=new Matrix(1,output,x -> 10*(float)(r.nextFloat()-r.nextFloat()));
	}
	public NeuralNetwork(NeuralNetwork nn) {
		super();
		this.input = nn.input;
		this.hidenput = nn.hidenput;
		this.output = nn.output;	
				
	
		this.inHidenput=new Matrix(nn.inHidenput);
		this.hidenOutput=new Matrix(nn.hidenOutput);
		this.hbias=new Matrix(nn.hbias);
		this.obias=new Matrix(nn.obias);
	}
	public Matrix softmax(Matrix m)//matrix with  1,n
	{
		 
		float summ=m.map(x ->(float) Math.exp(x)).summ();
		
		return m.map(x -> (float)Math.exp(x)/summ);


	}
	String save()
	{

		
		return null;

		
	}
	public float sigmoid(float x)
	{

		return (float) (1 / (1 + Math.exp(-x)));

	}
	public Matrix guess(Matrix inputs)
	{	
		return inputs.mmult(this.inHidenput).//IN*IH
		add(this.hbias).//IN*IH+hbias
		map(x -> sigmoid(x)).//SIGMOID
		mmult(this.hidenOutput).//IH*HO
		add(this.obias).map(x ->sigmoid(x));
		

	}

	public float RandomMutate(float x, float probabilite,Random r)
	{
	
		if(r.nextFloat() <= probabilite)return (float)((x*r.nextFloat()*2));
			return x;

	}
	public void mutate( float probabilite)
	{
		Random r= new Random();

		this.inHidenput=this.inHidenput.map( x -> RandomMutate(x,probabilite,r));
		this.hidenOutput=this.hidenOutput.map( x -> RandomMutate(x,probabilite,r));
		this.hbias=this.hbias.map( x -> x+RandomMutate(x,probabilite,r));
		this.obias=this.obias.map( x -> RandomMutate(x,probabilite,r));
	}
	public NeuralNetwork crossover(NeuralNetwork nn)
	{
		Random r= new Random();
		NeuralNetwork child = new NeuralNetwork(this);
		float v=r.nextFloat();
		if(v<1f/3f)
		{
			child.inHidenput=(this.inHidenput.add(nn.inHidenput.map(x -> -x))).map(x -> x/2);
			child.hidenOutput=(this.hidenOutput.add(nn.hidenOutput.map(x -> -x))).map(x -> x/2);
			child.hbias=(this.hbias.add(nn.hbias.map(x -> -x))).map(x -> x/2);
			child.obias=(this.obias.add(nn.obias.map(x -> -x))).map(x -> x/2);

		}
		else if(v<2f/3f)
		{
			child.inHidenput=(this.inHidenput.map(x -> -x).add(nn.inHidenput)).map(x -> x/2);
			child.hidenOutput=(this.hidenOutput.map(x -> -x).add(nn.hidenOutput)).map(x -> x/2);
			child.hbias=(this.hbias.map(x -> -x).add(nn.hbias)).map(x -> x/2);
			child.obias=(this.obias.map(x -> -x).add(nn.obias)).map(x -> x/2);
		}
		else
		{
			child.inHidenput=(this.inHidenput.add(nn.inHidenput)).map(x -> x/2);
			child.hidenOutput=(this.hidenOutput.add(nn.hidenOutput)).map(x -> x/2);
			child.hbias=(this.hbias.add(nn.hbias)).map(x -> x/2);
			child.obias=(this.obias.add(nn.obias)).map(x -> x/2);
		}
		return nn;
	}
	

}
