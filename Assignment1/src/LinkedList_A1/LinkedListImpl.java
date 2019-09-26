/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
  int numElts;

  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
    numElts = 0;
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	if(index > numElts)
		return false;
	else
	{
		if(numElts == 0)
		{
			Node c = new Node(elt);
			c.prev = sentinel;
			c.next = sentinel;
			sentinel.next=c;
			sentinel.prev=c;
			numElts++;
			return true;
		}
		else
		{
			Node curr = sentinel.next;
			int ct = 0;
			while(curr != sentinel)
			{
				if(ct < index)
				{
					curr = curr.next;
					ct++;
				}
				else
				{
					break;
				}
			}
			Node c = new Node(elt);
			c.prev = curr.prev;
			curr.prev = c;
			c.prev.next = c;
			c.next = curr;
			numElts++;
			return true;
		}
	}
}


@Override
public boolean remove(int index) {
	if(index >= numElts)
		return false;
	else
	{
		if(numElts == 1)
		{
			sentinel.next=null;
			sentinel.prev=null;
			numElts--;
			return true;
		}
		else
		{
			Node curr = sentinel.next;
			int ct = 0;
			while(curr != sentinel)
			{
				if(ct < index)
				{
					curr = curr.next;
					ct++;
				}
				else
				{
					break;
				}	
			}	
			curr.prev.next=curr.next;
			curr.next.prev=curr.prev;
			numElts--;
			return true;
		}
	}
}

@Override
public double get(int index) {
	Node curr = sentinel.next;
	int ct = 0;
	if(index>=this.size())
	{
		return Double.NaN;
	}
	while(curr != sentinel)
	{
		if(ct < index)
		{
			curr = curr.next;
			ct++;
		}
		else
		{
			break;
		}
	}
	return curr.getData();
}

@Override
public int size() {
	return numElts;
}

@Override
public boolean isEmpty() {
	return numElts == 0;
}

@Override
public void clear() {
	for(int i = 0; i<numElts; i++)
		this.remove(0);
	numElts = 0;
}
}