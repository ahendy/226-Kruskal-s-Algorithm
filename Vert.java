public  class Vert{
		public Vert parent= this;
		public int val;
		public int rank= 0;
		public Vert(int val){
			this.val = val;
			//this.parent = this;
			
		}
		public Vert(int val, Vert parent){
			this.val = val;
			this.parent = parent;


		}

	}