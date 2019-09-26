package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	if(root==null)
	{
		root = new BST_Node(s);
		size++;
		return true;
	}
	else if(root.insertNode(s)) {
		size++;
		return true;
	}
	else
		return false;
}

@Override
public boolean remove(String s) {
	if(root.containsNode(s)) {
		if(root.getData()==s)
		{
			if(root.getLeft()==null && root.getRight()==null)
			{
				  root = null;//no elements under root
				  size = 0;
				  return true;
			}
			else {
				root.removeRoot();
				size--;
				return true;
			}
		}
		else if(root.removeNode(s)) {
			size--;
			return true;
		}
		else
			return false;
	}
	else
	{
		return false;
	}
}

@Override
public String findMin() {
	return root.findMin().getData();
}

@Override
public String findMax() {
	return root.findMax().getData();
}

@Override
public boolean empty() {
	return size == 0;
}

@Override
public boolean contains(String s) {
	return root.containsNode(s);
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	
	return root.getHeight(0);
}

}