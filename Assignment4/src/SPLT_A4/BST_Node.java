package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par; //parent...not necessarily required, but can be useful in splay tree
	  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
	            //I personally use it to indicate to my SPLT insert whether or not we increment size.
	
	BST_Node(String data){
		this.data=data;
		justMade = true;
	}
	
	
	BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
	    this.data=data;
	    this.left=left;
	    this.right=right;
	    this.par=par;
	    this.justMade=true;
	  }
	
	public boolean getJustMade()
	{
		return justMade;
	}
	
	private boolean isLChild(BST_Node toSplay)
	{
		if (toSplay.par!= null && toSplay == toSplay.par.left)
			return true;
		return false;
	}

	private boolean isRChild(BST_Node toSplay)
	{
		if (!isLChild(toSplay))
			return true;
		return false;
	}
	private boolean isRoot(BST_Node toSplay)
	{
		if (toSplay.par == null)
			return true;
		return false;
	}
	
	private boolean needRotateLeft(BST_Node toSplay)
	{
		if (isRoot(toSplay.par) && isRChild(toSplay))
			return true;	
		return false;
	}
	
	private boolean needRotateRight(BST_Node toSplay)
	{
		if (isRoot(toSplay.par) && isLChild(toSplay))
			return true;
		return false;
	}
	
	private boolean needZigZigL(BST_Node toSplay){
		if (!isRoot(toSplay.par) && isRChild(toSplay.par) && isRChild(toSplay))
			return true;
		return false;
	}
	
	private boolean needZigZigR(BST_Node toSplay){
		if (!isRoot(toSplay.par) && isLChild(toSplay.par) && isLChild(toSplay))
			return true;
		return false;
	}
	
	private boolean needZigZagL(BST_Node toSplay){
		if (!isRoot(toSplay.par) && isRChild(toSplay.par) && isLChild(toSplay))
			return true;
		return false;
	}
	
	private boolean needZigZagR(BST_Node toSplay){
		if (!isRoot(toSplay.par) && isLChild(toSplay.par) && isRChild(toSplay))
			return true;
		return false;
	}
	
	private void splay(BST_Node toSplay) 
	{
		if (!isRoot(toSplay))
		{
			if(needRotateLeft(toSplay))
			{
				rotateLeft(toSplay);
			}
			else if(needRotateRight(toSplay) )
			{
				rotateRight(toSplay);
			}
			else if(needZigZigR(toSplay))
			{
				rotateRight(toSplay.par);
				rotateRight(toSplay);
			}
			else if(needZigZigL(toSplay))
			{
				rotateLeft(toSplay.par);
				rotateLeft(toSplay);
			}
			else if(needZigZagR(toSplay) )
			{
				rotateRight(toSplay.par);
				rotateRight(toSplay);
				rotateLeft(toSplay);
			}
			else if(needZigZagL(toSplay) )
			{
				rotateLeft(toSplay.par);
				rotateLeft(toSplay);
				rotateRight(toSplay);
			}
			splay(toSplay);
		}
	}

	private void rotateLeft(BST_Node toSplay)
	{
			BST_Node lchild = toSplay.left;
			BST_Node parent = toSplay.par;
			BST_Node gparent = parent.par;
			if (!isRoot(parent) && isLChild(parent) )
				gparent.left = toSplay;
			else if ( !isRoot(parent) && isRChild(parent) )
				gparent.right = toSplay;
			toSplay.par = gparent;
			parent.par=toSplay;
			parent.right=lchild;
			toSplay.left=parent;
			if(lchild!=null)
				lchild.par=parent;
	}
	private void rotateRight(BST_Node toSplay)
	{
		BST_Node rchild = toSplay.right;
		BST_Node parent = toSplay.par;
		BST_Node gparent = parent.par;
		if (!isRoot(parent) && isLChild(parent))
			gparent.left = toSplay;
		else if (!isRoot(parent) && isRChild(parent))
			gparent.right = toSplay;
		toSplay.par = gparent;
		parent.par=toSplay;
		parent.left=rchild;
		toSplay.right=parent;
		if(rchild!=null)
			rchild.par=parent;
		
	}
	public String getData(){
		return data;
	}
	public BST_Node getLeft(){
		return left;
	}
	public BST_Node getRight(){
		return right;
	}
	
	public BST_Node containsNode(String s){ //it was me
		if(data.equals(s))
		{
			splay(this);
			return this;
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null)
			{
				splay(this);
				return this;
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null)
			{
				splay(this);
				return this;
			}
			return right.containsNode(s);
		}
		return this; //shouldn't hit
	}
	public BST_Node insertNode(String s){
		if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par=this;
				splay(left);
				return this.getRoot();
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par=this;
				splay(right);
				return this.getRoot();
			}
			return right.insertNode(s);
		}
		splay(this);
		this.justMade=false;
		return this;//ie we have a duplicate
	}
	public boolean removeNode(String s){ //DIO
		if(data==null)
			return false;
		if(data.equals(s)){
			if(left!=null){
				data=left.findMax().data;
				left.removeNode(data);
				if(left.data==null)left=null;
			}
			else if(right!=null){
				data=right.findMin().data;
				right.removeNode(data);
				if(right.data==null)right=null;
			}
			else data=null;
			return true;
		}
		else if(data.compareTo(s)>0){
			if(left==null)return false;
			if(!left.removeNode(s))return false;
			if(left.data==null)left=null;
			return true;
		}
		else if(data.compareTo(s)<0){
			if(right==null)return false;
			if(!right.removeNode(s))return false;
			if(right.data==null)right=null;
			return true;
		}
		return false;
	}
	public BST_Node findMin(){
		if(left!=null)
			return left.findMin();
		splay(this);
		return this;
	}
	public BST_Node findMax(){
		if(right!=null)
			return right.findMax();
		splay(this);
		return this;
	}
	
	public int getHeight(){
		int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
	}
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
	
	private BST_Node getRoot()
	{
		if (isRoot(this))
			return this;
		return this.par.getRoot();
			
	}
	public BST_Node removeRoot(BST_Node root)
	{
		BST_Node lChild = root.left;
		BST_Node rChild = root.right;
		if(lChild == null && rChild == null)
		{
			return null;
		}
		else if(lChild == null)
		{
			rChild.par=null;
			return rChild.findMin();
		}
		else if(rChild == null)
		{
			lChild.par=null;
			return lChild.findMax();
		}
		else
		{
			rChild.par=null;
			lChild.par=null;
			lChild.findMax();
			lChild.getRoot().right=rChild;
			rChild.par=lChild.getRoot();
			return lChild.getRoot();
		}
	}
}
