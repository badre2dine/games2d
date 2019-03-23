package games2d;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Matrix implements Serializable{

	private static final long serialVersionUID = 1L;
	final public static int MATRIX_RANDOM = 0;
	private int numberOfRows, numberOfCols;
	public Float[][] data;
	public Matrix(float [][]A)
	{
		this.setNumberOfRows(A.length);
		this.setNumberOfCols(A[0].length);
		this.data =new Float[this.numberOfRows][this.numberOfCols];

		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				this.data[i][j]=A[i][j];
			}
		
		}
		
	}
	public Matrix(float []A)
	{
		this.setNumberOfRows(1);
		this.setNumberOfCols(A.length);
		this.data =new Float[this.numberOfRows][this.numberOfCols];

		
			for(int j=0;j<this.numberOfCols;j++)
			{
				this.data[0][j]=A[j];
			}
		
		
		
	}
	public Matrix(Matrix m)
	{
		this.setNumberOfRows(m.numberOfRows);
		this.setNumberOfCols(m.numberOfCols);
		this.data =new Float[this.numberOfRows][this.numberOfCols];

		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				this.data[i][j]=m.data[i][j];
			}
		
		}
		
	}
	public Matrix(int numberOfRows, int numberOfCols) {

		this.setNumberOfRows(numberOfRows);
		this.setNumberOfCols(numberOfCols);

		this.data =new Float[this.numberOfRows][this.numberOfCols];
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				this.data[i][j]=0f;
			}
		
		}


		// TODO Auto-generated constructor stub

	}

	public Matrix(int numberOfRows, int numberOfCols, int parametre) {

		this.setNumberOfRows(numberOfRows);
		this.setNumberOfCols(numberOfCols);
		this.data =new Float[this.numberOfRows][this.numberOfCols];
switch(parametre)
{
case MATRIX_RANDOM: 
{
	Random r=new Random();
	for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				this.data[i][j]=r.nextFloat();
			}
		
		}
}break;
default :
{
		
	for(int i=0;i<this.numberOfRows;i++)
	{
		for(int j=0;j<this.numberOfCols;j++)
		{
			this.data[i][j]=0f;
		}
	
	}
}
}
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the numberOfRows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/**
	 * @return the numberOfCols
	 */
	public int getNumberOfCols() {
		return numberOfCols;
	}

	/**
	 * @param numberOfCols the numberOfCols to set
	 */
	public void setNumberOfCols(int numberOfCols) {
		this.numberOfCols = numberOfCols;
	}

	@Override
	public String toString() {

		String matrice ="";
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				matrice+="\t"+this.data[i][j];
			}
			matrice+="\n";
		}
		return matrice;
	}
	public Matrix map(Function<Float,Float> x){

		Matrix result=new Matrix(this);
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				result.data[i][j]=x.apply(this.data[i][j]);
			}
		
		}
		return result;
	}
	
	public Matrix mmult(Matrix m)
	{
		Matrix result = new Matrix(this.numberOfRows,m.numberOfCols);
		for (int i = 0; i < result.numberOfRows; i++) {
			for (int k = 0; k < this.numberOfCols; k++) {
				for (int j = 0; j < result.numberOfCols; j++) {
					result.data[i][j] += this.data[i][k] * m.data[k][j];
				}
			}
		}
		return result;
	}

	public Matrix add(Matrix m){



		Matrix result=new Matrix(this);
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				result.data[i][j]+=m.data[i][j];
			}
		
		}
		return result;
	}
	
	public Matrix subtract(Matrix m){



		Matrix result=new Matrix(this);
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++)
			{
				result.data[i][j]-=m.data[i][j];
			}
		
		}
		return result;
	}
	public Matrix  parallelMult(Matrix A, int threadNumber) {
		Matrix C = new Matrix(this.numberOfRows, A.numberOfCols);
ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
List<Future<Matrix>> list = new ArrayList<Future<Matrix>>();

int part = A.numberOfRows / threadNumber;
if (part < 1) {
	part = 1;
}
for (int i = 0; i < A.numberOfRows; i += part) {
	System.err.println(i);
	Callable<Matrix> worker = new LineMultiplier(this, A,i, i+part);
	Future<Matrix> submit = executor.submit(worker);
	list.add(submit);
}

// now retrieve the result
int start = 0;
Matrix CF;
for (Future<Matrix> future : list) {
	try {
		CF = future.get();
		for (int i=start; i < start+part; i += 1) {
			C.data[i] = CF.data[i];
		}
	} catch (InterruptedException e) {
		e.printStackTrace();
	} catch (ExecutionException e) {
		e.printStackTrace();
	}
	start+=part;
}
executor.shutdown();

return C;
}

	public class LineMultiplier implements Callable<Matrix> {
	    Matrix A;
	    Matrix B;
	    int start;
	    int end;
	    public Matrix C;

	    public LineMultiplier(Matrix a,
	    		Matrix b, int s, int e) {
	        A = a;
	        B = b;
	        C = new Matrix(a.numberOfRows,b.numberOfCols);
	        start = s;
	        end = e;
	    }

	    @Override
	    public Matrix call() {
	        for (int i = start; i < end; i++) {
	            for (int k = 0; k < B.numberOfRows; k++) {
	                for (int j = 0; j < B.numberOfCols; j++) {
	                    C.data[i][j] += A.data[i][k] * B.data[k][i];
	                }
	            }
	        }
	        return C;
	    }
	}
	public Matrix(int numberOfRows,int numberOfCols,Function<Float,Float> x){
		this.setNumberOfRows(numberOfRows);
		this.setNumberOfCols(numberOfCols);
		this.data =new Float[this.numberOfRows][this.numberOfCols];
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++){
				this.data[i][j]=x.apply(0f);
			}
		}
		
	}

	float max()
	{
		float max = this.data[0][0];
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++){
				if(max<this.data[i][j])max=this.data[i][j];
			}
		}
		return max;
	}

	float min()
	{
		float min = this.data[0][0];
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++){
				if(min>this.data[i][j])min=this.data[i][j];
			}
		}
		return min;
	}
	float summ()
	{
		float sum=0;
		for(int i=0;i<this.numberOfRows;i++)
		{
			for(int j=0;j<this.numberOfCols;j++){
				sum+=this.data[i][j];
			}
		}
		return sum;
	}
	public void print()
	{
		System.out.println(toString());
	}
	static public Matrix pivot_gauss(Matrix m,float b[])
	{

		test.print(m);
		
		Matrix M=new Matrix(1,m.numberOfRows);
		for(int i=0 ;i<m.numberOfRows;i++)
		{

			for(int j=i+1;j<m.numberOfRows;j++)
			{
				
				float pivot =m.data[j][i]/m.data[i][i];
				
				for(int k=i;k<m.numberOfRows;k++)
				{
					m.data[j][k]-=m.data[i][k]*pivot;
				}
				b[j]-=b[i]*pivot;;
			}
		}
		test.print(m);
		for(int i=0 ;i<m.numberOfRows;i++)
		{

			test.print(b[i]);
			
		
		}
		for(int i=m.numberOfRows-1 ;i>=0;i--)
		{

			M.data[0][i]=b[i];
			for(int j=m.numberOfRows-1 ;j>i;j--)
			M.data[0][i]-=M.data[0][j]*m.data[i][j];
			
			M.data[0][i]/=m.data[i][i];
		
		}

		return M;
	}

}
