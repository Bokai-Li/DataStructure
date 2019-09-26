package SPLT_A4;

public class SPLT implements SPLT_Interface{
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
	} 

	public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
		return root;
	}

	@Override
	public void insert(String s) { //It was me
		// TODO Auto-generated method stub
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
		}
		else
		{	
			root = root.insertNode(s);
			if(root.getJustMade())
				size += 1;
		}
	}


	@Override
	public void remove(String s) {  //DIO
		// TODO Auto-generated method stub
		if(contains(s)) {
			if(root.getData().equals(s))
			{
				size--;
			}
			root = root.removeRoot(root);
		}
	}

	@Override
	public String findMin() {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		} else {
			root=root.findMin();
			return root.data;
		}
	}

	@Override
	public String findMax() {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		} else {
			root=root.findMax();
			return root.data;
		}
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		if (empty()) {
			return false;
		}
		root = root.containsNode(s);
		if(root.getData().equals(s))
			return true;
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

}