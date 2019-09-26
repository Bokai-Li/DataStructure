package A5_Dijkstra;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	//be null. This is ok! Just build out 
	//from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
		size=0;
		//of child/parent computations...
		//the book/animation page both do this.
	}
	private void bubbleDown(int index)
	{
		if(index*2>size)
		{
			return;
		}
		EntryPair temp = array[index];
		EntryPair child1 = array[index*2];
		EntryPair child2 = array[index*2+1];
		if(child1 != null && child2 != null)
		{
			if(temp.getPriority()<child1.getPriority() && temp.getPriority()<child2.getPriority()) 
			{
				return;//finished
			}
			else if(child1.getPriority()<child2.getPriority())
			{
				array[index] = child1;
				array[index*2] = temp;
				bubbleDown(index*2);					
			}
			else
			{
				array[index]=child2;
				array[index*2+1] = temp;
				bubbleDown(index*2+1);
			}

		}
		else
		{
			if(child1!=null)
			{
				if(temp.getPriority()<child1.getPriority())
					return;
				else
				{
					array[index] = child1;
					array[index*2] = temp;
					bubbleDown(index*2);
				}
			}
		}
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		if(size==0)
		{
			array[1]=entry;
			size++;
			return;
		}
		size++;
		int holeIndex = size;
		int parentIndex=(int) Math.floor(holeIndex/2);
		while(parentIndex >0)
		{
			parentIndex =(int) Math.floor(holeIndex/2);
			if(entry.getPriority()<array[parentIndex].getPriority())
			{
				array[holeIndex]=array[parentIndex];
				holeIndex = parentIndex;
			}
			else
			{
				array[holeIndex]=entry;
				break;
			}
			if(holeIndex == 1)
			{
				array[1]=entry;
				break;
			}
		}


	}

	@Override
	public void delMin() {
		if(size==0)
		{
			return;
		}
		if(size == 1)
		{
			size = 0;
			array[1] = null;
			return;
		}
		EntryPair temp = array[size];
		size--;
		//array[1]=temp;
		//bubbleDown(1);
		
		int holeIndex = 1;
		boolean finish = false;
		while(!finish)
		{	
			if(holeIndex*2>size) //array[holeIndex*2]==null&&array[holeIndex*2+1]==null)
			{
				array[holeIndex]=temp;
				finish=true;
			}
			else if(holeIndex*2+1>size)//array[holeIndex*2+1]==null)
			{
				if(temp.getPriority()>array[holeIndex*2].getPriority())
				{
					array[holeIndex]=array[holeIndex*2];
					holeIndex=holeIndex*2;
				}
				else
				{
					array[holeIndex]=temp;
					finish=true;
				}
			}
			else
			{
				if(temp.getPriority()>array[holeIndex*2].getPriority()||temp.getPriority()>array[holeIndex*2+1].getPriority())
				{
					if(array[holeIndex*2].getPriority()<array[holeIndex*2+1].getPriority())
					{
						array[holeIndex]=array[holeIndex*2];
						holeIndex=holeIndex*2;
					}
					else
					{
						array[holeIndex]=array[holeIndex*2+1];
						holeIndex=holeIndex*2+1;
					}
				}
				else
				{
					array[holeIndex]=temp;
					finish=true;
				}
			}
		}
		array[size+1]=null;
	}

	@Override
	public EntryPair getMin() {
		if(size==0)
			return null;
		else
			return array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		for(int i = 1; i<= entries.length;i++)
		{
			size++;
			array[i]=entries[i-1];
		}

		int index=(int)Math.floor(size/2);
		while(index>0)
		{
			bubbleDown(index);
			index--;
		}
			

		/*
		EntryPair temp=array[index];
		if(size>=2)
			while(index>0)
			{
				boolean finish = false;
				while(!finish)
				{	
					if(index*2>size) //array[holeIndex*2]==null&&array[holeIndex*2+1]==null)
					{
						array[index]=temp;
						finish=true;
					}
					else if(index*2+1>size)//array[holeIndex*2+1]==null)
					{
						if(temp.getPriority()>array[index*2].getPriority())
						{
							array[index]=array[index*2];
							index=index*2;
						}
						else
						{
							array[index]=temp;
							finish=true;
						}
					}
					else
					{
						if(temp.getPriority()>array[index*2].getPriority()||temp.getPriority()>array[index*2+1].getPriority())
						{
							if(array[index*2].getPriority()<array[index*2+1].getPriority())
							{
								array[index]=array[index*2];
								index=index*2;
							}
							else
							{
								array[index]=array[index*2+1];
								index=index*2+1;
							}
						}
						else
						{
							array[index]=temp;
							finish=true;
						}
					}
				}

				/*			
				if((index*2+1)>size)
				{
					if(temp.getPriority()>array[index*2].getPriority())
					{
						array[index]=array[index*2];
						array[index*2]=temp;
					}
				}
				else
				{
					if(temp.getPriority()>array[index*2].getPriority()||temp.getPriority()>array[index*2+1].getPriority())
					{
						if(array[index*2].getPriority()<array[index*2+1].getPriority())
						{
							array[index]=array[index*2];
							array[index*2]=temp;
						}
						else
						{
							array[index]=array[index*2+1];
							array[index*2+1]=temp;
						}
					}
				}//
				index--;
				temp=array[index];
			}*/
	} 
}


