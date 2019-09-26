package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(String s){
	  if ( this.getData().equals(s) ) { 
		    // found it
		    return true;
		} else if ( this.getData().compareTo(s)> 0 ) { 
		    // item has to be in left subtree
		    if (left ==null) return false;
		    else return left.containsNode(s);
		} else { 
		    // this.val < elt, so elt has to be in right subtree 
		    if (right==null) return false;
		    else return right.containsNode(s);
		}

  }
  public boolean insertNode(String s){
	  if (this.getData().equals(s)) { 
		    // found it
		    return false;
		} else if ( this.getData().compareTo(s) > 0 ) { 
		    // item has to be in left subtree
		    if (left ==null) {
		       left = new BST_Node(s);
		       return true;
		    }
		    else return left.insertNode(s); // recursion
		} else { 
		    // this.val < elt, so elt has to be in right subtree 
		    if (right==null) {
		      right = new BST_Node(s);
		      return true;
		    }
		    else return right.insertNode(s);  //  delegation one node to another
		}

	  
  }
  public void removeRoot()
  {
	  if(left!=null && right==null)
	  {
		  data = left.getData();//only left child
		  left=null;
	  }
	  else if(left==null && right!=null)
	  {
		  data = right.getData();//only right child
		  right =null;
	  }
	  else
	  {
		//find R tree min, copy value, remove min node
		  data = this.getRight().findMin().data;
		  this.removeNode(data);
	  }
  }
  public boolean removeNode(String s){
	  
	  if (left != null && left.getData().equals(s) ) { 
		    // left child is
		  if(left.getLeft()==null && left.getRight()==null)
			  left=null;//leaf
		  else if(left.getLeft()!=null && left.getRight()==null)
			  left = left.getLeft();//only left child
		  else if(left.getLeft()==null && left.getRight()!=null)
			  left = left.getRight();//only right child
		  else//find R tree min, copy value, remove min node
		  {
			  left.data = left.getRight().findMin().data;
			  left.removeNode(left.getData());
		  }
		  return true;
	  }else if(right.getData().equals(s))
	  {		// right child is
		  if(right.getLeft()==null && right.getRight()==null)
			  right = null;//leaf
		  else if(right.getLeft()!=null && right.getRight()==null)
			  right = right.getLeft();//only left child
		  else if(right.getLeft()==null && right.getRight()!=null)
			  right = right.getRight();//only right child
		  else { //find R tree min, copy value, remove min node
			  right.data = right.getRight().findMin().data;
			  right.removeNode(right.getData());
		  }
		  return true;
		} else if ( this.getData().compareTo(s)> 0 ) { 
		    // item has to be in left subtree
		    if (left == null) return false;
		    else return left.removeNode(s);
		} else { 
		    // this.val < elt, so elt has to be in right subtree 
		    if (right == null) return false;
		    else return right.removeNode(s);
		}
	  //

  }
  public BST_Node findMin(){ 
	  if(left==null)
	  		return this;
	  else
		  return left.findMin();
	  
  }
  public BST_Node findMax(){
	  if(right==null)
		  return this;
	  else
		  return right.findMax();
  }
  public int getHeight(int h){
	  if(left==null && right == null)
		  return h;
	  else if(left == null)
		  return right.getHeight(h+1);
	  else if(right == null)
		  return left.getHeight(h+1);
	  else
		  return Math.max(left.getHeight(h+1), right.getHeight(h+1));
  }

 

  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}